package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume resumeToSearch = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resumeToSearch);
    }

    @Override
    protected void saveByIndexToArray(int resumeIndex, Resume resume) {
        int resumeIndexInverted = -resumeIndex;
        System.arraycopy(storage, resumeIndexInverted - 1, storage, resumeIndexInverted, size - resumeIndexInverted + 1);
        storage[resumeIndexInverted - 1] = resume;
    }

    @Override
    protected void deleteByIndexFromArray(int resumeIndex) {
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex - 1);
    }
}
