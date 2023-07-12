package com.aleksandrphilimonov.service;

import com.aleksandrphilimonov.converter.ServiceUserToUserDtoConverter;
import com.aleksandrphilimonov.dao.ServiceUserDao;
import com.aleksandrphilimonov.domain.ServiceUser;
import com.aleksandrphilimonov.dto.UserDto;
import com.aleksandrphilimonov.exception.CommonServiceException;

public class SecurityService {
    private final ServiceUserDao serviceUserDao;
    private final DigestService digestService;
    private final ServiceUserToUserDtoConverter converter;

    public SecurityService(ServiceUserDao serviceUserDao, DigestService digestService, ServiceUserToUserDtoConverter convert) {
        this.serviceUserDao = serviceUserDao;
        this.digestService = digestService;
        this.converter = convert;
    }

    public UserDto auth(String email, String password) throws CommonServiceException {
        ServiceUser serviceUser = serviceUserDao.findByEmail(email);

        if (serviceUser != null) {
            String passwordHash = digestService.hash(password);

            if (passwordHash.equals(serviceUser.getPassword())) {
                return converter.convert(serviceUser);
            }
        }
        return null;
    }
}
