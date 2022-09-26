package com.company.repository;

import com.company.entity.ProfileEntity;
import com.company.rowMapper.ProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepository  {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Optional<ProfileEntity> findByUserId(String userId) {

        String sql = "SELECT id, u_name,lang,user_id,visible, status, phone_number from profile where user_id = '"+userId+"';";

        List<ProfileEntity> users = jdbcTemplate.query(sql, new ProfileRowMapper());

        return users.stream().findAny();
    }

    public void save(ProfileEntity profile){

        String sql = "INSERT INTO profile (user_id,phone_number,visible,status,u_name, lang) " +
                "values( ?, ?, ? ,?, ?, ?);";


        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1, profile.getUserId());
            ps.setString(2, profile.getPhoneNumber());
            ps.setBoolean(3, profile.getVisible());
            ps.setString(4,profile.getStatus().name());
            ps.setString(5, profile.getUsername());
            ps.setString(6, profile.getLanguage().name());
        };
        jdbcTemplate.update(sql, preparedStatementSetter);

    }

    public void update(ProfileEntity profile) {
        System.out.println("profile = " + profile);

        String sql = "UPDATE profile" +
                " SET status = ?, lang = ?, phone_number = ?" +
                " WHERE id = "+profile.getId()+";";


        PreparedStatementSetter preparedStatementSetter = ps -> {
            ps.setString(1,profile.getStatus().name());
            ps.setString(2, profile.getLanguage().name());
            ps.setString(3, profile.getPhoneNumber());
        };
        jdbcTemplate.update(sql, preparedStatementSetter);


    }
}
