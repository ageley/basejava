package ru.topjava.webapp;

import ru.topjava.webapp.model.Resume;
import ru.topjava.webapp.storage.ArrayStorage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume();
        resume1.setUuid("uuid1");
        Resume resume2 = new Resume();
        resume2.setUuid("uuid2");
        Resume resume3 = new Resume();
        resume3.setUuid("uuid3");
        Resume resume3Update = new Resume();
        resume3Update.setUuid("uuid3");
        Resume resume4 = new Resume();
        resume4.setUuid("uuid4");

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

        printAll();
        ARRAY_STORAGE.delete(resume1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
