package org.lebedeva.dao;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import org.lebedeva.model.MyFileTxt;

import java.sql.*;

/**
 * Class for writing to the db in the txt_files table
 */

@AllArgsConstructor
public class DaoMyFileTxt implements Dao<MyFileTxt> {
    private Connection connection;

    @Override
    public int save(MyFileTxt data) throws SQLException {
        final String INSERT = "{CALL sp_add_txt_file(?, ?, ?, ?, ?, ?, ?)}";
        int id = 0;

        @Cleanup CallableStatement statement = connection.prepareCall(INSERT);
        try {
            connection.setAutoCommit(false);

            statement.setString(1, data.getName());
            statement.setString(2, data.getLongestWord());
            statement.setString(3, data.getShortestWord());
            statement.setInt(4, data.getLineLength());
            statement.setDouble(5, data.getAverageWordLength());
            statement.setString(6, data.getDuplicationOfWords().toString());
            statement.registerOutParameter(7, Types.INTEGER);
            statement.executeUpdate();

            connection.commit();
            id = statement.getInt(7);
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }
        return id;
    }
}
