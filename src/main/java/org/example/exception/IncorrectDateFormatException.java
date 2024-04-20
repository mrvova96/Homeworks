package org.example.exception;

/**
 * Вспомогательный класс-Exception для проверки корректности формата даты
 */
public class IncorrectDateFormatException extends Exception {
    public IncorrectDateFormatException(String message) {
        super(message);
    }
}
