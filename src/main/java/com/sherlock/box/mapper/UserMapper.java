package com.sherlock.box.mapper;

import com.sherlock.box.dto.UserDTO;
import com.sherlock.box.models.User;

public class UserMapper {

    public static UserDTO toUserDTO(User user){
        return new UserDTO(user.getId(), user.getFirstName(), user.getNumber());
    }
}
