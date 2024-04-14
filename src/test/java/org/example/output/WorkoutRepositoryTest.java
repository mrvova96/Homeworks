package org.example.output;

import org.example.exception.IncorrectDateFormatException;
import org.example.model.User;
import org.example.model.Workout;
import org.example.model.WorkoutType;
import org.example.util.InputValidate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutRepositoryTest {

    private WorkoutRepository workoutRepository;

    @BeforeEach
    void setUp() {
        User user = new User("123", "123", "Nick", 25);
        workoutRepository = new WorkoutRepositoryImpl(user);
    }

    @Test
    void whenAddNewWorkoutThenSizeIsChanging() throws IncorrectDateFormatException {
        LocalDate date = InputValidate.getConvertedDate("12.12.12");
        Workout workout = new Workout(new WorkoutType("type"), date, 0, 0, "");
        workoutRepository.addWorkout(workout);
        assertEquals(1, workoutRepository.getWorkoutList().size());
    }

    @Test
    void whenAddNewWorkoutTypeThenSizeIsChanging() {
        WorkoutType workoutType = new WorkoutType("type");
        workoutRepository.addWorkoutType(workoutType);
        assertEquals(4, workoutRepository.getWorkoutTypeList().size());
    }

    @Test
    void whenRemoveWorkoutThenSizeIsChanging() throws IncorrectDateFormatException {
        LocalDate date = InputValidate.getConvertedDate("12.12.12");
        Workout workout = new Workout(new WorkoutType("type"), date, 0, 0, "");
        workoutRepository.addWorkout(workout);
        assertEquals(1, workoutRepository.getWorkoutList().size());
        workoutRepository.removeWorkout(0);
        assertEquals(0, workoutRepository.getWorkoutList().size());
    }

    @Test
    void whenUpdateWorkoutThenIsCorrect() throws IncorrectDateFormatException {
        LocalDate date = InputValidate.getConvertedDate("12.12.12");
        Workout workout = new Workout(new WorkoutType("type"), date, 0, 0, "");
        Workout newWorkout = new Workout(new WorkoutType("newType"), date, 0, 0, "");
        workoutRepository.addWorkout(workout);
        workoutRepository.updateWorkout(0, newWorkout);
        assertEquals(new WorkoutType("newType"), workoutRepository.getWorkoutList().get(0).workoutType());
    }
}