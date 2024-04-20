package org.example.exception;

/**
 * Вспомогательный класс-Exception для проверки целых чисел на неотрицательность
 */
public class NegativeValueException extends Exception {
    public NegativeValueException(String message) {
        super(message);
    }
}
