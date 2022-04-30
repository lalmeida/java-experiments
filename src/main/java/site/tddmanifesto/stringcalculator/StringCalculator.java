package site.tddmanifesto.stringcalculator;

import java.util.StringTokenizer;

public class StringCalculator {

    public static final String DELIM = ",\n";

    public int add(String s) {
        if (s.isBlank()) {
            return 0;
        } else {
            StringTokenizer tokenizer = new StringTokenizer(s, DELIM, true);
            int sum = 0;
            String token;
            while (tokenizer.hasMoreTokens()) {
                sum += processToken(tokenizer.nextToken(), !tokenizer.hasMoreTokens());
            }
            return sum;
        }
    }

    private int processToken(String token, boolean lastToken) {
        if (tokenIsDelimeter(token)) {
            if (lastToken) {
                throw new IllegalArgumentException("Empty argument after last separator not allowed.");
            } else {
                return 0; // delimiters don´t change sum
            }
        } else {
            return Integer.parseInt(token);
        }
    }

    private boolean tokenIsDelimeter(String token) {
        return DELIM.contains(token);
    }

}
