package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean exceed;
    private final int mealId;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed,int mealId) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.mealId=mealId;
    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                ", mealId=" + mealId +
                '}';
    }

    public boolean isExceed() {
        return exceed;
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

    public int getMealId() {
        return mealId;
    }
    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
