package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume resumeToSearch = new Resume();
        resumeToSearch.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resumeToSearch);
    }

    @Override
    protected void saveByIndex(int resumeIndex, Resume resume) {
        int resumeIndexNext = resumeIndex + 1;
        int resumeIndexToSave = -resumeIndexNext;
        int rangeToCopy = size + resumeIndexNext;
        System.arraycopy(storage, -resumeIndexNext, storage, -resumeIndex, rangeToCopy);
        storage[resumeIndexToSave] = resume;
    }

    @Override
    protected void deleteByIndex(int resumeIndex) {
        int resumeIndexNext = resumeIndex + 1;
        int rangeToCopy = size - resumeIndexNext;
        System.arraycopy(storage, resumeIndexNext, storage, resumeIndex, rangeToCopy);
    }
}
