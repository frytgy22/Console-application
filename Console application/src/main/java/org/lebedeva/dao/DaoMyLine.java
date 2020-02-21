package org.lebedeva.dao;

import lombok.AllArgsConstructor;
import lombok.Cleanup;
import org.lebedeva.model.MyLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class for writing to the db in the statistics_for_each_line table
 */

@AllArgsConstructor
public class DaoMyLine implements Dao<MyLine> {
    Connection connection;

    @Override
    public int save(MyLine data) throws SQLException {
        return 0;
    }

    public void save(MyLine data, int id) throws SQLException {
        final String INSERT = "INSERT INTO statistics_for_each_line(" +
                "file_id, line, longest_word, shortest_word, line_length, average_word_length, duplication_of_words)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?)";

        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        try {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, data.getLine());
            preparedStatement.setString(3, data.getLongestWord());
            preparedStatement.setString(4, data.getShortestWord());
            preparedStatement.setInt(5, data.getLineLength());
            preparedStatement.setDouble(6, data.getAverageWordLength());
            preparedStatement.setString(7, data.getDuplicationOfWords().toString());
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
}

