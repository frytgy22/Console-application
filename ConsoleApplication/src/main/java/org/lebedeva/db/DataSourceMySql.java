package org.lebedeva.db;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Class for connection with the db
 */

public class DataSourceMySql implements DataSource {
    private Connection connection;

    @Override
    public Connection getConnection() throws Exception {
        Properties property = new Properties();
        @Cleanup FileInputStream stream = new FileInputStream("dbConfig.properties");
        property.load(stream);

        return DriverManager.getConnection(
                property.getProperty("mysql.url"),
                property.getProperty("mysql.user"),
                property.getProperty("mysql.password"));
    }
}
