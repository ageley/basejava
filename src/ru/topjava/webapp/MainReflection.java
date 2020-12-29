package ru.topjava.webapp;

import ru.topjava.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume("test_reflection");
        Method toStringMethod = resume.getClass().getMethod("toString");
        System.out.println(toStringMethod.invoke(resume));
    }
}
