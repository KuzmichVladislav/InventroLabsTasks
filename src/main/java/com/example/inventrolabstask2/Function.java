package com.example.inventrolabstask2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Задание 2 "Стремится к нулю или к бесконечности?"
 * (n! обозначает factorial(n))
 * Будет ли выражение вида:
 * un = (1 / n!) * (1! + 2! + 3! + ... + n!)
 * стремится к 0 или к бесконечности?
 * Реализуйте функцию, которая раcчитывает значение un для целочисленных n > 1
 * (с точностью не хуже 6 знаков после запятой).
 */

public class Function {

    public String functionUn(double n) {
        double factor = 0;
        double firstFactor = 0;
        double result = 0;
        String resultMessage;
        for (int i = 1; i <= n; i++) {
            factor += factorial(i);
            if (i == 2) {
                firstFactor = factor;
            }
        }
        double firstResult = (1.0 / factorial(2)) * firstFactor;
        result = (1.0 / factorial(n)) * factor;
        BigDecimal bigDecimalResult = new BigDecimal(Double.toString(result));
        bigDecimalResult = bigDecimalResult.setScale(6, RoundingMode.HALF_UP);
        resultMessage = firstResult > result ? "Функция стремиться к нулю u(n) = " + bigDecimalResult
                : "Функция стремиться к бесконечности u(n) = " + bigDecimalResult;
        return resultMessage;
    }

    private double factorial(double n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }
}
