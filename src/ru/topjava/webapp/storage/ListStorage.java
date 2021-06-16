package ru.topjava.webapp.storage;

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
    public void clear() {
        storage.clear();
    }

    @Override
    protected Resume getFromStorage(int resumeIndex) {
        return storage.get(resumeIndex);
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
    protected void updateInStorage(int resumeIndex, Resume resume) {
        storage.set(resumeIndex, resume);
    }
}
