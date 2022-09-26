package com.company.rowMapper;

import com.company.entity.ProfileEntity;
import com.company.enums.Language;
import com.company.enums.ProfileStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper implements RowMapper<ProfileEntity> {
    @Override
    public ProfileEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProfileEntity profile = new ProfileEntity();
        profile.setUserId(rs.getString("user_id"));
        profile.setStatus(ProfileStatus.valueOf(rs.getString("status")));
        profile.setPhoneNumber(rs.getString("phone_number"));
        profile.setVisible(rs.getBoolean("visible"));
        profile.setLanguage(Language.valueOf(rs.getString("lang")));
        profile.setUsername(rs.getString("u_name"));
        profile.setId(rs.getInt("id"));

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

        return profile;
    }
}
