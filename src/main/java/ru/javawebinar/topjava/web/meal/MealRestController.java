package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public List<MealWithExceed> getWithExeed()
    {
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()),MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void delete(int Id)
    {
        int userId=1;
        service.delete(Id,userId);
    }

    public Collection<Meal> getAll()
    {
        int userId = AuthorizedUser.id();
        return service.getAll(userId);
    }





}