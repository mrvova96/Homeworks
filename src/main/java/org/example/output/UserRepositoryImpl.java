package org.example.output;

import org.example.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс-репозиторий, имитирующий запросы в таблицу "Пользователи"
 */
public class UserRepositoryImpl implements UserRepository {

    /**
     * Коллекция для хранения пользователей
     */
    private final Map<String, User> userMap;

    public UserRepositoryImpl() {
        userMap = new HashMap<>();
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
}
