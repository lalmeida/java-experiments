package site.tddmanifesto.stringcalculator;

import java.util.StringTokenizer;

public class StringCalculator {

    public static final String DELIM = ",\n";

    public int add(String s) {
        String[] parts = s.split("\n");
        String delimiter = parts[0].substring(2);
        return addNumbers(parts[1], delimiter);
    }

    private int addNumbers(String numberString, String delimiters) {
        int sum = 0;
        StringTokenizer tokenizer = new StringTokenizer(numberString, delimiters, true);
        while (tokenizer.hasMoreTokens()) {
            sum += processToken(tokenizer.nextToken(), !tokenizer.hasMoreTokens(), delimiters);
        }
        return sum;
    }

    private int processToken(String token, boolean lastToken, String delimiter) {
        if (tokenIsDelimiter(token, delimiter)) {
            if (lastToken) {
                throw new IllegalArgumentException("Empty argument after last separator not allowed.");
            } else {
                return 0; // delimiters don´t change sum
            }
        } else {
            return Integer.parseInt(token);
        }
    }

    private boolean tokenIsDelimiter(String token, String delimiter) {
        return delimiter.contains(token);
    }

}
