package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LENGTH = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LENGTH];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public abstract void save(Resume resume);

    public Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.get: uuid '%1$s' is not exists", uuid));
            return null;
        } else {
            return storage[resumeIndex];
        }
    }

    public abstract void delete(String uuid);

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
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex < 0) {
            System.out.println(String.format("ERROR ru.topjava.webapp.storage.ArrayStorage.update: uuid '%1$s' is not exists", resume.getUuid()));
        } else {
            storage[resumeIndex] = resume;
        }
    }

    protected abstract int getIndex(String uuid);
}
