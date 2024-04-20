package org.example.model;

import java.util.ArrayList;
import java.util.List;

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
    private final List<WorkoutType> workoutTypeList;

    /**
     * @param login    Логин пользователя
     * @param password Пароль пользователя
     * @param name     Имя пользователя
     * @param age      Возраст пользователя
     */
    public User(String login, String password, String name, int age) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.age = age;
        workoutList = new ArrayList<>();
        workoutTypeList = new ArrayList<>();
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

    public List<Workout> getWorkoutList() {
        return workoutList;
    }

    public List<WorkoutType> getWorkoutTypeList() {
        return workoutTypeList;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", возраст: " + age + ", логин: " + login + ", пароль: " + password;
    }
}
