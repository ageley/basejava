package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.save: uuid '%1$s' is already exists", resume.getUuid()));
        } else if (size >= storage.length) {
            System.out.println("ERROR ru.topjava.webapp.storage.ArrayStorage.save: storage is full");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.get: uuid '%1$s' is not exists", uuid));
            return null;
        } else {
            return storage[resumeIndex];
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getResumeIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.delete: uuid '%1$s' is not exists", uuid));
        } else {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public void update(Resume resume) {
        int resumeIndex = getResumeIndex(resume.getUuid());
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.update: uuid '%1$s' is not exists", resume.getUuid()));
        } else {
            storage[resumeIndex] = resume;
        }
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
