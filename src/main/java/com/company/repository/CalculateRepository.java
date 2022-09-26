package com.company.repository;

import com.company.entity.CalculateEntity;
import com.company.enums.CategoryStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CalculateRepository {

    public static final String DB_USERNAME = "higselmbwgerqe";
    public static final String DB_PASSWORD = "de82a1cb482af42f77b41c8c868611600d2f64f57675cf674d08af1f7c05c921";
    public static final String DB_URL = "jdbc:postgresql://ec2-35-170-146-54.compute-1.amazonaws.com:5432/dej1c86jkjt528";
    public static Connection connection;


    public Optional<CalculateEntity> getCalculate(CalculateEntity calculate){

        List<CalculateEntity> calculateEntities = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();

//            String ADD_CUSTOMER = "INSERT INTO " +
//                    "customer(id, username, phone_number, language, hobby_id, gender, status, role) \nVALUES (" +
//                    +user.getId() + ", '" + user.getUsername() + "', null, null, null ,null,'NEW','REGISTER' );";

            String query = "SELECT * FROM calc WHERE profile_id = "+calculate.getProfileId()
                    +" AND status = '"+calculate.getStatus()+"' AND category_id = "+calculate.getCategoryId()+" ;";

            ResultSet resultSet = statement.executeQuery(query);
            connection.close();

//            List<CalculateEntity> users = jdbcTemplate.query(query, new CalcRowMapper());

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int toId = resultSet.getInt("category_id");
                double value1 = resultSet.getDouble("value1");
                double value2 = resultSet.getDouble("value2");
                double value3 = resultSet.getDouble("value3");
                String status = resultSet.getString("status");
                int profile_id = resultSet.getInt("profile_id");

                CalculateEntity calculate1 = new CalculateEntity(id,toId,value1,value2,value3, CategoryStatus.valueOf(status),profile_id);
                calculateEntities.add(calculate1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(calculateEntities.get(0));

    }

    public void save(CalculateEntity calculate) {

        String query = "INSERT INTO calc (profile_id,status,category_id, value1, value2, value3)" +
                "values("+calculate.getProfileId()+",'"+calculate.getStatus().name()+"',"+calculate.getCategoryId()
                +", "+calculate.getValue1()+", "+calculate.getValue2()+", "+calculate.getValue3()+");";

//        PreparedStatementSetter preparedStatementSetter = ps -> {
//            ps.setInt(1, calculate.getProfileId());
//            ps.setString(2, calculate.getStatus().name());
//            ps.setInt(3, calculate.getCategoryId());
//            ps.setDouble(4,calculate.getValue1());
//            ps.setDouble(5,calculate.getValue2());
//            ps.setDouble(6,calculate.getValue3());
//        };
//
//        jdbcTemplate.update(query, preparedStatementSetter);


        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();


            int i = statement.executeUpdate(query);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
