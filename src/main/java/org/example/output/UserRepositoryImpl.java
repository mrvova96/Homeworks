package org.example.output;

import org.example.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс-репозиторий, имитирующий запросы в таблицу "Пользователи"
 */
public class UserRepositoryImpl implements UserRepository {

    /**
     * Коллекция для хранения пользователей
     */
    private final Map<String, User> userMap;
    /**
     * Коллекция для хранения аудита пользователей
     */
    private final Map<User, List<String>> auditMap;

    public UserRepositoryImpl() {
        userMap = new HashMap<>();
        auditMap = new HashMap<>();
    }

    /**
     * Добавляет нового пользователя в коллекцию
     *
     * @param user Пользователь
     */
    @Override
    public void addUser(User user) {
        userMap.put(user.getLogin(), user);
    }

    /**
     * Возвращает копию коллекции пользователей
     *
     * @return Коллекция пользователей
     */
    @Override
    public Map<String, User> getUserMap() {
        return new HashMap<>(userMap);
    }

    /**
     * Возвращает пользователя по его логину
     *
     * @param login Логин пользователя
     * @return Пользователь
     */
    @Override
    public User getUser(String login) {
        return userMap.get(login);
    }

    /**
     * Добавляет новое сообщение в аудит для указанного пользователя
     *
     * @param user    Пользователь
     * @param message Сообщение о некоторой активности
     */
    @Override
    public void addMessageInAudit(User user, String message) {
        if (!auditMap.containsKey(user)) {
            auditMap.put(user, new ArrayList<>());
        }
        auditMap.get(user).add(message);
    }

    /**
     * Возвращает аудит по указанному пользователю
     *
     * @param user Пользователь
     * @return Аудит действий пользователя
     */
    @Override
    public List<String> getAuditListByUser(User user) {
        return auditMap.get(user);
    }
}
