package org.example.output;

import org.example.model.Admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс-репозиторий, имитирующий запросы в таблицу "Администраторы"
 */
public class AdminRepositoryImpl implements AdminRepository {

    /**
     * Коллекция для хранения администраторов (в рамках приложения администратор только один)
     */
    private final List<Admin> adminList;

    public AdminRepositoryImpl() {
        adminList = new ArrayList<>(List.of(new Admin("admin", "admin", "Владимир", 27)));
    }

    /**
     * Возвращает список администраторов
     * @return Список администраторов
     */
    @Override
    public List<Admin> getAdminList() {
        return adminList;
    }

    /**
     * Возвращает администратора по его логину
     * @param login Логин администратора
     * @return Администратор
     */
    @Override
    public Admin getAdmin(String login) {
        return adminList.stream()
                .filter(admin2 -> admin2.login().equals(login))
                .findFirst()
                .get();
    }
}
