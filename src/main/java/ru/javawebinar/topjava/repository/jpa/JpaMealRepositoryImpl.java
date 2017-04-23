package ru.javawebinar.topjava.repository.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class JpaMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew()) {
            em.persist(meal);
            LOG.info("new meal {} for user {}",meal.getId(),ref.getId());
        }
        else {
            LOG.info("update meal {} for user {}",meal.getId(),ref.getId());
            if (get(meal.getId(),userId)==null) return null;
            em.merge(meal);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id",id).setParameter("user_id",userId).executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = em.createNamedQuery(Meal.GET,Meal.class)
                .setParameter("id",id)
                .setParameter("user_id",userId).getResultList();
        return meals.size()==0? null: DataAccessUtils.requiredSingleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.GET_ALL, Meal.class).setParameter(1,userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class).setParameter(1,userId).setParameter("start",startDate).setParameter("end",endDate).getResultList();
    }
}