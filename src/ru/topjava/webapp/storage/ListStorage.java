package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> storage = new ArrayList<>();

    @Override
    protected Object getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void saveByIndex(Object resumeIndex, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected void deleteByIndex(Object resumeIndex) {
        storage.remove((int) resumeIndex);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getFromStorage(Object resumeIndex) {
        return storage.get((int) resumeIndex);
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
    protected void updateInStorage(Object resumeIndex, Resume resume) {
        storage.set((int) resumeIndex, resume);
    }
}
