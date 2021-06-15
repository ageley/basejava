package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void saveByIndex(int resumeIndex, Resume resume);

    protected abstract void deleteByIndex(int resumeIndex);

    protected void checkResumeCanBeSaved(int resumeIndex, String uuid) {
        if (resumeIndex >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    protected void checkResumeCanBeFound(int resumeIndex, String uuid) {
        if (resumeIndex < 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    public final void save(Resume resume) {
        final String uuid = resume.getUuid();
        final int resumeIndex = getIndex(uuid);
        checkResumeCanBeSaved(resumeIndex, uuid);
        saveByIndex(resumeIndex, resume);
    }

    protected abstract Resume getFromStorage(int resumeIndex);

    public final Resume get(String uuid) {
        final int resumeIndex = getIndex(uuid);
        checkResumeCanBeFound(resumeIndex, uuid);
        return getFromStorage(resumeIndex);
    }

    public final void delete(String uuid) {
        final int resumeIndex = getIndex(uuid);
        checkResumeCanBeFound(resumeIndex, uuid);
        deleteByIndex(resumeIndex);
    }

    protected abstract void updateInStorage(int resumeIndex, Resume resume);

    public final void update(Resume resume) {
        final String uuid = resume.getUuid();
        final int resumeIndex = getIndex(uuid);
        checkResumeCanBeFound(resumeIndex, uuid);
        updateInStorage(resumeIndex, resume);
    }
}
