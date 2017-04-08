package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MealRestController {
    private MealService service;


    public List<MealWithExceed> getWithExeed()
    {
        return MealsUtil.getWithExceeded(service.getAll(),MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }



}