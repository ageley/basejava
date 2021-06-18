package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

/**
 * Unsorted array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveByIndexToArray(int resumeIndex, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void deleteByIndexFromArray(int resumeIndex) {
        storage[resumeIndex] = storage[size - 1];
    }
}
