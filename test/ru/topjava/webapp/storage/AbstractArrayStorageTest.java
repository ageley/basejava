package ru.topjava.webapp.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import static ru.topjava.webapp.storage.AbstractArrayStorage.STORAGE_LENGTH;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveStorageFull() {
        int initialSize = storage.size();
        try {
            for (int i = 0; i < STORAGE_LENGTH - initialSize; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("StorageException thrown earlier, than expected");
        }
        storage.save(new Resume());
    }

}