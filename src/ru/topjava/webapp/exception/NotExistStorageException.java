package ru.topjava.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super(String.format("uuid '%1$s' is not exists", uuid), uuid);
    }
}
