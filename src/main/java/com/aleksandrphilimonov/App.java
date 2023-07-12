package com.aleksandrphilimonov;

import com.aleksandrphilimonov.exception.CommonServiceException;
import com.aleksandrphilimonov.service.SecurityService;
import com.aleksandrphilimonov.service.ServiceFactory;

public class App {
    public static void main(String[] args) throws CommonServiceException {
        SecurityService securityService = ServiceFactory.getSecurityService();
        System.out.println(securityService.auth("aleks@gmail.com", "password"));
    }
}
