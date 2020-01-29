package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Unsorted array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected void saveByIndex(int resumeIndex, Resume resume) {
        storage[size] = resume;
        size++;
    }

    protected void deleteByIndex(int resumeIndex) {
        storage[resumeIndex] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }
}
