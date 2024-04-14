package org.example.output;

import org.example.model.Admin;

import java.util.Map;

/**
 * Шаблон для репозитория администраторов
 */
public interface AdminRepository {

    /**
     * Добавляет нового администратора
     *
     * @param admin Администратор
     */
    void addAdmin(Admin admin);

    /**
     * Возвращает список администраторов
     *
     * @return Список администраторов
     */
    Map<String, Admin> getAdminMap();

    /**
     * Возвращает администратора по его логину
     *
     * @param login Логин администратора
     * @return Администратор
     */
    Admin getAdmin(String login);
}
