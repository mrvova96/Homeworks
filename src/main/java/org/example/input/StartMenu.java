package org.example.input;

import org.example.exception.NegativeValueException;
import org.example.model.User;
import org.example.service.AdminService;
import org.example.service.UserService;
import org.example.util.InputValidate;

import java.util.InputMismatchException;
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
                    System.out.println("\nОшибка! Выберите корректный пункт!");
                    showStartMenu();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("\nОшибка! Выберите корректный пункт!");
            showStartMenu();
        } catch (NegativeValueException e) {
            System.out.println(e.getMessage());
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
        if (adminService.isAuthorizationDataCorrect(login, password)) {
            System.out.println("\nВы авторизованы в качестве администратора!");
            new AdminMenu(userService, adminService.getAdmin(login));
        } else if (userService.isAuthorizationDataCorrect(login, password)) {
            System.out.println("\nВы успешно авторизованы!");
            new UserMenu(userService, userService.getUser(login), false);
        } else {
            System.out.println("\nЛогин/пароль введены неверно! Попробуйте еще раз!");
            showStartMenu();
        }
    }

    /**
     * Выполняет регистрацию нового пользователя
     *
     * @throws InputMismatchException Бросает исключение, если в качестве возраста введено не целое число
     * @throws NegativeValueException Бросает исключение, если введен не положительный возраст
     */
    private static void register() throws InputMismatchException, NegativeValueException {
        System.out.print("\nВведите логин: ");
        String login = new Scanner(System.in).next();
        if (userService.isLoginAvailable(login) && adminService.isLoginAvailable(login)) {
            System.out.print("Введите пароль: ");
            String password = new Scanner(System.in).next();
            System.out.print("Введите ваше имя: ");
            String name = new Scanner(System.in).next();
            System.out.print("Введите ваш возраст: ");
            int age = new Scanner(System.in).nextInt();
            InputValidate.checkTheNumberIsPositive(age);
            userService.addUser(new User(login, password, name, age));
            System.out.println("\nВы успешно зарегистрированы!");
            new UserMenu(userService, userService.getUser(login), true);
        } else {
            System.out.println("\nВыбранный вами логин уже занят! Попробуйте еще раз!");
            showStartMenu();
        }
    }
}
