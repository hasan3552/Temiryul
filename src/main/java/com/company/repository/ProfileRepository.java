package com.company.repository;

import com.company.entity.ProfileEntity;
import com.company.enums.Language;
import com.company.enums.ProfileStatus;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProfileRepository {

    public static final String DB_USERNAME = "higselmbwgerqe";
    public static final String DB_PASSWORD = "de82a1cb482af42f77b41c8c868611600d2f64f57675cf674d08af1f7c05c921";
    public static final String DB_URL = "jdbc:postgresql://ec2-35-170-146-54.compute-1.amazonaws.com:5432/dej1c86jkjt528";
    public static Connection connection;

    public Optional<ProfileEntity> findByUserId(String userId) {

        String sql = "SELECT id, u_name,lang,user_id,visible, status, phone_number from profile where user_id = '" + userId + "';";

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Statement statement = connection.createStatement()) {

            ResultSet resultSet3 = statement.executeQuery(sql);

            while (resultSet3.next()) {

                int id = resultSet3.getInt("id");
                String user_id = resultSet3.getString("user_id");
                String phoneNumber = resultSet3.getString("phone_number");
                String u_name = resultSet3.getString("u_name");
                String status = resultSet3.getString("status");
                String lang = resultSet3.getString("lang");
                boolean visible = resultSet3.getBoolean("visible");

                ProfileEntity profile = new ProfileEntity(id, phoneNumber, user_id, u_name, visible, ProfileStatus.valueOf(status), Language.valueOf(lang));
                return Optional.of(profile);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void save(ProfileEntity profile) {

        String sql = "INSERT INTO profile (user_id,phone_number,visible,status,u_name, lang) " +
                "values( '" + profile.getUserId() + "', '" + profile.getPhoneNumber() + "', " + profile.getVisible() + " " +
                ",'" + profile.getStatus().name() + "', '" + profile.getUsername() + "', '" + profile.getLanguage().name() + "');";

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();

            int i = statement.executeUpdate(sql);
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(ProfileEntity profile) {
        System.out.println("profile = " + profile);

        String sql = "UPDATE profile" +
                " SET status = '" + profile.getStatus().name() + "', lang = '" + profile.getLanguage().name() + "'," +
                " phone_number = '" + profile.getPhoneNumber() + "'" +
                " WHERE id = " + profile.getId() + ";";


    }
}
