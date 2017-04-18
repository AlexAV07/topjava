package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    //private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private static final RowMapper<Meal> ROW_MAPPER = new RowMapper<Meal>() {
        @Override
        public Meal mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Meal(rs.getInt("id"),rs.getTimestamp("datetime").toLocalDateTime(),rs.getString("description"),rs.getInt("calories"));
        }
    };
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert jdbcInsert;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource,JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;

    }


    @Override
    public Meal save(Meal meal, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id",meal.getId())
                .addValue("dateTime", Timestamp.valueOf(meal.getDateTime()))
                .addValue("description",meal.getDescription())
                .addValue("calories",meal.getCalories())
                .addValue("user_id", userId);
        if (meal.isNew())
        {
            Number newId = jdbcInsert.executeAndReturnKey(map);
            meal.setId(newId.intValue());
        }
        else {
            if (namedParameterJdbcTemplate.update("update meals SET datetime=:dateTime, description=:description,"+
                    " calories=:calories WHERE id=:id AND user_id=:user_id",map)==0)
            {
                return null;
            }
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("delete from meals WHERE id=:? AND user_id=:?",id,userId)!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("select * from meals WHERE id=? AND user_id=:?ORDER BY datetime DESC ",ROW_MAPPER,id,userId);
        return CollectionUtils.isEmpty(meals)? null:DataAccessUtils.requiredSingleResult(meals);
        //return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return jdbcTemplate.query("select * from meals WHERE user_id=? ORDER BY datetime DESC ",ROW_MAPPER,userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
