package org.example.service;

import org.example.model.Admin;
import org.example.output.AdminRepository;
import org.example.output.AdminRepositoryImpl;

/**
 * Класс отвечает за взаимодействие с репозиториями, а также за обработку логики, связанной с администраторами
 */
public class AdminService {

    /**
     * Ссылка на репозиторий администраторов
     */
    private final AdminRepository adminRepository;

    public AdminService() {
        adminRepository = new AdminRepositoryImpl();
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
}
