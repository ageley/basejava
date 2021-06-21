package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    protected void saveByKey(Object uuid, Resume resume) {
        storage.put((String) uuid, resume);
    }

    @Override
    protected void deleteByKey(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    protected boolean isResumeExists(Object uuid) {
        return storage.containsKey((String) uuid);
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
