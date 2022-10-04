package com.company.repository;

import com.company.entity.CalculateEntity;
import com.company.enums.CategoryStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CalculateRepository {

//    public static final String DB_USERNAME = "dkvtodzlyatwel";
//    public static final String DB_PASSWORD = "9c7a272365b6450301f73e091193d0e0a2fda28f78b73cb0476b997baa957b73";
//    public static final String DB_URL = "jdbc:postgresql://ec2-63-32-248-14.eu-west-1.compute.amazonaws.com:5432/d325g5rfibvhk0";

    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "hasan";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/train";

    public static Connection connection;


    public Optional<CalculateEntity> getCalculate(CalculateEntity calculate) {

        List<CalculateEntity> calculateEntities = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();

//            String ADD_CUSTOMER = "INSERT INTO " +
//                    "customer(id, username, phone_number, language, hobby_id, gender, status, role) \nVALUES (" +
//                    +user.getId() + ", '" + user.getUsername() + "', null, null, null ,null,'NEW','REGISTER' );";

            String query = "SELECT * FROM calc WHERE profile_id = " + calculate.getProfileId()
                    + " AND status = '" + calculate.getStatus() + "' AND category_id = " + calculate.getCategoryId() + " ;";

            ResultSet resultSet = statement.executeQuery(query);
            connection.close();

//            List<CalculateEntity> users = jdbcTemplate.query(query, new CalcRowMapper());

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                int toId = resultSet.getInt("category_id");
                double value1 = resultSet.getDouble("value1");
                double value2 = resultSet.getDouble("value2");
                double value3 = resultSet.getDouble("value3");
                double value4 = resultSet.getDouble("value4");
                double value5 = resultSet.getDouble("value5");
                String status = resultSet.getString("status");
                int profile_id = resultSet.getInt("profile_id");

                CalculateEntity calculate1 = new CalculateEntity(id, toId, value1, value2, value3,value4,value5, CategoryStatus.valueOf(status), profile_id);
                calculateEntities.add(calculate1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(calculateEntities.get(0));

    }

    public void save(CalculateEntity calculate) {

        String query = "INSERT INTO calc(profile_id,status,category_id, value1, value2, value3, value4, value5) " +
                " values(" + calculate.getProfileId() + " , " +
                "'" + calculate.getStatus().name() + "' , " +
                 + calculate.getCategoryId() + " , " +
                calculate.getValue1() + " , " +
                calculate.getValue2() + " , " +
                calculate.getValue3() + " , " +
                calculate.getValue4() + " , " +
                calculate.getValue5() + ");";

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();

            int i = statement.executeUpdate(query);
            System.out.println(i);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
