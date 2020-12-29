package ru.topjava.webapp;

import ru.topjava.webapp.model.Resume;
import ru.topjava.webapp.storage.ArrayStorage;
import ru.topjava.webapp.storage.SortedArrayStorage;
import ru.topjava.webapp.storage.Storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Interactive test for ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainArray {
    private static Storage arrayStorage;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume resume;
        String[] params;
        storageChoice: while (true) {
            System.out.print("Выберите тип хранилища - (sort | unsort | exit): ");
            params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            switch (params[0]) {
                case "sort":
                    System.out.println("Выбранный тип хранилища: отсортированный массив.");
                    arrayStorage = new SortedArrayStorage();
                    break storageChoice;
                case "unsort":
                    System.out.println("Выбранный тип хранилища: неотсортированный массив.");
                    arrayStorage = new ArrayStorage();
                    break storageChoice;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
        while (true) {
            System.out.print("Введите одну из команд - (list | save uuid | update uuid | delete uuid | get uuid | clear | exit): ");
            params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(arrayStorage.size());
                    break;
                case "save":
                    resume = new Resume(uuid);
                    arrayStorage.save(resume);
                    printAll();
                    break;
                case "update":
                    resume = new Resume(uuid);
                    arrayStorage.update(resume);
                    printAll();
                    break;
                case "delete":
                    arrayStorage.delete(uuid);
                    printAll();
                    break;
                case "get":
                    System.out.println(arrayStorage.get(uuid));
                    break;
                case "clear":
                    arrayStorage.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        Resume[] all = arrayStorage.getAll();
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume resume : all) {
                System.out.println(resume);
            }
        }
        System.out.println("----------------------------");
    }
}