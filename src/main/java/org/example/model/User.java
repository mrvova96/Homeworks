package org.example.model;

import java.util.*;

/**
 * Класс-модель "Пользователь"
 */
public class User {

    private final String login;
    private final String password;
    private final String name;
    private final int age;
    /**
     * Список тренировок пользователя
     */
    private final List<Workout> workoutList;
    /**
     * Список типов тренировок пользователя
     */
    private final List<String> workoutTypeList;

    /**
     * @param login Логин пользователя
     * @param password Пароль пользователя
     * @param name Имя пользователя
     * @param age Возраст пользователя
     */
    public User(String login, String password, String name, int age) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.age = age;
        workoutList = new ArrayList<>();
        workoutTypeList = new ArrayList<>(Arrays.asList("Силовая", "Бег", "Йога"));
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Workout> getWorkoutList() {
        workoutList.sort(Comparator.comparing(Workout::date));
        return workoutList;
    }

    public List<String> getWorkoutTypeList() {
        return workoutTypeList;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", возраст: " + age + ", логин: " + login + ", пароль: " + password;
    }
}