package org.lebedeva.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lebedeva.model.MyLine;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class for getting text file statistic
 */

@Data
@AllArgsConstructor
public class FileStatisticImplementation implements FileStatistic<String, Integer> {
    private String text;

    @Override
    public String getLongestWord() {
        return Arrays.stream(text.replaceAll("[^A-Za-zА-Яа-я]", "\n")
                .split("\n"))
                .filter(s -> !s.isEmpty())
                .max(Comparator.comparingInt(String::length)).orElse(text);
    }

    @Override
    public String getShortestWord() {
        return Arrays.stream(text.replaceAll("[^A-Za-zА-Яа-я]", "\n")
                .split("\n"))
                .filter(s -> !s.isEmpty())
                .min(Comparator.comparingInt(String::length)).orElse(text);
    }

    @Override
    public int getLineLength() {
        return text.length();
    }

    @Override
    public double getAverageOfWords() {
        return Arrays.stream(text.replaceAll("[^A-Za-zА-Яа-я]", "\n")
                .split("\n"))
                .filter(s -> !s.isEmpty())
                .collect(Collectors.averagingInt(String::length));
    }

    @Override
    public Map<String, Integer> getDuplicationOfWords() {
        Map<String, Integer> words = new HashMap<>();
        String copyText = text.replaceAll("[^A-Za-zА-Яа-я]", "\n");

        StringTokenizer tokenizer = new StringTokenizer(copyText, "\n");
        String key = "";

        while (tokenizer.hasMoreTokens()) {
            key = tokenizer.nextToken();
            if (!words.containsKey(key)) {
                words.put(key, 1);
            } else {
                for (Map.Entry<String, Integer> map : words.entrySet()) {
                    if (map.getKey().equals(key)) {
                        words.put(map.getKey(), map.getValue() + 1);
                        break;
                    }
                }
            }
        }
        return words.entrySet().stream()
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() > 1)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    /**
     * Class for getting statistic for each line from the file
     */

    public class StatisticForEachLines {
        public List<MyLine> getLineStatistic() {
            List<MyLine> lineStatistics = new ArrayList<>();

            StringTokenizer tokenizer = new StringTokenizer(text, "\n");
            String key = "";

            FileStatisticImplementation statistic;

            while (tokenizer.hasMoreTokens()) {
                key = tokenizer.nextToken();
                statistic = new FileStatisticImplementation(key);

                lineStatistics.add(MyLine.builder()
                        .line(key)
                        .longestWord(statistic.getLongestWord())
                        .shortestWord(statistic.getShortestWord())
                        .averageWordLength(statistic.getAverageOfWords())
                        .lineLength(statistic.getLineLength())
                        .duplicationOfWords(statistic.getDuplicationOfWords())
                        .build());
            }
            return lineStatistics;
        }
    }
}
