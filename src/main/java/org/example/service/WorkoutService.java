package org.example.service;

import org.example.model.User;
import org.example.model.Workout;
import org.example.model.WorkoutType;
import org.example.output.WorkoutRepository;
import org.example.output.WorkoutRepositoryImpl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

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
     *
     * @param workout Тренировка
     */
    public void addWorkout(Workout workout) {
        workoutRepository.addWorkout(workout);
    }

    /**
     * Проверяет, была ли проведена в этот день тренировка такого типа
     *
     * @param workoutType Тип тренировки
     * @param date        Дата тренировки
     * @return True, если в этот день была проведена тренировка такого типа, иначе False
     */
    public boolean isWorkoutThisTypeWasOnThisDay(WorkoutType workoutType, LocalDate date) {
        return getWorkoutList().stream()
                .anyMatch(workout -> workout.workoutType().equals(workoutType) && workout.date().equals(date));
    }

    /**
     * Удаляет тренировку по индексу через репозиторий
     *
     * @param index Индекс тренировки
     */
    public void removeWorkout(int index) {
        workoutRepository.removeWorkout(index);
    }

    /**
     * Обновляет тренировку по индексу через репозиторий
     *
     * @param index   Индекс старой тренировки
     * @param workout Новая тренировка
     */
    public void updateWorkout(int index, Workout workout) {
        workoutRepository.updateWorkout(index, workout);
    }

    /**
     * Возвращает список тренировок
     *
     * @return Коллекция тренировок
     */
    public List<Workout> getWorkoutList() {
        return workoutRepository.getWorkoutList();
    }

    /**
     * Возвращает список тренировок, отсортированных по дате
     *
     * @return Коллекция тренировок
     */
    public List<Workout> getWorkoutListSortedByDate() {
        List<Workout> workoutList = getWorkoutList();
        workoutList.sort(Comparator.comparing(Workout::date));
        return workoutList;
    }

    /**
     * Проверяет, существует ли в коллекции указанный тип тренировки
     *
     * @param workoutType Тип тренировки
     * @return True, если такой тип тренировки уже находится в коллекции, иначе False
     */
    public boolean isWorkoutTypeExist(WorkoutType workoutType) {
        return workoutRepository.getWorkoutTypeList().contains(workoutType);
    }

    /**
     * Добавляет новый тип тренировки через репозиторий
     *
     * @param workoutType Тип тренировки
     */
    public void addWorkoutType(WorkoutType workoutType) {
        workoutRepository.addWorkoutType(workoutType);
    }

    /**
     * Возвращает список типов тренировок через репозиторий
     *
     * @return Коллекция типов тренировок
     */
    public List<WorkoutType> getWorkoutTypeList() {
        return workoutRepository.getWorkoutTypeList();
    }

    /**
     * Отображает статистику по общему количеству тренировок определенного типа
     */
    public String getCountOfWorkoutsByTypeMessage(WorkoutType workoutType) {
        StringBuilder message = new StringBuilder();
        long count = getWorkoutList().stream()
                .filter(workout -> workout.workoutType().equals(workoutType))
                .count();
        message.append("\nОбщее количество тренировок типа \"").append(workoutType).append("\": ").append(count);
        return message.toString();
    }

    /**
     * Возвращает статистику по среднему времени выполнения всех тренировок
     */
    public String getAverageWorkoutsTimeMessage() {
        StringBuilder message = new StringBuilder();
        getWorkoutList().stream()
                .mapToInt(Workout::time)
                .average()
                .ifPresentOrElse(
                        avg -> message.append("\nСреднее время тренировок: ").append(avg),
                        () -> message.append("\nСписок тренировок пуст!")
                );
        return message.toString();
    }

    /**
     * Возвращает статистику по общему количеству потраченных килокалорий
     */
    public String getCountOfCaloriesMessage() {
        StringBuilder message = new StringBuilder();
        if (getWorkoutList().isEmpty()) {
            message.append("\nСписок тренировок пуст!");
        } else {
            int kcal = getWorkoutList().stream()
                    .mapToInt(Workout::kcal)
                    .sum();
            message.append("\nКилокалорий потрачено за все тренировки: ").append(kcal);
        }
        return message.toString();
    }

    /**
     * Возвращает статистику по количеству потраченных килокалорий за тренировку в среднем
     */
    public String getAverageCountOfCaloriesMessage() {
        StringBuilder message = new StringBuilder();
        getWorkoutList().stream()
                .mapToInt(Workout::kcal)
                .average()
                .ifPresentOrElse(
                        avg -> message.append("\nКилокалорий потрачено за тренировку в среднем: ").append(avg),
                        () -> message.append("\nСписок тренировок пуст!")
                );
        return message.toString();
    }
}
