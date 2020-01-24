package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Unsorted array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.save: uuid '%1$s' is already exists", resume.getUuid()));
        } else if (size >= STORAGE_LENGTH) {
            System.out.println("ERROR ru.topjava.webapp.storage.ArrayStorage.save: storage is full");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.delete: uuid '%1$s' is not exists", uuid));
        } else {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
