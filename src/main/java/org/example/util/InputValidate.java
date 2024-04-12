package org.example.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Вспомогательный класс, который отвечает за валидацию введенных данных
 */
public class InputValidate {

    /**
     * Проверяет строковое представление даты на соответствие паттерну
     * @param date Дата
     * @throws Exception Бросает исключение общего типа, если формат записи даты не соответствует паттерну
     */
    public static void checkInputDate(String date) throws Exception {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yy");
            LocalDate.parse(date, format);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    /**
     * Проверяет переданное число на положительность
     * @param number Целое число
     * @throws Exception Бросает исключение общего типа, если переданное число неположительное
     */
    public static void checkTheNumberIsPositive(int number) throws Exception {
        if (number <= 0)
            throw new Exception();
    }
}
