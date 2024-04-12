package org.example.input;

import org.example.model.User;
import org.example.model.Workout;
import org.example.service.WorkoutService;
import org.example.util.InputValidate;

import java.util.Scanner;

/**
 * Класс представляет из себя интерфейс взаимодействия с пользователем
 */
public class UserMenu {

    /**
     * Ссылка на класс-сервис для обработки логики выборов пользователя
     */
    private final WorkoutService workoutService;
    /**
     * Ссылка на текущего пользователя
     */
    private final User user;

    public UserMenu(User user) {
        workoutService = new WorkoutService(user);
        this.user = user;
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
                case 8 -> StartMenu.showStartMenu();
                case 0 -> System.exit(0);
                default -> {
                    System.out.println("\nОшибка! Попробуйте еще раз!");
                    showUserMenu();
                }
            }
        } catch (Exception e) {
            System.out.println("\nОшибка! Попробуйте еще раз!");
            showUserMenu();
        }
    }

    /**
     * Добавляет новую тренировку по данным из консоли
     * @throws Exception Бросает исключение общего типа, если выбран некорректный пункт меню, если введено
     * отрицательное значение времени или килокалорий, или если дата была введена некорректно
     */
    private void addWorkout() throws Exception {
        System.out.println("\nВыберите тип тренировки:");
        for (int i = 0; i < workoutService.getWorkoutTypeList().size(); i++)
            System.out.println((i + 1) + ") " + workoutService.getWorkoutTypeList().get(i));
        System.out.println(0 + ") Назад");
        int position = new Scanner(System.in).nextInt();
        if (position == 0) {
            showUserMenu();
            return;
        }
        String type = workoutService.getWorkoutTypeList().get(position - 1);

        System.out.print("Введите дату тренировки (в формате DD.MM.YY): ");
        String date = new Scanner(System.in).next();
        InputValidate.checkInputDate(date);

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
        showUserMenu();
    }

    /**
     * Удаляет выбранную тренировку из общего списка
     */
    private void removeWorkout() {
        System.out.println("\nКакую тренировку необходимо удалить?");
        for (int i = 0; i < workoutService.getWorkoutList().size(); i++)
            System.out.println((i + 1) + ") " + workoutService.getWorkoutList().get(i));
        System.out.println(0 + ") Назад");
        int position = new Scanner(System.in).nextInt();
        if (position == 0) {
            showUserMenu();
            return;
        }
        workoutService.removeWorkout(position - 1);
        System.out.println("\nТренировка была успешно удалена!");
        showUserMenu();
    }

    /**
     * Обновляет выбранную тренировку из общего списка
     * @throws Exception Бросает исключение общего типа, если выбран некорректный пункт меню, если введено
     * отрицательное значение времени или килокалорий, или если дата была введена некорректно
     */
    private void updateWorkout() throws Exception {
        System.out.println("\nКакую тренировку необходимо отредактировать?");
        for (int i = 0; i < workoutService.getWorkoutList().size(); i++)
            System.out.println((i + 1) + ") " + workoutService.getWorkoutList().get(i));
        System.out.println(0 + ") Назад");
        int position = new Scanner(System.in).nextInt();
        if (position == 0) {
            showUserMenu();
            return;
        }
        Workout workout = workoutService.getWorkoutList().get(position - 1);

        System.out.println("Выберите тип тренировки (\"0\" чтобы не менять):");
        for (int i = 0; i < workoutService.getWorkoutTypeList().size(); i++)
            System.out.println((i + 1) + ") " + workoutService.getWorkoutTypeList().get(i));
        int typeNumber = new Scanner(System.in).nextInt();
        String type = (typeNumber == 0) ? workout.type() : workoutService.getWorkoutTypeList().get(typeNumber - 1);

        System.out.print("Введите дату тренировки (\"0\" чтобы не менять): ");
        String date = new Scanner(System.in).next();
        date = (date.equals("0")) ? workout.date() : date;
        InputValidate.checkInputDate(date);

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
        showUserMenu();
    }

    /**
     * Отображает список тренировок текущего пользователя, отсортированных по дате
     */
    private void showWorkoutList() {
        if (workoutService.getWorkoutList().isEmpty())
            System.out.println("\nСписок тренировок пуст!");
        else {
            System.out.println("\nСписок занесенных тренировок:");
            for (int i = 0; i < workoutService.getWorkoutList().size(); i++)
                System.out.println((i + 1) + ") " + workoutService.getWorkoutList().get(i));
        }
        showUserMenu();
    }

    /**
     * Отображает список типов тренировок текущего пользователя
     */
    private void showWorkoutTypeList() {
        System.out.println("\nСписок занесенных типов тренировок:");
        for (int i = 0; i < workoutService.getWorkoutTypeList().size(); i++)
            System.out.println((i + 1) + ") " + workoutService.getWorkoutTypeList().get(i));
        showUserMenu();
    }

    /**
     * Добавляет новый тип тренировки
     */
    private void addWorkoutType() {
        System.out.print("\nВведите новый тип тренировки: ");
        String type = new Scanner(System.in).next();
        if (workoutService.workoutTypeIsExists(type))
            System.out.println("Данный тип тренировки уже существует!");
        else {
            workoutService.addWorkoutType(type);
            System.out.println("Новый тип тренировки был успешно добавлен!");
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
                    System.out.println("\nОшибка! Попробуйте еще раз!");
                    showUserMenu();
                }
            }
        } catch (Exception e) {
            System.out.println("\nОшибка! Попробуйте еще раз!");
            showUserMenu();
        }
    }

    /**
     * Отображает статистику по общему количеству тренировок определенного типа
     */
    private void showCountOfWorkoutsByType() {
        workoutService.showCountOfWorkoutsByType();
        showStatistic();
    }

    /**
     * Отображает статистику по среднему времени выполнения всех тренировок
     */
    private void showAverageWorkoutsTime() {
        workoutService.showAverageWorkoutsTime();
        showStatistic();
    }

    /**
     * Отображает статистику по общему количеству потраченных килокалорий
     */
    private void showCountOfCalories() {
        workoutService.showCountOfCalories();
        showStatistic();
    }

    /**
     * Отображает статистику по количеству потраченных килокалорий за тренировку в среднем
     */
    private void showAverageCountOfCalories() {
        workoutService.showAverageCountOfCalories();
        showStatistic();
    }
}
