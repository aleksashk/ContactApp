package com.aleksandrphilimonov.service;

import com.aleksandrphilimonov.converter.ServiceUserToUserDtoConverter;

import static com.aleksandrphilimonov.dao.DaoFactory.getServiceUserDao;

public class ServiceFactory {
    private static ServiceUserToUserDtoConverter serviceUserToUserDtoConverter;

    public static ServiceUserToUserDtoConverter getServiceUserToUserDtoConverter() {
        if (serviceUserToUserDtoConverter == null) {
            serviceUserToUserDtoConverter = new ServiceUserToUserDtoConverter();
        }
        return serviceUserToUserDtoConverter;
    }

    private static DigestService digestService;

    public static DigestService getDigestService() {
        if (digestService == null) {
            digestService = new DigestService();
        }
        return digestService;
    }

    private static SecurityService securityService;

    public static SecurityService getSecurityService() {
        if (securityService == null) {
            securityService = new SecurityService(
                    getServiceUserDao(),
                    getDigestService(),
                    getServiceUserToUserDtoConverter()
            );
        }
        return securityService;
    }
}
