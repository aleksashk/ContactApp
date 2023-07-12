package com.aleksandrphilimonov.service;

import com.aleksandrphilimonov.converter.ServiceUserToUserDtoConverter;
import com.aleksandrphilimonov.dao.ServiceUserDao;
import com.aleksandrphilimonov.domain.ServiceUser;
import com.aleksandrphilimonov.dto.UserDto;
import com.aleksandrphilimonov.exception.CommonServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest {

    @InjectMocks
    SecurityService subj;

    @Mock
    ServiceUserDao serviceUserDao;
    @Mock
    DigestService digestService;
    @Mock
    ServiceUserToUserDtoConverter converter;

    @Test
    public void auth_userNotFoundByEmail() throws CommonServiceException {
        when(serviceUserDao.findByEmail("aleks@gmail.com")).thenReturn(null);
        UserDto userDto = subj.auth("aleks@gmail.com", "password");

        assertNull(userDto);
    }

    @Test
    public void auth_userFoundButEmailWrong() throws CommonServiceException {
        ServiceUser serviceUser = new ServiceUser();
        serviceUser.setPassword("some password");

        when(serviceUserDao.findByEmail("aleks@gmail.com")).thenReturn(serviceUser);
        when(digestService.hash("password")).thenReturn("other hash");
        UserDto userDto = subj.auth("aleks@gmail.com", "password");

        assertNull(userDto);
    }

    @Test
    public void auth_ok() throws CommonServiceException {
        UserDto userDto = new UserDto();
        userDto.setId(1L);

        ServiceUser serviceUser = new ServiceUser();
        serviceUser.setId(1L);
        serviceUser.setPassword("some password");

        when(serviceUserDao.findByEmail("aleks@gmail.com")).thenReturn(serviceUser);
        when(digestService.hash("password")).thenReturn("some password");
        when(converter.convert(serviceUser)).thenReturn(userDto);
        UserDto userDtoFromService = subj.auth("aleks@gmail.com", "password");

        assertEquals(userDto, userDtoFromService);
    }

}