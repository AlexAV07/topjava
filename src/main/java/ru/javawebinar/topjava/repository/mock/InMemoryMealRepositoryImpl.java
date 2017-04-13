package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.MealServlet;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final Logger LOG = LoggerFactory.getLogger(MealServlet.class);
    public static final Comparator<Meal> USER_MEAL_COMPARATOR = (m1, m2) ->m2.getDateTime().compareTo(m1.getDateTime());

    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        //MealsUtil.MEALS.forEach((meal) -> save(meal,AuthorizedUser.id() ));
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),USER_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),ADMIN_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),ADMIN_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 550),USER_ID);

    }

    @Override
    public Meal save(Meal meal, int userId) {
        LOG.info("Save meal {}",meal);
        Integer mealId = meal.getId();
        if (meal.isNew()) {
            mealId=counter.incrementAndGet();
            meal.setId(mealId);

        }
        meal.setUserId(userId);

        LOG.info("Save2 meal {}",meal);
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id) {

        return  repository.remove(id)!=null;


    }
    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        Collection<Meal> list = repository.values();
        //return list;

        return list.stream()
                .filter((m)->m.getUserId()==userId)
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(),startDate,endDate))
                .sorted(USER_MEAL_COMPARATOR)
                .collect(Collectors.toList());

    }



}

