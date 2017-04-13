package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
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
        int userId=AuthorizedUser.id();
        service.delete(Id,userId);
    }

    public List<Meal> getAll()
    {
        int userId = AuthorizedUser.id();
        return service.getAll(userId);
    }

    public Meal get(int id){
        int userId = AuthorizedUser.id();
        return service.get(id,userId);
    }

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        return service.save(meal,userId);
    }

    public void update(Meal meal){
        int userId=AuthorizedUser.id();
        service.update(meal,userId);
    }

    public Collection<MealWithExceed> getBetween(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        int userId = AuthorizedUser.id();

        return MealsUtil.getFilteredWithExceeded(
                service.getBetweenDates(
                        startDate != null ? startDate : DateTimeUtil.MIN_DATE,
                        endDate != null ? endDate : DateTimeUtil.MAX_DATE, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay()
        );


    }



}