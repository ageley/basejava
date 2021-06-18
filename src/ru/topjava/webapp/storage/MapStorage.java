package ru.topjava.webapp.storage;

import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.NotExistStorageException;
import ru.topjava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected void saveByIndex(Object uuid, Resume resume) {
        storage.put((String) uuid, resume);
    }

    @Override
    protected void deleteByIndex(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    protected Object findIndexIfResumeNotExist(String uuid) {
        if (storage.containsKey(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return uuid;
    }

    @Override
    protected Object findIndexIfResumeExist(String uuid) {
        if (!storage.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return uuid;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getFromStorage(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    public Resume[] getAll() {
        final Resume[] tempArray = new Resume[this.size()];
        return storage.values().toArray(tempArray);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void updateInStorage(Object uuid, Resume resume) {
        storage.replace((String) uuid, resume);
    }
}
