package ru.javawebinar.topjava;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser {

    private int id;

    private static AuthorizedUser AuthUser = new AuthorizedUser();

    public static AuthorizedUser getId() {
        return AuthUser;
    }

    public static int id() {
        return getId().id;
    }

    public static void setId(int id) {
        AuthUser.id=id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }

}