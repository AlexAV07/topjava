package ru.javawebinar.topjava.dao;

/**
 * Created by Alexander on 01.04.2017.
 */

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MealsDao {
    public static List<Meal> mealList = new ArrayList<Meal>();

    static {
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, MealsUtil.getMealIdNext()));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, MealsUtil.getMealIdNext()));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, MealsUtil.getMealIdNext()));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, MealsUtil.getMealIdNext()));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, MealsUtil.getMealIdNext()));
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, MealsUtil.getMealIdNext()));
    }

    public static List<Meal> getAllMeal()
    {
        return mealList;
    }

    public static  void addMeal(Meal meal)
    {
        mealList.add(meal);
    }
}
