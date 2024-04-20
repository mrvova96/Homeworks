package org.example.input;

import org.example.exception.IncorrectDateFormatException;
import org.example.exception.NegativeValueException;
import org.example.model.User;
import org.example.model.Workout;
import org.example.model.WorkoutType;
import org.example.service.UserService;
import org.example.service.WorkoutService;
import org.example.util.InputValidate;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Класс представляет из себя интерфейс взаимодействия с пользователем
 */
public class UserMenu {

    /**
     * Ссылка на класс-сервис для обработки логики, связанной с тренировками пользователей
     */
    private final WorkoutService workoutService;
    /**
     * Ссылка на класс-сервис для обработки логики, связанной с сохранением аудита пользователей
     */
    private final UserService userService;
    /**
     * Ссылка на текущего пользователя
     */
    private final User user;

    /**
     * Конструктор класса
     *
     * @param userService  Ссылка на класс-сервис UserService
     * @param user         Текущий пользователь
     * @param isRegistered Параметр используется для записи в аудит данных о регистрации, или об авторизации,
     *                     в замисимости от того, зарегистрировался пользователь впервые или авторизовался
     */
    public UserMenu(UserService userService, User user, boolean isRegistered) {
        this.userService = userService;
        this.user = user;
        workoutService = new WorkoutService(user);
        userService.getAuditService().addLoginInfoInAudit(user, isRegistered);
        showUserMenu();
    }

    /**
     * Отображает меню пользователя с возможностью выбрать нужный пункт
     */
    public void showUserMenu() {
        System.out.printf("""
                \nДобрый день, %s!
                1) Добавление тренировки
                2) Удаление тренировки
                3) Редактирование тренировки
                4) Просмотр списка тренировок, отсортированных по дате
                5) Просмотр списка типов тренировок
                6) Добавление нового типа тренировки
                7) Просмотр статистики
                8) Выход из профиля
                0) Выход из приложения
                """, user.getName());
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> addWorkout();
                case 2 -> removeWorkout();
                case 3 -> updateWorkout();
                case 4 -> showWorkoutList();
                case 5 -> showWorkoutTypeList();
                case 6 -> addWorkoutType();
                case 7 -> showStatistic();
                case 8 -> logout();
                case 0 -> exit();
                default -> {
                    System.out.println("\nОшибка! Выберите корректный пункт!");
                    showUserMenu();
                }
            }
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("\nОшибка! Выберите корректный пункт!");
            showUserMenu();
        } catch (NegativeValueException | IncorrectDateFormatException e) {
            System.out.println(e.getMessage());
            showUserMenu();
        }
    }

    /**
     * Добавляет новую тренировку по данным из консоли
     *
     * @throws InputMismatchException       Бросает исключение, если в качестве длительности тренировки, количества
     *                                      килокалорий или позиции типа тренировки введено не целое число
     * @throws IndexOutOfBoundsException    Бросает исключение, если введен некорректный номер типа тренировки
     * @throws NegativeValueException       Бросает исключение, если введены не положительные количество
     *                                      килокалорий или длительность тренировки
     * @throws IncorrectDateFormatException Бросает исключение, если введенная дата не соответствует формату
     */
    private void addWorkout() throws InputMismatchException, IndexOutOfBoundsException, NegativeValueException,
            IncorrectDateFormatException {
        System.out.println("\nВыберите тип тренировки:");
        List<WorkoutType> workoutTypeList = workoutService.getWorkoutTypeList();
        for (int i = 0; i < workoutTypeList.size(); i++) {
            System.out.println((i + 1) + ") " + workoutTypeList.get(i));
        }
        System.out.println(0 + ") Назад");
        int position = new Scanner(System.in).nextInt();
        if (position == 0) {
            showUserMenu();
            return;
        }
        WorkoutType type = workoutTypeList.get(position - 1);

        System.out.print("Введите дату тренировки (в формате DD.MM.YY): ");
        String dateString = new Scanner(System.in).next();
        LocalDate date = InputValidate.getConvertedDate(dateString);
        if (date.isAfter(LocalDate.now())) {
            System.out.println("\nВведенный вами день еще не наступил!");
            showUserMenu();
            return;
        }
        if (workoutService.isWorkoutThisTypeWasOnThisDay(type, date)) {
            System.out.println("\nВ этот день уже была проведена тренировка такого типа!");
            showUserMenu();
            return;
        }

        System.out.print("Введите длительность тренировки: ");
        int time = new Scanner(System.in).nextInt();
        InputValidate.checkTheNumberIsPositive(time);

        System.out.print("Введите количество потраченных килокалорий: ");
        int kcal = new Scanner(System.in).nextInt();
        InputValidate.checkTheNumberIsPositive(kcal);

        System.out.println("Введите дополнительную информацию о тренировке (в противном случае введите \"-\"):");
        String info = new Scanner(System.in).nextLine();

        Workout workout = new Workout(type, date, time, kcal, info);
        workoutService.addWorkout(workout);
        System.out.println("\nТренировка была успешно добавлена!");
        userService.getAuditService().addAddingWorkoutInAudit(user, workout);
        showUserMenu();
    }

    /**
     * Удаляет выбранную тренировку из общего списка
     *
     * @throws InputMismatchException    Бросает исключение, если в качестве позиции тренировки введено не целое число
     * @throws IndexOutOfBoundsException Бросает исключение, если введен некорректный номер тренировки
     */
    private void removeWorkout() throws InputMismatchException, IndexOutOfBoundsException {
        System.out.println("\nКакую тренировку необходимо удалить?");
        List<Workout> workoutList = workoutService.getWorkoutList();
        for (int i = 0; i < workoutList.size(); i++) {
            System.out.println((i + 1) + ") " + workoutList.get(i));
        }
        System.out.println(0 + ") Назад");
        int position = new Scanner(System.in).nextInt();
        if (position == 0) {
            showUserMenu();
            return;
        }
        Workout removedWorkout = workoutService.getWorkoutList().get(position - 1);
        workoutService.removeWorkout(position - 1);
        System.out.println("\nТренировка была успешно удалена!");
        userService.getAuditService().addRemovingWorkoutInAudit(user, removedWorkout);
        showUserMenu();
    }

    /**
     * Обновляет выбранную тренировку из общего списка
     *
     * @throws InputMismatchException       Бросает исключение, если в качестве длительности тренировки, количества
     *                                      килокалорий, позиции тренировки или позиции типа тренировки введено не
     *                                      целое число
     * @throws IndexOutOfBoundsException    Бросает исключение, если введен некорректный номер тренировки или номер
     *                                      типа тренировки
     * @throws NegativeValueException       Бросает исключение, если введены не положительные количество
     *                                      килокалорий или длительность тренировки
     * @throws IncorrectDateFormatException Бросает исключение, если введенная дата не соответствует формату
     */
    private void updateWorkout() throws InputMismatchException, IndexOutOfBoundsException, NegativeValueException,
            IncorrectDateFormatException {
        System.out.println("\nКакую тренировку необходимо отредактировать?");
        List<Workout> workoutList = workoutService.getWorkoutList();
        for (int i = 0; i < workoutList.size(); i++) {
            System.out.println((i + 1) + ") " + workoutList.get(i));
        }
        System.out.println(0 + ") Назад");
        int position = new Scanner(System.in).nextInt();
        if (position == 0) {
            showUserMenu();
            return;
        }
        Workout workout = workoutList.get(position - 1);

        System.out.println("Выберите тип тренировки (\"0\" чтобы не менять):");
        List<WorkoutType> workoutTypeList = workoutService.getWorkoutTypeList();
        for (int i = 0; i < workoutTypeList.size(); i++) {
            System.out.println((i + 1) + ") " + workoutTypeList.get(i));
        }
        int typePosition = new Scanner(System.in).nextInt();
        WorkoutType type = (typePosition == 0) ? workout.workoutType() : workoutTypeList.get(typePosition - 1);

        System.out.print("Введите дату тренировки (в формате DD.MM.YY) (\"0\" чтобы не менять): ");
        String dateString = new Scanner(System.in).next();
        LocalDate date = (dateString.equals("0")) ? workout.date() : InputValidate.getConvertedDate(dateString);
        if (date.isAfter(LocalDate.now())) {
            System.out.println("\nВведенный вами день еще не наступил!");
            showUserMenu();
            return;
        }
        if (workoutService.isWorkoutThisTypeWasOnThisDay(type, date)) {
            System.out.println("\nВ этот день уже была проведена тренировка такого типа!");
            showUserMenu();
            return;
        }

        System.out.print("Введите длительность тренировки (\"0\" чтобы не менять): ");
        int time = new Scanner(System.in).nextInt();
        time = (time == 0) ? workout.time() : time;
        InputValidate.checkTheNumberIsPositive(time);

        System.out.print("Введите количество потраченных килокалорий (\"0\" чтобы не менять): ");
        int kcal = new Scanner(System.in).nextInt();
        kcal = (kcal == 0) ? workout.kcal() : kcal;
        InputValidate.checkTheNumberIsPositive(kcal);

        System.out.println("Введите дополнительную информацию о тренировке (\"0\" чтобы не менять, \"-\" чтобы удалить):");
        String info = new Scanner(System.in).nextLine();
        info = (info.equals("0")) ? workout.info() : info;

        Workout newWorkout = new Workout(type, date, time, kcal, info);
        workoutService.updateWorkout(position - 1, newWorkout);
        System.out.println("\nТренировка была успешно отредактирована!");
        userService.getAuditService().addUpdatingWorkoutInAudit(user, workout);
        showUserMenu();
    }

    /**
     * Отображает список тренировок текущего пользователя, отсортированных по дате
     */
    private void showWorkoutList() {
        List<Workout> workoutListSortedByDate = workoutService.getWorkoutListSortedByDate();
        if (workoutListSortedByDate.isEmpty()) {
            System.out.println("\nСписок тренировок пуст!");
        } else {
            System.out.println("\nСписок занесенных тренировок:");
            for (int i = 0; i < workoutListSortedByDate.size(); i++) {
                System.out.println((i + 1) + ") " + workoutListSortedByDate.get(i));
            }
        }
        userService.getAuditService().addViewingWorkoutListInAudit(user);
        showUserMenu();
    }

    /**
     * Отображает список типов тренировок текущего пользователя
     */
    private void showWorkoutTypeList() {
        System.out.println("\nСписок занесенных типов тренировок:");
        List<WorkoutType> workoutTypeList = workoutService.getWorkoutTypeList();
        for (int i = 0; i < workoutTypeList.size(); i++) {
            System.out.println((i + 1) + ") " + workoutTypeList.get(i));
        }
        userService.getAuditService().addViewingWorkoutTypeListInAudit(user);
        showUserMenu();
    }

    /**
     * Добавляет новый тип тренировки
     */
    private void addWorkoutType() {
        System.out.print("\nВведите новый тип тренировки: ");
        String type = new Scanner(System.in).next();
        WorkoutType workoutType = new WorkoutType(type);
        if (workoutService.isWorkoutTypeExist(workoutType)) {
            System.out.println("\nДанный тип тренировки уже существует!");
        } else {
            workoutService.addWorkoutType(workoutType);
            System.out.println("\nНовый тип тренировки был успешно добавлен!");
            userService.getAuditService().addAddingWorkoutTypeInAudit(user, workoutType);
        }
        showUserMenu();
    }

    /**
     * Отображает меню с возможностью вывести нужную статистику
     */
    private void showStatistic() {
        System.out.println("""
                \nКакую статистику необходимо отобразить?
                1) Общее количество тренировок определенного типа
                2) Среднее время всех тренировок
                3) Общее количество потраченных килокалорий
                4) Количество потраченных килокалорий за тренировку в среднем
                0) Назад""");
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1 -> showCountOfWorkoutsByType();
                case 2 -> showAverageWorkoutsTime();
                case 3 -> showCountOfCalories();
                case 4 -> showAverageCountOfCalories();
                case 0 -> showUserMenu();
                default -> {
                    System.out.println("\nОшибка! Выберите корректный пункт!");
                    showStatistic();
                }
            }
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("\nОшибка! Выберите корректный пункт!");
            showStatistic();
        }
    }

    /**
     * Отображает статистику по общему количеству тренировок определенного типа
     *
     * @throws InputMismatchException    Бросает исключение, если в качестве позиции типа тренировки введено не
     *                                   целое число
     * @throws IndexOutOfBoundsException Бросает исключение, если введен некорректный номер типа тренировки
     */
    private void showCountOfWorkoutsByType() throws InputMismatchException, IndexOutOfBoundsException {
        if (workoutService.getWorkoutList().isEmpty()) {
            System.out.println("\nСписок тренировок пуст!");
        } else {
            System.out.println("\nВыберите тип тренировки:");
            List<WorkoutType> workoutTypeList = workoutService.getWorkoutTypeList();
            for (int i = 0; i < workoutTypeList.size(); i++) {
                System.out.println((i + 1) + ") " + workoutTypeList.get(i));
            }
            int position = new Scanner(System.in).nextInt();
            WorkoutType workoutType = workoutTypeList.get(position - 1);
            System.out.println(workoutService.getCountOfWorkoutsByTypeMessage(workoutType));
            userService.getAuditService().addViewingCountOfWorkoutsByTypeInAudit(user, workoutType);
        }
        showStatistic();
    }

    /**
     * Отображает статистику по среднему времени выполнения всех тренировок
     */
    private void showAverageWorkoutsTime() {
        System.out.println(workoutService.getAverageWorkoutsTimeMessage());
        userService.getAuditService().addViewingAverageWorkoutsTimeInAudit(user);
        showStatistic();
    }

    /**
     * Отображает статистику по общему количеству потраченных килокалорий
     */
    private void showCountOfCalories() {
        System.out.println(workoutService.getCountOfCaloriesMessage());
        userService.getAuditService().addViewingCountOfCaloriesInAudit(user);
        showStatistic();
    }

    /**
     * Отображает статистику по количеству потраченных килокалорий за тренировку в среднем
     */
    private void showAverageCountOfCalories() {
        System.out.println(workoutService.getAverageCountOfCaloriesMessage());
        userService.getAuditService().addViewingAverageCountOfCaloriesInAudit(user);
        showStatistic();
    }

    /**
     * Производит выход из профиля
     */
    private void logout() {
        userService.getAuditService().addLogoutInfoInAudit(user);
        StartMenu.showStartMenu();
    }

    /**
     * Производит выход из приложения
     */
    private void exit() {
        userService.getAuditService().addExitInfoInAudit(user);
        System.exit(0);
    }
}
