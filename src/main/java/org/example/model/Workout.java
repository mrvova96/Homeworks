package org.example.model;

/**
 * Класс-модель "Тренировка"
 * @param type Тип тренировки
 * @param date Дата тренировки
 * @param time Время тренировки
 * @param kcal Количество потраченных килокалорий
 * @param info Дополнительная информация о тренировке
 */
public record Workout(String type, String date, int time, int kcal, String info) {

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Тип тренировки: " + type +
                "\n   Дата тренировки: " + date +
                "\n   Время тренировки: " + time +
                "\n   Потраченные килокалории: " + kcal
        );
        if (!info.equals("-"))
            builder.append("\n   Дополнительная информация: ").append(info);
        return builder.toString();
    }
}
