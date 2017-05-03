package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("delete from Meal m where m.id=:id and m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);


    @Override
    List<Meal> findAll(Sort sort);

    @Transactional
    @Override
    Meal save(Meal meal);

    @Override
    Meal findOne(Integer integer);

    @Override
    @Query()
    List<Meal> findAll();


}


