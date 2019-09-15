package stu.napls.ciprogsolu;


import stu.napls.ciprogsolu.config.Config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class App {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName(Config.PROBLEM);
            Object object = clazz.newInstance();
            Method method = clazz.getMethod(Config.QUESTION);
            method.invoke(object);
        } catch (ClassNotFoundException e) {
            System.out.println("No solution for this Problem.");
        } catch (NoSuchMethodException e) {
            System.out.println("No solution for this Question.");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
