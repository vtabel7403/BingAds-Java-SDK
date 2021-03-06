package com.microsoft.bingads.v13.internal.bulk;

import java.io.File;
import java.io.IOException;

import com.microsoft.bingads.internal.ErrorMessages;
import com.microsoft.bingads.internal.functionalinterfaces.Predicate;

/**
 * Reads a bulk object and also its related data (for example corresponding
 * errors) from the stream
 */
public class SimpleBulkRecordReader implements BulkRecordReader {

    private BulkObjectReader objectReader;
    private BulkObject nextObject;    
    private boolean passedFirstRow = false;
    private boolean deleteFileOnClose;
    private File file;

    public SimpleBulkRecordReader(BulkObjectReader reader) {
        objectReader = reader;
    }

    /**
     * Returns the next object from the file
     *
     * @return Next object
     */
    @Override
    public BulkObject read() {
        TryResult<BulkObject> result = tryRead(BulkObject.class);
        if (result.isSuccessful()) {
            return result.getResult();
        }

        try {
            // reaching here mean there is no more content to read. We'd close it.
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Reads the object only if it has a certain type
     *
     * @param <T> Type of the object
     * @param klazz The next object from the file if the object has the same
     * type as requested, null otherwise
     * @return True if object has requested type, false otherwise
     */
    @Override
    public <T extends BulkObject> TryResult<T> tryRead(Class<T> klazz) {
        return tryRead(new Predicate<T>() {
            @Override
            public boolean test(T obj) {
                return true;
            }
        }, klazz);
    }

    /**
     * Reads the object only if it matches a predicate
     *
     * @param <T> Type of the object
     * @param predicate Predicate that needs to be matched
     * @param klazz The next object from the file if the object matches the
     * predicate, null otherwise
     * @return True if object matches the predicate, false otherwise</returns>
     */
    @Override
    public <T extends BulkObject> TryResult<T> tryRead(Predicate<T> predicate, Class<T> klazz) {
        BulkObject peeked = peek();
        
        if (klazz.isInstance(peeked)) {
            if (predicate.test(klazz.cast(peeked))) {
                nextObject = null;

                peeked.readRelatedData(this);
                
                return new TryResult(true, klazz.cast(peeked));
            }
        }

        return new TryResult(false, null);
    }

    private BulkObject peek() {
        if (!passedFirstRow) {
            BulkObject firstRowObject = objectReader.readNextBulkObject();

            if (firstRowObject instanceof FormatVersion) {
            	FormatVersion formatVersion = (FormatVersion)firstRowObject;
            	
            	if(formatVersion != null) {
                	String version = formatVersion.getValue();
                	if (!version.equals(StringTable.FORMAT_VERSION_SIMPLE) && !version.equals(StringTable.FORMAT_VERSION)) {
                		throw new UnsupportedOperationException(ErrorMessages.FormatVersionIsNotSupported + version);
                	}
                }
            } else {
                nextObject = firstRowObject;
            }
            
            passedFirstRow = true;
        }

        if (nextObject != null) {
            return nextObject;
        }

        nextObject = objectReader.readNextBulkObject();
        
        return nextObject;
    }

    @Override
    public void close() throws IOException {
        try {
            this.objectReader.close();
        } finally {
            if (deleteFileOnClose && file != null && file.exists()) {
                file.delete();
            }
        }
    }
}
