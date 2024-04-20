package org.example.output;

import org.example.model.Admin;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс-репозиторий, имитирующий запросы в таблицу "Администраторы"
 */
public class AdminRepositoryImpl implements AdminRepository {

    /**
     * Коллекция для хранения администраторов (в рамках приложения администратор только один)
     */
    private final Map<String, Admin> adminMap;

    public AdminRepositoryImpl() {
        adminMap = new HashMap<>();
        addAdmin(new Admin("admin", "admin", "Владимир", 27));
    }

    /**
     * Добавляет нового администратора в коллекцию
     *
     * @param admin Администратор
     */
    @Override
    public void addAdmin(Admin admin) {
        adminMap.put(admin.login(), admin);
    }

    /**
     * Возвращает копию коллекции администраторов
     *
     * @return Коллекция администраторов
     */
    @Override
    public Map<String, Admin> getAdminMap() {
        return new HashMap<>(adminMap);
    }

    /**
     * Возвращает администратора по его логину
     *
     * @param login Логин администратора
     * @return Администратор
     */
    @Override
    public Admin getAdmin(String login) {
        return adminMap.get(login);
    }
}
