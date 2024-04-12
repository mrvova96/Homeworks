package org.example.service;

import org.example.model.Admin;
import org.example.model.User;
import org.example.output.AdminRepositoryImpl;
import org.example.output.AdminRepository;
import org.example.output.UserRepository;
import org.example.output.UserRepositoryImpl;

import java.util.List;

/**
 * Класс отвечает за взаимодействие с репозиториями, а также за обработку логики, связанной с администраторами
 */
public class AdminService {

    /**
     * Ссылка на репозиторий администраторов
     */
    private final AdminRepository adminRepository;
    /**
     * Ссылка на репозиторий пользователей
     */
    private final UserRepository userRepository;

    public AdminService() {
        adminRepository = new AdminRepositoryImpl();
        userRepository = new UserRepositoryImpl();
    }

    /**
     * Проверяет корректность введенных данных при авторизации администратора
     * @param login Логин администратора
     * @param password Пароль администратора
     * @return True, если администратор находится в базе, иначе False
     */
    public boolean authorizationDataIsCorrect(String login, String password) {
        return adminRepository.getAdminList().stream()
                .anyMatch(admin -> admin.login().equals(login) && admin.password().equals(password));
    }

    /**
     * Возвращает администратора по его логину через репозиторий
     * @param login Логин администратора
     * @return Администратор
     */
    public Admin getAdmin(String login) {
        return adminRepository.getAdmin(login);
    }

    /**
     * Возвращает список пользователей через репозиторий
     * @return Список пользователей
     */
    public List<User> getUserList() {
        return userRepository.getUserList();
    }
}
