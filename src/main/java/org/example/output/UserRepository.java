package org.example.output;

import org.example.model.User;

import java.util.List;
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

    /**
     * Добавляет новое сообщение в аудит для указанного пользователя
     *
     * @param user    Пользователь
     * @param message Сообщение о некоторой активности
     */
    void addMessageInAudit(User user, String message);

    /**
     * Возвращает аудит по указанному пользователю
     *
     * @param user Пользователь
     * @return Аудит действий пользователя
     */
    List<String> getAuditListByUser(User user);
}
