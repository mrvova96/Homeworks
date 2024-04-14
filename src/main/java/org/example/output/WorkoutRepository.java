package org.example.output;

import org.example.model.Workout;
import org.example.model.WorkoutType;

import java.util.List;

/**
 * Шаблон для репозитория тренировок
 */
public interface WorkoutRepository {

    /**
     * Добавляет новую тренировку
     *
     * @param workout Тренировка
     */
    void addWorkout(Workout workout);

    /**
     * Удаляет тренировку по индексу
     *
     * @param index Индекс тренировки
     */
    void removeWorkout(int index);

    /**
     * Обновляет тренировку по индексу
     *
     * @param index   Индекс старой тренировки
     * @param workout Новая тренировка
     */
    void updateWorkout(int index, Workout workout);

    /**
     * Возвращает список тренировок
     *
     * @return Коллекция тренировок
     */
    List<Workout> getWorkoutList();

    /**
     * Добавляет новый тип тренировки
     *
     * @param workoutType Тип тренировки
     */
    void addWorkoutType(WorkoutType workoutType);

    /**
     * Возвращает список типов тренировок
     *
     * @return Коллекция типов тренировок
     */
    List<WorkoutType> getWorkoutTypeList();
}
