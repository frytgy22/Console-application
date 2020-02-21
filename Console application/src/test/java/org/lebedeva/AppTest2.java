package org.lebedeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lebedeva.statistic.FileStatisticImplementation;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.lebedeva.AppTest1.mapEquals;

public class AppTest2 {

    String text = "";

    FileStatisticImplementation statistic = new FileStatisticImplementation(text);

    @Test
    @DisplayName("Test 2.1")
    void test2_1() {
        String actual = statistic.getLongestWord();
        String expected = "";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 2.2")
    void test2_2() {
        String actual = statistic.getShortestWord();
        String expected = "";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 2.3")
    void test2_3() {
        double actual = statistic.getAverageOfWords();
        double expected = 0.0;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 2.4")
    void test2_4() {
        int actual = statistic.getLineLength();
        int expected = 0;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 2.5")
    void test2_5() {
        Map<String, Integer> actual = statistic.getDuplicationOfWords();
        Map<String, Integer> expected = new LinkedHashMap<>();
        Assertions.assertTrue(mapEquals(actual, expected));
    }
}
