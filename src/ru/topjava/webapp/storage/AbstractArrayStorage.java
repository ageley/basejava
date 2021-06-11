package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
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
    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void saveToStorage(int resumeIndex, Resume resume) {
        if (size >= STORAGE_LENGTH) {
            throw new StorageException("storage is full", resume.getUuid());
        } else {
            saveByIndex(resumeIndex, resume);
            size++;
        }
    }

    @Override
    protected Resume getFromStorage(int resumeIndex) {
        return storage[resumeIndex];
    }

    @Override
    protected void deleteFromStorage(int resumeIndex) {
        deleteByIndex(resumeIndex);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume[] getAllFromStorage() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected int getSizeOfStorage() {
        return size;
    }

    @Override
    protected void updateInStorage(int resumeIndex, Resume resume) {
        storage[resumeIndex] = resume;
    }
}
