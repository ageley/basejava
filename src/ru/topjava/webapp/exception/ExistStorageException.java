package ru.topjava.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super(String.format("uuid '%1$s' is already exists", uuid), uuid);
    }
}
