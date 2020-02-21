package org.lebedeva.dao;

import java.sql.SQLException;

public interface Dao<T> {
    int save(T data) throws SQLException;
}
