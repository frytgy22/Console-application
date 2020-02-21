package org.lebedeva.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Class for saving statistics of each line from a file
 */

@Data
@Builder
public class MyLine {
    private int fileId;
    private String line;
    private String longestWord;
    private String shortestWord;
    private int lineLength;
    private double averageWordLength;
    private Map<String, Integer> duplicationOfWords;
}
