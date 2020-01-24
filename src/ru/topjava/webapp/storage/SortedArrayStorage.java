package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Sorted array based storage for Resumes
 */
public class SortedArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.save: uuid '%1$s' is already exists", resume.getUuid()));
        } else if (size >= STORAGE_LENGTH) {
            System.out.println("ERROR ru.topjava.webapp.storage.ArrayStorage.save: storage is full");
        } else {
            for (int k = size - 1; k >= -(resumeIndex + 1); k--) {
                storage[k+1] = storage[k];
            }
            storage[-(resumeIndex + 1)] = resume;
            size++;

        }
    }

    public void delete(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.delete: uuid '%1$s' is not exists", uuid));
        } else {
            for (int k = resumeIndex; k < size - 1; k++) {
                storage[k] = storage[k+1];
            }
            size--;
        }
    }

     protected int getIndex(String uuid) {
        Resume resumeToSearch = new Resume();
        resumeToSearch.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resumeToSearch);
    }
}
