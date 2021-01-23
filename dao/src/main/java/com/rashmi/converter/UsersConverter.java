package com.rashmi.converter;

import com.rashmi.entity.UsersEntity;
import com.rashmi.request.User;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public UsersEntity convertVOtoDO(User user){

        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setFirstName(user.getFirstName());
        usersEntity.setLastName(user.getLastName());
        usersEntity.setUserName(user.getUserName());
        usersEntity.setPassword(user.getPassword());
        return usersEntity;

    }

    public User convertDOtoVO(UsersEntity usersEntity){

        User user = new User();
        user.setFirstName(usersEntity.getFirstName());
        user.setLastName(usersEntity.getLastName());
        user.setPassword(usersEntity.getPassword());
        user.setUserName(usersEntity.getUserName());
        return user;
    }


}
