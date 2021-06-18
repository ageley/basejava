package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getIndex(String uuid);

    protected abstract void saveByIndex(Object resumeIndex, Resume resume);

    protected abstract void deleteByIndex(Object resumeIndex);

    protected Object findIndexIfResumeNotExist(String uuid) {
        final int resumeIndex = (int) getIndex(uuid);
        if (resumeIndex >= 0) {
            throw new ExistStorageException(uuid);
        }
        return resumeIndex;
    }

    protected Object findIndexIfResumeExist(String uuid) {
        final int resumeIndex = (int) getIndex(uuid);
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
        return resumeIndex;
    }

    public final void save(Resume resume) {
        final String uuid = resume.getUuid();
        saveByIndex(findIndexIfResumeNotExist(uuid), resume);
    }

    protected abstract Resume getFromStorage(Object resumeIndex);

    public final Resume get(String uuid) {
        return getFromStorage(findIndexIfResumeExist(uuid));
    }

    public final void delete(String uuid) {
        deleteByIndex(findIndexIfResumeExist(uuid));
    }

    protected abstract void updateInStorage(Object resumeIndex, Resume resume);

    public final void update(Resume resume) {
        final String uuid = resume.getUuid();
        updateInStorage(findIndexIfResumeExist(uuid), resume);
    }
}
