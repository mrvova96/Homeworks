package org.example.model;

/**
 * Класс-модель "Тип тренировки"
 *
 * @param type Тип тренировки
 */
public record WorkoutType(String type) {

    @Override
    public String toString() {
        return type;
    }
}
