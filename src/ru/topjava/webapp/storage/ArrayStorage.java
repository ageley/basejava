package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[3];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage,null);
        size = 0;
    }

    public void save(Resume r) {
        int resumeIndex = this.getResumeIndex(r.getUuid());
        if (resumeIndex >= 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.save: uuid '%1$s' is already exists", r.getUuid()));
        } else if (size >= storage.length) {
            System.out.println("ERROR ru.topjava.webapp.storage.ArrayStorage.save: storage is full");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = this.getResumeIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.get: uuid '%1$s' is not exists", uuid));
        } else {
            return storage[resumeIndex];
        }
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = this.getResumeIndex(uuid);
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

    public void update(Resume r) {
        int resumeIndex = this.getResumeIndex(r.getUuid());
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.update: uuid '%1$s' is not exists", r.getUuid()));
        } else {
            storage[resumeIndex] = r;
        }
    }

    public int getResumeIndex(String uuid) {
        if (uuid == null) {
            System.out.println("ERROR ru.topjava.webapp.storage.ArrayStorage.getResumeIndex: uuid is null");
        } else {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
