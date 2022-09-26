package com.company.rowMapper;

import com.company.entity.CalculateEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.CategoryStatus;
import com.company.enums.Language;
import com.company.enums.ProfileStatus;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalcRowMapper implements RowMapper<CalculateEntity> {

    @Override
    public CalculateEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        CalculateEntity calculate = new CalculateEntity();
        calculate.setProfileId(rs.getInt("profile_id"));
        calculate.setStatus(CategoryStatus.valueOf(rs.getString("status")));
        calculate.setCategoryId(rs.getInt("category_id"));
        calculate.setValue1(rs.getDouble("value1"));
        calculate.setValue2(rs.getDouble("value2"));
        calculate.setValue3(rs.getDouble("value3"));
        calculate.setId(rs.getInt("id"));

//        sale.setSize(rs.getString("s_s"));
//        sale.setAmount(rs.getInt("s_a"));
//        sale.setPrice(rs.getDouble("s_p"));
//        sale.setWhen(rs.getTimestamp("s_w").toLocalDateTime());
//
//        product.setName(rs.getString("p_n"));
//        product.setDesign(rs.getString("p_d"));
//        product.setColour(rs.getString("p_c"));
//        product.setPon(rs.getString("p_p"));
//
//        user.setFullName(rs.getString("u_f"));
//
//        sale.setUser(user);
//        sale.setProduct(product);

        return calculate;
    }
}
