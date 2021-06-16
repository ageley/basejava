package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void saveByIndex(int resumeIndex, Resume resume);

    protected abstract void deleteByIndex(int resumeIndex);

    private int findIndexIfResumeNotExist(String uuid) {
        final int resumeIndex = getIndex(uuid);
        if (resumeIndex >= 0) {
            throw new ExistStorageException(uuid);
        }
        return resumeIndex;
    }

    private int findIndexIfResumeExist(String uuid) {
        final int resumeIndex = getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return resumeIndex;
    }

    public final void save(Resume resume) {
        final String uuid = resume.getUuid();
        saveByIndex(findIndexIfResumeNotExist(uuid), resume);
    }

    protected abstract Resume getFromStorage(int resumeIndex);

    public final Resume get(String uuid) {
        return getFromStorage(findIndexIfResumeExist(uuid));
    }

    public final void delete(String uuid) {
        deleteByIndex(findIndexIfResumeExist(uuid));
    }

    protected abstract void updateInStorage(int resumeIndex, Resume resume);

    public final void update(Resume resume) {
        final String uuid = resume.getUuid();
        updateInStorage(findIndexIfResumeExist(uuid), resume);
    }
}
