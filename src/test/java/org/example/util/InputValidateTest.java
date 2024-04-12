package org.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputValidateTest {

    @Test
    void whenDateIsCorrectFormatThenIsCorrect() {
        assertDoesNotThrow(() -> InputValidate.checkInputDate("10.04.24"));
    }

    @Test
    void whenDateIsIncorrectFormatThenThrowException() {
        assertThrows(Exception.class, () -> InputValidate.checkInputDate("New Date"));
        assertThrows(Exception.class, () -> InputValidate.checkInputDate("2010-12-12"));
    }

    @Test
    void whenInputNumberIsPositiveWhenIsCorrect() {
        assertDoesNotThrow(() -> InputValidate.checkTheNumberIsPositive(10));
    }

    @Test
    void whenInputNumberIsNotPositiveWhenThrowException() {
        assertThrows(Exception.class, () -> InputValidate.checkTheNumberIsPositive(-10));
        assertThrows(Exception.class, () -> InputValidate.checkTheNumberIsPositive(0));
    }
}