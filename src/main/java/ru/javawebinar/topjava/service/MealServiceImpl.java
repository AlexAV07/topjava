package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

public class MealServiceImpl implements MealService {

    private MealRepository repository;
    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        if (repository.get(id).getUserId()== AuthorizedUser.id()) {
            repository.delete(id);
        }
        else throw new NotFoundException("Not owner for meal");
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        if (repository.get(id).getUserId()== AuthorizedUser.id()) {
            return repository.get(id);
        }
        else throw new NotFoundException("Not owner for meal");

    }

    @Override
    public Collection<Meal> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);

    }


}