package ru.topjava.webapp;

import ru.topjava.webapp.model.Resume;
import ru.topjava.webapp.storage.SortedArrayStorage;
import ru.topjava.webapp.storage.Storage;

import java.util.Arrays;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final Storage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1");
        Resume resume3 = new Resume("uuid3");
        Resume resume2 = new Resume("uuid2");
        Resume resume3Update = new Resume("uuid3");
        Resume resume4 = new Resume("uuid4");

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);

        System.out.println("Get resume1: " + ARRAY_STORAGE.get(resume1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        ARRAY_STORAGE.update(resume3Update);
        System.out.println("Update resume3: " + (ARRAY_STORAGE.get("uuid3") == resume3Update));
        System.out.println("Update non-existing resume4");
        ARRAY_STORAGE.update(resume4);

        System.out.println("resume2 index: " + Arrays.binarySearch(ARRAY_STORAGE.getAll(), resume2));
        printAll();
        ARRAY_STORAGE.delete(resume2.getUuid());
        printAll();
        System.out.println("resume2 index: " + Arrays.binarySearch(ARRAY_STORAGE.getAll(), resume2));
        ARRAY_STORAGE.clear();
        printAll();
        System.out.println("resume2 index: " + Arrays.binarySearch(ARRAY_STORAGE.getAll(), resume2));

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
