package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected Object getIndex(String uuid) {
        final Resume resumeToSearch = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resumeToSearch);
    }

    @Override
    protected void saveByIndexToArray(int resumeIndex, Resume resume) {
        final int resumeIndexToSave = -resumeIndex - 1;
        System.arraycopy(storage, resumeIndexToSave, storage, resumeIndexToSave + 1, size - resumeIndexToSave);
        storage[resumeIndexToSave] = resume;
    }

    @Override
    protected void deleteByIndexFromArray(int resumeIndex) {
        System.arraycopy(storage, resumeIndex + 1, storage, resumeIndex, size - resumeIndex - 1);
    }
}
