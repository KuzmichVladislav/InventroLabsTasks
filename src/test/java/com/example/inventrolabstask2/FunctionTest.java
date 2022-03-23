package com.example.inventrolabstask2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FunctionTest {
    Function function = new Function();

    @Test
    void testFunctionUn() {
        String result = function.functionUn(4);
        Assertions.assertEquals("Функция стремиться к нулю u(n) = 1.375000", result);
    }
}
