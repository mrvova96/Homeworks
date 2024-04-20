package org.example.output;

import org.example.model.User;

import java.util.Map;

/**
 * Шаблон для репозитория пользователей
 */
public interface UserRepository {

    /**
     * Добавляет нового пользователя
     *
     * @param user Пользователь
     */
    void addUser(User user);

    /**
     * Возвращает список пользователей
     *
     * @return Список пользователей
     */
    Map<String, User> getUserMap();

    /**
     * Возвращает пользователя по его логину
     *
     * @param login Логин пользователя
     * @return Пользователь
     */
    User getUser(String login);
}
