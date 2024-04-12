package org.example.output;

import org.example.model.Admin;

import java.util.List;

/**
 * Шаблон для репозитория администраторов
 */
public interface AdminRepository {

    /**
     * Возвращает список администраторов
     * @return Список администраторов
     */
    List<Admin> getAdminList();

    /**
     * Возвращает администратора по его логину
     * @param login Логин администратора
     * @return Администратор
     */
    Admin getAdmin(String login);
}
