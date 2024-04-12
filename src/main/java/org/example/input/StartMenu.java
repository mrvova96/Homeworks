package org.example.input;

import org.example.model.User;
import org.example.service.AdminService;
import org.example.service.UserService;
import org.example.util.InputValidate;

import java.util.Scanner;

/**
 * Класс представляет из себя начальный интерфейс взаимодействия при входе в приложение
 */
public class StartMenu {

    /**
     * Ссылка на класс-сервис для обработки логики авторизации и регистрации пользователя
     */
    private static UserService userService;
    /**
     * Ссылка на класс-сервис для обработки логики авторизации администратора
     */
    private static AdminService adminService;

    /**
     * Точка входа в приложение
     */
    public static void main(String[] args) {
        userService = new UserService();
        adminService = new AdminService();
        showStartMenu();
    }

    /**
     * Отображает стартовое меню при входе в приложение
     */
    public static void showStartMenu() {
        System.out.println("""
                \nДобро пожаловать в приложение "Дневник тренировок"!
                1) Авторизация
                2) Регистрация
                0) Выход из приложения""");
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> login();
                case 2 -> register();
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("\nОшибка! Попробуйте еще раз!");
                    showStartMenu();
                }
            }
        } catch (Exception e) {
            System.out.println("\nОшибка! Попробуйте еще раз!");
            showStartMenu();
        }
    }

    /**
     * Выполняет авторизацию пользователей и администраторов
     */
    private static void login() {
        System.out.print("\nВведите логин: ");
        String login = new Scanner(System.in).next();
        System.out.print("Введите пароль: ");
        String password = new Scanner(System.in).next();
        if (adminService.authorizationDataIsCorrect(login, password)) {
            System.out.println("\nВы авторизованы в качестве администратора!");
            new AdminMenu(adminService.getAdmin(login));
        } else if (userService.authorizationDataIsCorrect(login, password)) {
            System.out.println("\nВы успешно авторизованы!");
            new UserMenu(userService.getUser(login));
        } else {
            System.out.println("Логин/пароль введены неверно! Попробуйте еще раз!");
            showStartMenu();
        }
    }

    /**
     * Выполняет регистрацию нового пользователя
     * @throws Exception Бросает исключение общего типа, если введен отрицательный возраст
     */
    private static void register() throws Exception {
        System.out.print("\nВведите логин: ");
        String login = new Scanner(System.in).next();
        if (userService.registrationDataIsCorrect(login)) {
            System.out.print("Введите пароль: ");
            String password = new Scanner(System.in).next();
            System.out.print("Введите ваше имя: ");
            String name = new Scanner(System.in).next();
            System.out.print("Введите ваш возраст: ");
            int age = new Scanner(System.in).nextInt();
            InputValidate.checkTheNumberIsPositive(age);
            userService.addUser(new User(login, password, name, age));
            System.out.println("\nВы успешно зарегистрированы!");
            new UserMenu(userService.getUser(login));
        } else {
            System.out.println("Выбранный вами логин уже занят! Попробуйте еще раз!");
            showStartMenu();
        }
    }
}
