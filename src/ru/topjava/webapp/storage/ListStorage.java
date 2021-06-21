package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveByKey(Object resumeKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteByKey(Object resumeKey) {
        storage.remove((int) resumeKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getFromStorage(Object resumeKey) {
        return storage.get((int) resumeKey);
    }

    @Override
    public Resume[] getAll() {
        final Resume[] tempArray = new Resume[this.size()];
        return storage.toArray(tempArray);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void updateInStorage(Object resumeKey, Resume resume) {
        storage.set((int) resumeKey, resume);
    }
}
