package org.example.output;

import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-репозиторий, имитирующий запросы в таблицу "Пользователи"
 */
public class UserRepositoryImpl implements UserRepository {

    /**
     * Коллекция для хранения пользователей
     */
    private final List<User> userList;

    public UserRepositoryImpl() {
        userList = new ArrayList<>();
    }

    /**
     * Добавляет нового пользователя в коллекцию
     * @param user Пользователь
     */
    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    /**
     * Возвращает список пользователей
     * @return Список пользователей
     */
    @Override
    public List<User> getUserList() {
        return userList;
    }

    /**
     * Возвращает пользователя по его логину
     * @param login Логин пользователя
     * @return Пользователь
     */
    @Override
    public User getUser(String login) {
        return userList.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst().get();
    }
}
