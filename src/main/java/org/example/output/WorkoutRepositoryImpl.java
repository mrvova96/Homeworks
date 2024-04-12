package org.example.output;

import org.example.model.User;
import org.example.model.Workout;

import java.util.Comparator;
import java.util.List;

/**
 * Класс, имитирующий запросы в таблицу "Тренировки"
 */
public class WorkoutRepositoryImpl implements WorkoutRepository {

    /**
     * Коллекция для хранения тренировок пользователя
     */
    private final List<Workout> workoutList;
    /**
     * Коллекция для хранения типов тренировок пользователя
     */
    private final List<String> workoutTypeList;

    public WorkoutRepositoryImpl(User user) {
        workoutList = user.getWorkoutList();
        workoutTypeList = user.getWorkoutTypeList();
    }

    /**
     * Добавляет новую тренировку в коллекцию
     * @param workout Тренировка
     */
    @Override
    public void addWorkout(Workout workout) {
        workoutList.add(workout);
    }

    /**
     * Удаляет тренировку из коллекции по индексу
     * @param index Индекс тренировки
     */
    @Override
    public void removeWorkout(int index) {
        workoutList.remove(index);
    }

    /**
     * Обновляет тренировку из коллекции по индексу
     * @param index Индекс старой тренировки
     * @param workout Новая тренировка
     */
    @Override
    public void updateWorkout(int index, Workout workout) {
        workoutList.set(index, workout);
    }

    /**
     * Возвращает список тренировок, отсортированных по дате
     * @return Коллекция тренировок
     */
    @Override
    public List<Workout> getWorkoutList() {
        workoutList.sort(Comparator.comparing(Workout::date));
        return workoutList;
    }

    /**
     * Добавляет новый тип тренировки
     * @param workoutType Тип тренировки
     */
    @Override
    public void addWorkoutType(String workoutType) {
        workoutTypeList.add(workoutType);
    }

    /**
     * Возвращает список типов тренировок
     * @return Коллекция типов тренировок
     */
    @Override
    public List<String> getWorkoutTypeList() {
        return workoutTypeList;
    }
}