package org.lebedeva;

import org.lebedeva.dao.DaoMyFileTxt;
import org.lebedeva.dao.DaoMyLine;
import org.lebedeva.db.DataSource;
import org.lebedeva.db.DataSourceMySql;
import org.lebedeva.file.FileTxt;
import org.lebedeva.file.SelectFileImplementation;
import org.lebedeva.model.MyFileTxt;
import org.lebedeva.model.MyLine;
import org.lebedeva.statistic.FileStatisticImplementation;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new SelectFileImplementation().getFile();
        FileTxt fileTxt = new FileTxt(file);
        String text = fileTxt.readFile();

        if (!text.isEmpty()) {
            FileStatisticImplementation statistic = new FileStatisticImplementation(text);

            MyFileTxt myFileTxt = MyFileTxt.builder()
                    .name(file.getName())
                    .longestWord(statistic.getLongestWord())
                    .shortestWord(statistic.getShortestWord())
                    .averageWordLength(statistic.getAverageOfWords())
                    .lineLength(statistic.getLineLength())
                    .duplicationOfWords(statistic.getDuplicationOfWords())
                    .build();
            try {
                DataSource dataSource = new DataSourceMySql();
                Connection connection = dataSource.getConnection();

                DaoMyFileTxt daoMyFileTxt = new DaoMyFileTxt(connection);
                DaoMyLine daoMyLine = new DaoMyLine(connection);

                int newFileId = daoMyFileTxt.save(myFileTxt);

                if (newFileId > 0) {
                    FileStatisticImplementation.StatisticForEachLines eachLines = statistic.new StatisticForEachLines();
                    List<MyLine> lineStatistic = eachLines.getLineStatistic();

                    for (MyLine line : lineStatistic) {
                        daoMyLine.save(MyLine.builder()
                                .line(line.getLine())
                                .longestWord(line.getLongestWord())
                                .shortestWord(line.getShortestWord())
                                .averageWordLength(line.getAverageWordLength())
                                .lineLength(line.getLineLength())
                                .duplicationOfWords(line.getDuplicationOfWords())
                                .build(), newFileId);
                    }
                    System.out.println("Statistic saved successfully.");
                } else {
                    System.out.println("This file already exist in the db.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File is empty or not txt.");
        }
    }
}
