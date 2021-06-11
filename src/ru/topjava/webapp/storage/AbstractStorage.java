package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void saveByIndex(int resumeIndex, Resume resume);

    protected abstract void deleteByIndex(int resumeIndex);

    protected abstract void clearStorage();

    public final void clear() {
        clearStorage();
    }

    protected abstract void saveToStorage(int resumeIndex, Resume resume);

    public final void save(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            saveToStorage(resumeIndex, resume);
        }
    }

    protected abstract Resume getFromStorage(int resumeIndex);

    public final Resume get(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            return getFromStorage(resumeIndex);
        }
    }

    protected abstract void deleteFromStorage(int resumeIndex);

    public final void delete(String uuid) {
        int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteFromStorage(resumeIndex);
        }
    }

    protected abstract Resume[] getAllFromStorage();

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public final Resume[] getAll() {
        return getAllFromStorage();
    }

    protected abstract int getSizeOfStorage();

    public final int size() {
        return getSizeOfStorage();
    }

    protected abstract void updateInStorage(int resumeIndex, Resume resume);

    public final void update(Resume resume) {
        int resumeIndex = getIndex(resume.getUuid());
        if (resumeIndex < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            updateInStorage(resumeIndex, resume);
        }
    }
}
