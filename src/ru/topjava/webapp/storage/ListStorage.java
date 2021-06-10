package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.exception.StorageException;
import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage implements Storage {
    protected final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        final Resume tempResume = new Resume(uuid);
        return storage.indexOf(tempResume);
    }

    @Override
    protected void saveByIndex(int resumeIndex, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteByIndex(int resumeIndex) {
        storage.remove(resumeIndex);
    }

    public void clear() {
        storage.clear();
    }

    public void save(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveByIndex(resumeIndex, resume);
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return storage.get(resumeIndex);
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteByIndex(resumeIndex);
        }
    }

    public Resume[] getAll() {
        final Resume[] tempArray = new Resume[this.size()];
        return storage.toArray(tempArray);
    }

    public int size() {
        return storage.size();
    }

    public void update(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage.set(resumeIndex, resume);
        }
    }
}
