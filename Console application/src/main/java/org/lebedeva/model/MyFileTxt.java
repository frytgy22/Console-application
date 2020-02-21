package org.lebedeva.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Class for saving file statistic
 */

@Data
@Builder
public class MyFileTxt {
    private int id;
    private String name;
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private double averageWordLength;
    private Map<String, Integer> duplicationOfWords;
}
