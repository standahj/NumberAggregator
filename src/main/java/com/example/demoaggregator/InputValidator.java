package com.example.demoaggregator;

import java.util.ArrayList;
import java.util.List;

public class InputValidator {
    public static List<Double> validateInput(String numberString) {
        List<Double> result = new ArrayList<>();
        if (numberString.length() > 0) {
            String[] numbers = numberString.split(",");
            for (String number : numbers) {
                try {
                    //BigDecimal bd = BigDecimal.valueOf(0d).longValue();
                    double dNumber = Double.parseDouble(number.trim());
                    result.add(dNumber);
                } catch (Exception wrongInput) {
                    // ignore all that is not a number
                }
            }
        }
        return result;
    }
}
