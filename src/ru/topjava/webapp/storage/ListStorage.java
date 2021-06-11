package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveByIndex(int resumeIndex, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteByIndex(int resumeIndex) {
        storage.remove(resumeIndex);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected void saveToStorage(int resumeIndex, Resume resume) {
        saveByIndex(resumeIndex, resume);
    }

    @Override
    protected Resume getFromStorage(int resumeIndex) {
        return storage.get(resumeIndex);
    }

    @Override
    protected void deleteFromStorage(int resumeIndex) {
        deleteByIndex(resumeIndex);
    }

    @Override
    protected Resume[] getAllFromStorage() {
        final Resume[] tempArray = new Resume[this.size()];
        return storage.toArray(tempArray);
    }

    @Override
    protected int getSizeOfStorage() {
        return storage.size();
    }

    @Override
    protected void updateInStorage(int resumeIndex, Resume resume) {
        storage.set(resumeIndex, resume);
    }
}
