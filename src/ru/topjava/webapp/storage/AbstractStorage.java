package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Object getKey(String uuid);

    protected abstract void saveByKey(Object resumeKey, Resume resume);

    protected abstract void deleteByKey(Object resumeKey);

    protected boolean isResumeExists(Object resumeKey) {
        return ((int) resumeKey >= 0);
    }

    private Object findKeyIfResumeNotExist(String uuid) {
        final Object resumeKey = getKey(uuid);
        if (isResumeExists(resumeKey)) {
            throw new ExistStorageException(uuid);
        }
        return resumeKey;
    }

    private Object findKeyIfResumeExist(String uuid) {
        final Object resumeKey = getKey(uuid);
        if (!isResumeExists(resumeKey)) {
            throw new NotExistStorageException(uuid);
        }
        return resumeKey;
    }

    public final void save(Resume resume) {
        final String uuid = resume.getUuid();
        saveByKey(findKeyIfResumeNotExist(uuid), resume);
    }

    protected abstract Resume getFromStorage(Object resumeKey);

    public final Resume get(String uuid) {
        return getFromStorage(findKeyIfResumeExist(uuid));
    }

    public final void delete(String uuid) {
        deleteByKey(findKeyIfResumeExist(uuid));
    }

    protected abstract void updateInStorage(Object resumeKey, Resume resume);

    public final void update(Resume resume) {
        final String uuid = resume.getUuid();
        updateInStorage(findKeyIfResumeExist(uuid), resume);
    }
}
