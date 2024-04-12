package org.example.output;

import org.example.model.User;
import org.example.model.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkoutRepositoryTest {

    private WorkoutRepository workoutRepository;

    @BeforeEach
    void setUp() {
        User user = new User("123", "123", "Nick", 25);
        workoutRepository = new WorkoutRepositoryImpl(user);
    }

    @Test
    void whenAddNewWorkoutThenSizeIsChanging() {
        Workout workout = new Workout("Type", "12.12.12", 0, 0, "");
        workoutRepository.addWorkout(workout);
        assertEquals(1, workoutRepository.getWorkoutList().size());
    }

    @Test
    void whenAddNewWorkoutTypeThenSizeIsChanging() {
        workoutRepository.addWorkoutType("Type");
        assertEquals(4, workoutRepository.getWorkoutTypeList().size());
    }

    @Test
    void whenRemoveWorkoutThenSizeIsChanging() {
        Workout workout = new Workout("Type", "12.12.12", 0, 0, "");
        workoutRepository.addWorkout(workout);
        assertEquals(1, workoutRepository.getWorkoutList().size());
        workoutRepository.removeWorkout(0);
        assertEquals(0, workoutRepository.getWorkoutList().size());
    }

    @Test
    void whenUpdateWorkoutThenIsCorrect() {
        Workout workout = new Workout("Type", "12.12.12", 0, 0, "");
        Workout newWorkout = new Workout("NewType", "12.12.12", 0, 0, "");
        workoutRepository.addWorkout(workout);
        workoutRepository.updateWorkout(0, newWorkout);
        assertEquals("NewType", workoutRepository.getWorkoutList().get(0).type());
    }
}