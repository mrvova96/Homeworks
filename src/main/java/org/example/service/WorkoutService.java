package org.example.service;

import org.example.model.User;
import org.example.model.Workout;
import org.example.output.WorkoutRepository;
import org.example.output.WorkoutRepositoryImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Класс отвечает за взаимодействие с репозиторием тренировок, а также за обработку логики, связанной с
 * тренировками пользователей
 */
public class WorkoutService {

    /**
     * Ссылка на репозиторий тренировок
     */
    private final WorkoutRepository workoutRepository;

    public WorkoutService(User user) {
        workoutRepository = new WorkoutRepositoryImpl(user);
    }

    /**
     * Добавляет новую тренировку через репозиторий
     * @param workout Тренировка
     */
    public void addWorkout(Workout workout) {
        workoutRepository.addWorkout(workout);
    }

    /**
     * Удаляет тренировку по индексу через репозиторий
     * @param index Индекс тренировки
     */
    public void removeWorkout(int index) {
        workoutRepository.removeWorkout(index);
    }

    /**
     * Обновляет тренировку по индексу через репозиторий
     * @param index Индекс старой тренировки
     * @param workout Новая тренировка
     */
    public void updateWorkout(int index, Workout workout) {
        workoutRepository.updateWorkout(index, workout);
    }

    /**
     * Возвращает список тренировок через репозиторий
     * @return Коллекция тренировок
     */
    public List<Workout> getWorkoutList() {
        return workoutRepository.getWorkoutList();
    }

    /**
     * Проверяет, существует ли в коллекции указанный тип тренировки
     * @param workoutType Тип тренировки
     * @return True, если такой тип тренировки уже находится в коллекции, иначе False
     */
    public boolean workoutTypeIsExists(String workoutType) {
        return workoutRepository.getWorkoutTypeList().contains(workoutType);
    }

    /**
     * Добавляет новый тип тренировки через репозиторий
     * @param workoutType Тип тренировки
     */
    public void addWorkoutType(String workoutType) {
        workoutRepository.addWorkoutType(workoutType);
    }

    /**
     * Возвращает список типов тренировок через репозиторий
     * @return Коллекция типов тренировок
     */
    public List<String> getWorkoutTypeList() {
        return workoutRepository.getWorkoutTypeList();
    }

    /**
     * Отображает статистику по общему количеству тренировок определенного типа
     */
    public void showCountOfWorkoutsByType() {
        if (getWorkoutList().isEmpty())
            System.out.println("\nСписок тренировок пуст!");
        else {
            System.out.println("\nВыберите тип тренировки:");
            for (int i = 0; i < getWorkoutTypeList().size(); i++)
                System.out.println((i + 1) + ") " + getWorkoutTypeList().get(i));
            int position = new Scanner(System.in).nextInt();
            String type = getWorkoutTypeList().get(position - 1);

            long count = getWorkoutList().stream()
                    .filter(workout -> workout.type().equals(type))
                    .count();
            System.out.printf("\nОбщее количество тренировок типа \"%s\": %d\n", type, count);
        }
    }

    /**
     * Отображает статистику по среднему времени выполнения всех тренировок
     */
    public void showAverageWorkoutsTime() {
        getWorkoutList().stream()
                .mapToInt(Workout::time)
                .average()
                .ifPresentOrElse(
                        avg -> System.out.println("\nСреднее время тренировок: " + avg),
                        () -> System.out.println("\nСписок тренировок пуст!")
                );
    }

    /**
     * Отображает статистику по общему количеству потраченных килокалорий
     */
    public void showCountOfCalories() {
        if (getWorkoutList().isEmpty())
            System.out.println("\nСписок тренировок пуст!");
        else {
            int kcal = getWorkoutList().stream()
                    .mapToInt(Workout::kcal)
                    .sum();
            System.out.println("\nКилокалорий потрачено за все тренировки: " + kcal);
        }
    }

    /**
     * Отображает статистику по количеству потраченных килокалорий за тренировку в среднем
     */
    public void showAverageCountOfCalories() {
        getWorkoutList().stream()
                .mapToInt(Workout::kcal)
                .average()
                .ifPresentOrElse(
                        avg -> System.out.println("\nКилокалорий потрачено за тренировку в среднем: " + avg),
                        () -> System.out.println("\nСписок тренировок пуст!")
                );
    }
}
