package org.example.service;

import org.example.model.User;
import org.example.output.UserRepository;
import org.example.output.UserRepositoryImpl;

/**
 * Класс отвечает за взаимодействие с репозиторием пользователей, а также за обработку логики, связанной с
 * пользователями
 */
public class UserService {

    /**
     * Ссылка на репозиторий пользователей
     */
    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepositoryImpl();
    }

    /**
     * Проверяет корректность введенных данных при авторизации пользователя
     * @param login Логин пользователя
     * @param password Пароль пользователя
     * @return True, если пользователь находится в базе, иначе False
     */
    public boolean authorizationDataIsCorrect(String login, String password) {
        return userRepository.getUserList().stream()
                .anyMatch(user -> user.getLogin().equals(login) && user.getPassword().equals(password));
    }

    /**
     * Проверяет корректность введенных данных при регистрации пользователя
     * @param login Логин пользователя
     * @return True, если выбранный логин свободен, иначе False
     */
    public boolean registrationDataIsCorrect(String login) {
        return userRepository.getUserList().stream()
                .noneMatch(user -> user.getLogin().equals(login));
    }

    /**
     * Добавляет нового пользователя через репозиторий
     * @param user Пользователь
     */
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    /**
     * Возвращает пользователя по его логину через репозиторий
     * @param login Логин пользователя
     * @return Пользователь
     */
    public User getUser(String login) {
        return userRepository.getUser(login);
    }
}
