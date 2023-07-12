package com.aleksandrphilimonov.dao;

import java.util.List;

public interface DAO<DOMAIN, ID> {
    DOMAIN findById(ID id);

    List<DOMAIN> findByAll();

    DOMAIN insert(DOMAIN domain);

    DOMAIN update(DOMAIN domain);

    boolean delete(ID id);

}
