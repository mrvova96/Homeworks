package org.example.model;

/**
 * Класс-модель "Администратор"
 *
 * @param login    Логин администратора
 * @param password Пароль администратора
 * @param name     Имя администратора
 * @param age      Возраст администратора
 */
public record Admin(String login, String password, String name, int age) {
}
