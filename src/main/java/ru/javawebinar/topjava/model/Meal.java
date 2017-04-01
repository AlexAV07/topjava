package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final int mealId;

    public Meal(LocalDateTime dateTime, String description, int calories,int mealId) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.mealId=mealId;
    }

    public int getMealId() {
        return mealId;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
