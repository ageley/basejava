package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    protected int getIndex(String uuid) {
        Resume resumeToSearch = new Resume();
        resumeToSearch.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resumeToSearch);
    }

    protected void saveByIndex(int resumeIndex, Resume resume) {
        int rangeToCopy;
        int resumeIndexNext = resumeIndex + 1;
        int resumeIndexToSave = -(resumeIndex + 1);
        rangeToCopy = size + resumeIndexNext;
        System.arraycopy(storage, -resumeIndexNext, storage, -resumeIndex, rangeToCopy);
        storage[resumeIndexToSave] = resume;
        size++;
    }

    protected void deleteByIndex(int resumeIndex) {
        int rangeToCopy;
        int resumeIndexNext = resumeIndex + 1;
        rangeToCopy = size - resumeIndexNext;
        System.arraycopy(storage, resumeIndexNext, storage, resumeIndex, rangeToCopy);
        storage[size - 1] = null;
        size--;
    }
}
