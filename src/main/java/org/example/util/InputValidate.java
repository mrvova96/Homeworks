package org.example.util;

import org.example.exception.IncorrectDateFormatException;
import org.example.exception.NegativeValueException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Вспомогательный класс, который отвечает за валидацию введенных данных
 */
public class InputValidate {

    /**
     * Возвращает дату в формате LocalDate из строкового представления
     *
     * @param date Дата в формате строки
     * @return Дата в формате LocalDate
     * @throws IncorrectDateFormatException Бросает исключение, если переданная дата не соответствует паттерну
     */
    public static LocalDate getConvertedDate(String date) throws IncorrectDateFormatException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            throw new IncorrectDateFormatException("\nВведенная дата не соответствует нужному формату!");
        }
    }

    /**
     * Проверяет переданное число на неотрицательность
     *
     * @param number Целое число
     * @throws NegativeValueException Бросает исключение, если переданное число не положительное
     */
    public static void checkTheNumberIsPositive(int number) throws NegativeValueException {
        if (number <= 0) {
            throw new NegativeValueException("\nДанное значение должно быть положительным!");
        }
    }
}
