package com.aleksandrphilimonov.converter;

import com.aleksandrphilimonov.domain.ServiceUser;
import com.aleksandrphilimonov.dto.UserDto;

public class ServiceUserToUserDtoConverter implements Converter<ServiceUser, UserDto>{

    public UserDto convert(ServiceUser source) {
        UserDto target = new UserDto();

        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setName(source.getFirstName() + " " + source.getLastName());
        return target;
    }
}
