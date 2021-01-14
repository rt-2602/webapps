package com.rashmi.dao.impl;

import com.rashmi.NewUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewUserRowMapper implements RowMapper<NewUser> {

    public NewUser mapRow(ResultSet rs, int rownum) throws SQLException {
        NewUser newUser = new NewUser();
        newUser.setId(rs.getInt("id"));
        newUser.setUserName(rs.getString("user_name"));
        newUser.setPassword(rs.getString("password"));
        newUser.setFirstName(rs.getString("first_name"));
        newUser.setLastName(rs.getString("last_name"));
        return newUser;
    }

}