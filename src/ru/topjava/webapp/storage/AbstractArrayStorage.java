package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LENGTH = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LENGTH];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    private void checkStorageIsNotFull(String uuid) {
        if (size >= STORAGE_LENGTH) {
            throw new StorageException("storage is full", uuid);
        }
    }

    protected abstract void saveByIndexToArray(int resumeIndex, Resume resume);

    @Override
    protected void saveByIndex(Object resumeIndex, Resume resume) {
        final String uuid = resume.getUuid();
        checkStorageIsNotFull(uuid);
        saveByIndexToArray((int) resumeIndex, resume);
        size++;
    }

    @Override
    protected Resume getFromStorage(Object resumeIndex) {
        return storage[(int) resumeIndex];
    }

    protected abstract void deleteByIndexFromArray(int resumeIndex);

    @Override
    protected void deleteByIndex(Object resumeIndex) {
        deleteByIndexFromArray((int) resumeIndex);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void updateInStorage(Object resumeIndex, Resume resume) {
        storage[(int) resumeIndex] = resume;
    }
}
