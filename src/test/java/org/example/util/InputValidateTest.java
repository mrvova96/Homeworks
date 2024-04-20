package org.example.util;

import org.example.exception.IncorrectDateFormatException;
import org.example.exception.NegativeValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputValidateTest {

    @Test
    void whenDateIsCorrectFormatThenIsCorrect() {
        assertDoesNotThrow(() -> InputValidate.getConvertedDate("10.04.24"));
    }

    @Test
    void whenDateIsIncorrectFormatThenThrowException() {
        assertThrows(IncorrectDateFormatException.class, () -> InputValidate.getConvertedDate("New Date"));
    }

    @Test
    void whenInputNumberIsPositiveWhenIsCorrect() {
        assertDoesNotThrow(() -> InputValidate.checkTheNumberIsPositive(10));
    }

    @Test
    void whenInputNumberIsNotPositiveWhenThrowException() {
        assertThrows(NegativeValueException.class, () -> InputValidate.checkTheNumberIsPositive(-10));
    }
}