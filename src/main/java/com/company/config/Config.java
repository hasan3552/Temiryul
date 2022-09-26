package com.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "com.company")
public class Config {

    @Bean
    public JdbcTemplate jdbcTemplate() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-35-170-146-54.compute-1.amazonaws.com:5432/dej1c86jkjt528");
        dataSource.setUsername("higselmbwgerqe");
        dataSource.setPassword("de82a1cb482af42f77b41c8c868611600d2f64f57675cf674d08af1f7c05c921");

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);

        return jdbcTemplate;
    }

}
