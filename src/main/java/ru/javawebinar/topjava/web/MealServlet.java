package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

import static org.slf4j.LoggerFactory.getLogger;
/**
 * Created by Alexander on 01.04.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(UserServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forvard to mealList");

        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("new"))
        {
            forward="/mealForm.jsp";
        }
        else {
            List<Meal> meals = MealsDao.getAllMeal();
            List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(meals,LocalTime.MIN,LocalTime.MAX, 2000);
            request.setAttribute("MealList",mealWithExceeds);
            forward="/meals.jsp";
        }

        request.getRequestDispatcher(forward).forward(request,response);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Meal meal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0),req.getParameter("mealName"),Integer.parseInt(req.getParameter("calories")),MealsUtil.getMealIdNext());

        MealsDao.addMeal(meal);

        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(MealsDao.getAllMeal(),LocalTime.MIN,LocalTime.MAX, 2000);
        req.setAttribute("MealList",mealWithExceeds);

        req.getRequestDispatcher("/meals.jsp").forward(req,resp);
    }
}
