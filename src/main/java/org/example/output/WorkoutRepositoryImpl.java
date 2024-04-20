package org.example.output;

import org.example.model.User;
import org.example.model.Workout;
import org.example.model.WorkoutType;

import java.util.ArrayList;
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
    private final List<WorkoutType> workoutTypeList;

    public WorkoutRepositoryImpl(User user) {
        workoutList = user.getWorkoutList();
        workoutTypeList = user.getWorkoutTypeList();
        if (workoutTypeList.isEmpty()) {
            addWorkoutType(new WorkoutType("Бег"));
            addWorkoutType(new WorkoutType("Силовая"));
            addWorkoutType(new WorkoutType("Йога"));
        }
    }

    /**
     * Добавляет новую тренировку в коллекцию
     *
     * @param workout Тренировка
     */
    @Override
    public void addWorkout(Workout workout) {
        workoutList.add(workout);
    }

    /**
     * Удаляет тренировку из коллекции по индексу
     *
     * @param index Индекс тренировки
     */
    @Override
    public void removeWorkout(int index) {
        workoutList.remove(index);
    }

    /**
     * Обновляет тренировку из коллекции по индексу
     *
     * @param index   Индекс старой тренировки
     * @param workout Новая тренировка
     */
    @Override
    public void updateWorkout(int index, Workout workout) {
        workoutList.set(index, workout);
    }

    /**
     * Возвращает копию коллекции тренировок
     *
     * @return Коллекция тренировок
     */
    @Override
    public List<Workout> getWorkoutList() {
        return new ArrayList<>(workoutList);
    }

    /**
     * Добавляет новый тип тренировки
     *
     * @param workoutType Тип тренировки
     */
    @Override
    public void addWorkoutType(WorkoutType workoutType) {
        workoutTypeList.add(workoutType);
    }

    /**
     * Возвращает копию коллекции типов тренировок
     *
     * @return Коллекция типов тренировок
     */
    @Override
    public List<WorkoutType> getWorkoutTypeList() {
        return new ArrayList<>(workoutTypeList);
    }
}
