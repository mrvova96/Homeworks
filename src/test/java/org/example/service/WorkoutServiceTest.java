package org.example.service;

import org.example.model.User;
import org.example.model.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutServiceTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private WorkoutService workoutService;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        User user = new User("123", "123", "Nick", 25);
        workoutService = new WorkoutService(user);
    }

    @Test
    void whenAddAnyWorkoutsThenPrintCorrectAverageWorkoutsTime() {
        workoutService.addWorkout(new Workout("Бег", "11.12.22", 40, 150, ""));
        workoutService.addWorkout(new Workout("Бег", "21.12.22", 25, 100, ""));
        workoutService.showAverageWorkoutsTime();
        assertEquals("Среднее время тренировок: 32.5", outputStream.toString().trim());
    }

    @Test
    void whenAddAnyWorkoutsThenPrintCorrectCountOfCalories() {
        workoutService.addWorkout(new Workout("Бег", "11.12.22", 40, 150, ""));
        workoutService.addWorkout(new Workout("Бег", "21.12.22", 25, 100, ""));
        workoutService.showCountOfCalories();
        assertEquals("Килокалорий потрачено за все тренировки: 250", outputStream.toString().trim());
    }

    @Test
    void whenAddAnyWorkoutsThenPrintCorrectAverageCountOfCalories() {
        workoutService.addWorkout(new Workout("Бег", "11.12.22", 40, 150, ""));
        workoutService.addWorkout(new Workout("Бег", "21.12.22", 25, 100, ""));
        workoutService.showAverageCountOfCalories();
        assertEquals("Килокалорий потрачено за тренировку в среднем: 125.0", outputStream.toString().trim());
    }

    @Test
    void whenWorkoutListIsEmptyThenPrintMessageAboutIt() {
        workoutService.showAverageWorkoutsTime();
        assertEquals("Список тренировок пуст!", outputStream.toString().trim());
    }
}