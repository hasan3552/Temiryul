package com.company.repository;

import com.company.entity.ProfileEntity;
import com.company.enums.Language;
import com.company.enums.ProfileStatus;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProfileRepository {

//    public static final String DB_USERNAME = "dkvtodzlyatwel";
//    public static final String DB_PASSWORD = "9c7a272365b6450301f73e091193d0e0a2fda28f78b73cb0476b997baa957b73";
//    public static final String DB_URL = "jdbc:postgresql://ec2-63-32-248-14.eu-west-1.compute.amazonaws.com:5432/d325g5rfibvhk0";


    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "hasan";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/train";

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
                System.out.println("profile =00 " + profile);
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

        String sql = "UPDATE profile" +
                " SET status = '" + profile.getStatus().name() + "', lang = '" + profile.getLanguage().name() + "'," +
                " phone_number = '" + profile.getPhoneNumber() + "'" +
                " WHERE id = " + profile.getId() + ";";

        try (Statement statement = connection.createStatement()) {


            int i = statement.executeUpdate(sql);

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
