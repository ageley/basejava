/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = this.size();
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int size = this.size();
        if (r.uuid != null) {
            storage[size] = r;
        }
    }

    Resume get(String uuid) {
        int size = this.size();
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    return storage[i];
                }
            }
        }
        return null;
    }

    void delete(String uuid) {
        int size = this.size();
        if (uuid != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    for (int k = i; k < size; k++) {
                        storage[k] = storage[k+1];
                    }
                    break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = this.size();
        Resume[] storageAll = new Resume[size];
        for (int i = 0; i < size; i++) {
            storageAll[i] = storage[i];
        }
        return storageAll;
    }

    int size() {
        int size = storage.length;
        for (int i = 0; i < size; i++) {
            if (storage[i] == null) {
                size = i;
            }
        }
        return size;
    }
}
