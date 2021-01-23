package com.rashmi.dao.impl;

import com.rashmi.request.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewUserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rownum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUserName(rs.getString("user_name"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        return user;
    }

}