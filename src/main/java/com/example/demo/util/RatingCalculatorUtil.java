package com.example.demo.util;

public class RatingCalculatorUtil {

    // Calculate average rating
    public static double calculate(
            double school,
            double hospital,
            double transport,
            double safety
    ) {
        return (school + hospital + transport + safety) / 4.0;
    }

    // Convert rating to category
    public static String category(double rating) {
        if (rating >= 4.5) {
            return "EXCELLENT";
        } else if (rating >= 3.5) {
            return "GOOD";
        } else if (rating >= 2.5) {
            return "AVERAGE";
        } else {
            return "POOR";
        }
    }
}
