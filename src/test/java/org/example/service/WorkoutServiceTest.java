package org.example.service;

import org.example.exception.IncorrectDateFormatException;
import org.example.model.User;
import org.example.model.Workout;
import org.example.model.WorkoutType;
import org.example.util.InputValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkoutServiceTest {

    private WorkoutService workoutService;

    @BeforeEach
    void setUp() throws IncorrectDateFormatException {
        User user = new User("123", "123", "Nick", 25);
        workoutService = new WorkoutService(user);
        LocalDate date1 = InputValidate.getConvertedDate("12.12.12");
        LocalDate date2 = InputValidate.getConvertedDate("21.12.12");
        workoutService.addWorkout(new Workout(new WorkoutType("type"), date1, 40, 150, ""));
        workoutService.addWorkout(new Workout(new WorkoutType("type"), date2, 25, 100, ""));
    }

    @Test
    void whenAddWorkoutSameTypeOnSameDayThenReturnTrue() throws IncorrectDateFormatException {
        LocalDate date = InputValidate.getConvertedDate("12.12.12");
        assertTrue(workoutService.isWorkoutThisTypeWasOnThisDay(new WorkoutType("type"), date));
    }

    @Test
    void whenAddAnyWorkoutsThenGetCorrectAverageWorkoutsTime() {
        String message = workoutService.getAverageWorkoutsTimeMessage();
        assertEquals("Среднее время тренировок: 32.5", message.trim());
    }

    @Test
    void whenAddAnyWorkoutsThenGetCorrectCountOfCalories() {
        String message = workoutService.getCountOfCaloriesMessage();
        assertEquals("Килокалорий потрачено за все тренировки: 250", message.trim());
    }

    @Test
    void whenAddAnyWorkoutsThenGetCorrectAverageCountOfCalories() {
        String message = workoutService.getAverageCountOfCaloriesMessage();
        assertEquals("Килокалорий потрачено за тренировку в среднем: 125.0", message.trim());
    }
}