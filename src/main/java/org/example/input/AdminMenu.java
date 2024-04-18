package org.example.input;

import org.example.model.Admin;
import org.example.model.User;
import org.example.model.Workout;
import org.example.service.UserService;
import org.example.service.WorkoutService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс представляет из себя интерфейс взаимодействия с администратором
 */
public class AdminMenu {

    /**
     * Ссылка на класс-сервис для доступа администратору к данным пользователей
     */
    private final UserService userService;
    /**
     * Ссылка на текущего администратора
     */
    private final Admin admin;

    public AdminMenu(UserService userService, Admin admin) {
        this.userService = userService;
        this.admin = admin;
        showAdminMenu();
    }

    /**
     * Отображает меню администратора с возможностью выбрать нужный пункт
     */
    public void showAdminMenu() {
        System.out.printf("""
                \nДобрый день, %s!
                1) Просмотр списка пользователей
                2) Просмотр списка тренировок пользователей
                3) Просмотр аудита пользователей
                4) Выход из профиля
                0) Выход из приложения
                """, admin.name());
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> showUserList();
                case 2 -> showUserWorkoutList();
                case 3 -> showAudit();
                case 4 -> StartMenu.showStartMenu();
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("\nОшибка! Выберите корректный пункт!");
                    showAdminMenu();
                }
            }
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("\nОшибка! Выберите корректный пункт!");
            showAdminMenu();
        }
    }

    /**
     * Отображает список зарегестрированных пользователей
     */
    private void showUserList() {
        List<User> userList = userService.getUserMap().values().stream().toList();
        if (userList.isEmpty()) {
            System.out.println("\nСписок пользователей пуст!");
        } else {
            System.out.println("\nСписок пользователей:");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println((i + 1) + ") " + userList.get(i));
            }
        }
        showAdminMenu();
    }

    /**
     * Отображает список тренировок по выбранному пользователю
     *
     * @throws InputMismatchException    Бросает исключение, если в качестве позиции меню введено не целое число
     * @throws IndexOutOfBoundsException Бросает исключение, если введен некорректный номер пользователя
     */
    private void showUserWorkoutList() throws InputMismatchException, IndexOutOfBoundsException {
        List<User> userList = userService.getUserMap().values().stream().toList();
        if (userList.isEmpty()) {
            System.out.println("\nСписок пользователей пуст!");
        } else {
            System.out.println("\nВыберите пользователя:");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println((i + 1) + ") " + userList.get(i));
            }
            int position = new Scanner(System.in).nextInt();
            User user = userList.get(position - 1);
            WorkoutService workoutService = new WorkoutService(user);
            List<Workout> workoutListSortedByDate = workoutService.getWorkoutListSortedByDate();
            if (workoutListSortedByDate.isEmpty()) {
                System.out.println("\nСписок тренировок пользователя " + user.getName() + " пуст!");
            } else {
                System.out.println("\nСписок занесенных тренировок пользователя " + user.getName() + ":");
                for (int i = 0; i < workoutListSortedByDate.size(); i++) {
                    System.out.println((i + 1) + ") " + workoutListSortedByDate.get(i));
                }
            }
        }
        showAdminMenu();
    }

    /**
     * Отображает аудит действий выбранного пользователя
     *
     * @throws InputMismatchException    Бросает исключение, если в качестве позиции меню введено не целое число
     * @throws IndexOutOfBoundsException Бросает исключение, если введен некорректный номер пользователя
     */
    private void showAudit() throws InputMismatchException, IndexOutOfBoundsException {
        List<User> userList = userService.getUserMap().values().stream().toList();
        if (userList.isEmpty()) {
            System.out.println("\nСписок пользователей пуст!");
        } else {
            System.out.println("\nВыберите пользователя:");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println((i + 1) + ") " + userList.get(i));
            }
            int position = new Scanner(System.in).nextInt();
            User user = userList.get(position - 1);
            System.out.println("\nПолный аудит пользователя " + user.getLogin() + ":");
            System.out.print(userService.getAuditService().getAuditByUser(user));
        }
        showAdminMenu();
    }
}
