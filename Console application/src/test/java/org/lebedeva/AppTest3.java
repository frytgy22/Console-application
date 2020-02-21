package org.lebedeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lebedeva.statistic.FileStatisticImplementation;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.lebedeva.AppTest1.mapEquals;

public class AppTest3 {
    String text = "123 hello world\n" +
            "\n" +
            "good morning!";

    FileStatisticImplementation statistic = new FileStatisticImplementation(text);

    @Test
    @DisplayName("Test 3.1")
    void test3_1() {
        String actual = statistic.getLongestWord();
        String expected = "morning";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 3.2")
    void test3_2() {
        String actual = statistic.getShortestWord();
        String expected = "good";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 3.3")
    void test3_3() {
        double actual = statistic.getAverageOfWords();
        double expected = 5.25;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 3.4")
    void test3_4() {
        int actual = statistic.getLineLength();
        int expected = 30;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 3.5")
    void test3_5() {
        Map<String, Integer> actual = statistic.getDuplicationOfWords();
        Map<String, Integer> expected = new LinkedHashMap<>();
        Assertions.assertTrue(mapEquals(actual, expected));
    }
}
