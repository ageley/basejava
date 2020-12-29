package ru.topjava.webapp.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorageTest {
    private final Storage arrayStorage;
    private final Resume resume1;
    private final Resume resume2;
    private final Resume resume3;
    private final Resume resume3Update;
    private final Resume resume4;

    public AbstractArrayStorageTest(AbstractArrayStorage arrayStorage) {
        this.arrayStorage = arrayStorage;
        resume1 = new Resume("uuid1");
        resume2 = new Resume("uuid2");
        resume3 = new Resume("uuid3");
        resume3Update = new Resume("uuid3");
        resume4 = new Resume("uuid4");
    }

    @Before
    public void setUp() throws Exception {
        arrayStorage.clear();
        arrayStorage.save(resume1);
        arrayStorage.save(resume2);
        arrayStorage.save(resume3);
    }

    @Test
    public void save() {
        arrayStorage.save(resume4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        arrayStorage.save(resume2);
    }

    @Test
    public void delete() {
        arrayStorage.delete(resume2.getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        arrayStorage.delete(resume4.getUuid());
    }

    @Test
    public void update() {
        arrayStorage.update(resume3Update);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        arrayStorage.update(resume4);
    }

    @Test
    public void get() {
        Assert.assertEquals(resume1, arrayStorage.get("uuid1"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        arrayStorage.get(resume4.getUuid());
    }

    @Test
    public void getAll() {
        Assert.assertEquals(0, Arrays.binarySearch(arrayStorage.getAll(), resume1));
        Assert.assertEquals(-4, Arrays.binarySearch(arrayStorage.getAll(), resume4));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, arrayStorage.size());
    }

    @Test
    public void clear() {
        arrayStorage.clear();
    }

    @Test
    public void saveMax() {
        for (int i = 0; i < 10000 - 3; i++) {
            arrayStorage.save(new Resume());
        }
    }

    @Test(expected = StorageException.class)
    public void saveStorageFull() {
        for (int i = 0; i < 10000 - 2; i++) {
            arrayStorage.save(new Resume());
        }
    }
}