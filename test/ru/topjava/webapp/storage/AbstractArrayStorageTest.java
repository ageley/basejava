package ru.topjava.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final Resume resume1;
    private final Resume resume2;
    private final Resume resume3;
    private final Resume resume4;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
        resume1 = new Resume("uuid1");
        resume2 = new Resume("uuid2");
        resume3 = new Resume("uuid3");
        resume4 = new Resume("uuid4");
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(resume1);
        storage.save(resume2);
        storage.save(resume3);
    }

    @Test
    public void save() {
        storage.save(resume4);
        Assert.assertEquals(resume4, storage.get("uuid4"));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(resume2);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        try {
            storage.delete(resume2.getUuid());
            Assert.assertEquals(2, storage.size());
        } catch (NotExistStorageException e) {
            Assert.fail("NotExistStorageException thrown earlier, than expected");
        }
        storage.get("uuid2");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(resume4.getUuid());
    }

    @Test
    public void update() {
        final Resume resume3Update = new Resume("uuid3");
        storage.update(resume3Update);
        Assert.assertEquals(resume3Update, storage.get("uuid3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(resume4);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, storage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(resume4.getUuid());
    }

    @Test
    public void getAll() {
        Assert.assertArrayEquals(new Resume[] {resume1, resume2, resume3}, storage.getAll());
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
    }

    @Test(expected = StorageException.class)
    public void saveStorageFull() {
        int initialSize = storage.size();
        try {
            for (int i = 0; i < storage.length() - initialSize; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("StorageException thrown earlier, than expected");
        }
        storage.save(new Resume());
    }
}