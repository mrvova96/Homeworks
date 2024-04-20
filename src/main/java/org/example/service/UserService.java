package org.example.service;

import org.example.model.User;
import org.example.output.UserRepository;
import org.example.output.UserRepositoryImpl;

import java.util.Map;

/**
 * Класс отвечает за взаимодействие с репозиторием пользователей, а также за обработку логики, связанной с
 * пользователями
 */
public class UserService {

    /**
     * Ссылка на репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * Ссылка на сервис аудитов
     */
    private final AuditService auditService;

    public UserService() {
        userRepository = new UserRepositoryImpl();
        auditService = new AuditService();
    }

    /**
     * Проверяет корректность введенных данных при авторизации пользователя
     *
     * @param login    Логин пользователя
     * @param password Пароль пользователя
     * @return True, если пользователь находится в базе, иначе False
     */
    public boolean isAuthorizationDataCorrect(String login, String password) {
        User user = getUser(login);
        if (user == null) {
            return false;
        } else {
            return user.getPassword().equals(password);
        }
    }

    /**
     * Проверяет доступность логина по базе пользователей
     *
     * @param login Логин пользователя
     * @return True, если выбранный логин свободен, иначе False
     */
    public boolean isLoginAvailable(String login) {
        return getUser(login) == null;
    }

    /**
     * Добавляет нового пользователя через репозиторий
     *
     * @param user Пользователь
     */
    public void addUser(User user) {
        userRepository.addUser(user);
    }

    /**
     * Возвращает пользователя по его логину через репозиторий
     *
     * @param login Логин пользователя
     * @return Пользователь
     */
    public User getUser(String login) {
        return userRepository.getUser(login);
    }

    /**
     * Возвращает список пользователей через репозиторий
     *
     * @return Список пользователей
     */
    public Map<String, User> getUserMap() {
        return userRepository.getUserMap();
    }

    /**
     * Геттер на сервис аудитов
     *
     * @return Сервис аудитов
     */
    public AuditService getAuditService() {
        return auditService;
    }
}
