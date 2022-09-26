package com.company.repository;

import com.company.entity.CalculateEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.CategoryStatus;
import com.company.rowMapper.CalcRowMapper;
import com.company.rowMapper.ProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CalculateRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<CalculateEntity> getCalculate(CalculateEntity calculate){

        String query = "SELECT * FROM calc WHERE profile_id = "+calculate.getProfileId()
                +" AND status = '"+calculate.getStatus()+"' AND category_id = "+calculate.getCategoryId()+" ;";
        List<CalculateEntity> users = jdbcTemplate.query(query, new CalcRowMapper());

        return users.stream().findAny();

    }

    public void save(CalculateEntity calculate) {

        String query = "INSERT INTO calc (profile_id,status,category_id, value1, value2, value3)" +
                "values(?,?,?, ?, ?, ?);";

        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setInt(1, calculate.getProfileId());
            ps.setString(2, calculate.getStatus().name());
            ps.setInt(3, calculate.getCategoryId());
            ps.setDouble(4,calculate.getValue1());
            ps.setDouble(5,calculate.getValue2());
            ps.setDouble(6,calculate.getValue3());
        };

        jdbcTemplate.update(query, preparedStatementSetter);

    }
}
