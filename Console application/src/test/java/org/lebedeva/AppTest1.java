package org.lebedeva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lebedeva.statistic.FileStatisticImplementation;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppTest1 {

    String text = "<p>There are many pictures of my cat here,\n" +
            "as well as my <b>very cool</b> blog page,\n" +
            "which contains <font color=\"red\">awesome\n" +
            "stuff about my trip to Vegas.</p>";

    FileStatisticImplementation statistic = new FileStatisticImplementation(text);

    @Test
    @DisplayName("Test 1.1")
    void test1_1() {
        String actual = statistic.getLongestWord();
        String expected = "pictures";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 1.2")
    void test1_2() {
        String actual = statistic.getShortestWord();
        String expected = "p";
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 1.3")
    void test1_3() {
        double actual = statistic.getAverageOfWords();
        double expected = 3.625;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 1.4")
    void test1_4() {
        int actual = statistic.getLineLength();
        int expected = 159;
        Assertions.assertEquals(actual, expected);
    }

    @Test
    @DisplayName("Test 1.5")
    void test1_5() {
        Map<String, Integer> actual = statistic.getDuplicationOfWords();
        Map<String, Integer> expected = new LinkedHashMap<>();
        expected.put("my", 3);
        expected.put("b", 2);
        expected.put("p", 2);
        expected.put("as", 2);
        Assertions.assertTrue(mapEquals(actual, expected));
    }

    public static boolean mapEquals(Map<String, Integer> first, Map<String, Integer> second) {
        if (first.size() != second.size()) {
            return false;
        }
        return first.entrySet().stream()
                .allMatch(e -> e.getValue().equals(second.get(e.getKey())));
    }
}

