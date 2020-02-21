package org.lebedeva.statistic;

import java.util.Map;

public interface FileStatistic<T, I> {
    T getLongestWord();

    T getShortestWord();

    int getLineLength();

    double getAverageOfWords();

    Map<T, I> getDuplicationOfWords();
}
