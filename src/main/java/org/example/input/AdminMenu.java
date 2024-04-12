package org.example.input;

import org.example.model.Admin;
import org.example.model.User;
import org.example.service.AdminService;

import java.util.Scanner;

/**
 * Класс представляет из себя интерфейс взаимодействия с администратором
 */
public class AdminMenu {

    /**
     * Ссылка на класс-сервис для обработки логики выборов администратора
     */
    private final AdminService adminService;
    /**
     * Ссылка на текущего администратора
     */
    private final Admin admin;

    public AdminMenu(Admin admin) {
        adminService = new AdminService();
        this.admin = admin;
        showAdminMenu();
    }

    /**
     * Отображает меню администратора с возможностью выбрать нужный пункт
     */
    public void showAdminMenu() {
        System.out.printf("""
                \nДобрый день, %s!
                1. Просмотр списка пользователей
                2. Просмотр списка тренировок пользователей
                3. Выход из профиля
                0. Выход из приложения
                """, admin.name());
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> showUserList();
                case 2 -> showUserWorkoutList();
                case 3 -> StartMenu.showStartMenu();
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("\nОшибка! Попробуйте еще раз!");
                    showAdminMenu();
                }
            }
        } catch (Exception e) {
            System.out.println("\nОшибка! Попробуйте еще раз!");
            showAdminMenu();
        }
    }

    /**
     * Отображает список зарегестрированных пользователей
     */
    private void showUserList() {
        if (adminService.getUserList().isEmpty())
            System.out.println("\nСписок пользователей пуст!");
        else {
            System.out.println("\nСписок пользователей:");
            for (int i = 0; i < adminService.getUserList().size(); i++)
                System.out.println((i + 1) + ") " + adminService.getUserList().get(i));
        }
        showAdminMenu();
    }

    /**
     * Отображает список тренировок по выбранному пользователю
     */
    private void showUserWorkoutList() {
        if (adminService.getUserList().isEmpty())
            System.out.println("\nСписок пользователей пуст!");
        else {
            System.out.println("\nВыберите пользователя:");
            for (int i = 0; i < adminService.getUserList().size(); i++)
                System.out.println((i + 1) + ") " + adminService.getUserList().get(i));
            int position = new Scanner(System.in).nextInt();
            User user = adminService.getUserList().get(position - 1);
            if (user.getWorkoutList().isEmpty())
                System.out.println("\nСписок тренировок пользователя пуст!");
            else {
                System.out.println("\nСписок занесенных тренировок пользователя:");
                for (int i = 0; i < user.getWorkoutList().size(); i++)
                    System.out.println((i + 1) + ") " + user.getWorkoutList().get(i));
            }
        }
        showAdminMenu();
    }
}
