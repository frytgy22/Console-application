package org.lebedeva.db;

import java.sql.Connection;

public interface DataSource {
    Connection getConnection() throws Exception;
}
