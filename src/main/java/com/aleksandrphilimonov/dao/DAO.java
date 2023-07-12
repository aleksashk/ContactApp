package com.aleksandrphilimonov.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<DOMAIN, ID> {
    DOMAIN findById(ID id) throws SQLException;

    List<DOMAIN> findByAll();

    DOMAIN insert(DOMAIN domain);

    DOMAIN update(DOMAIN domain);

    boolean delete(ID id);

}
