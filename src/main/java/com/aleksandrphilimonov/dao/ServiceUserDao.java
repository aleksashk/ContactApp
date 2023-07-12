package com.aleksandrphilimonov.dao;

import com.aleksandrphilimonov.domain.ServiceUser;
import com.aleksandrphilimonov.exception.CommonServiceException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ServiceUserDao implements Dao<ServiceUser, Long> {

    private final DataSource dataSource;

    public ServiceUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ServiceUser findByEmail(String email) throws CommonServiceException {
        ServiceUser serviceUser = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from service_user where email = ?")) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    serviceUser = new ServiceUser();
                    serviceUser.setId(rs.getLong("id"));
                    serviceUser.setEmail(rs.getString("email"));
                    serviceUser.setFirstName(rs.getString("first_name"));
                    serviceUser.setLastName(rs.getString("last_name"));
                    serviceUser.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            throw new CommonServiceException(e);
        }
        return serviceUser;
    }

    public ServiceUser findById(Long aLong) {
        return null;
    }

    public List<ServiceUser> findByAll() {
        return null;
    }

    public ServiceUser insert(ServiceUser serviceUser) {
        return null;
    }

    public ServiceUser update(ServiceUser serviceUser) {
        return null;
    }

    public boolean delete(Long aLong) {
        return false;
    }
}
