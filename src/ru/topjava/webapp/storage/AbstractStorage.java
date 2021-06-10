package ru.topjava.webapp.storage;

import ru.topjava.webapp.model.Resume;

import java.util.ArrayList;

public abstract class AbstractStorage implements Storage {

    protected abstract int getIndex(String uuid);

    protected abstract void saveByIndex(int resumeIndex, Resume resume);

    protected abstract void deleteByIndex(int resumeIndex);

}
