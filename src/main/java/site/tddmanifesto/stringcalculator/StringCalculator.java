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
                token = tokenizer.nextToken();
                if (tokenIsDelimeter(token)) {
                    if (!tokenizer.hasMoreTokens()) {
                        throw new IllegalArgumentException("Empty argument after last separator not allowed.");
                    }
                } else {
                    sum += Integer.parseInt(token);
                }
            }
            return sum;
        }
    }

    private boolean tokenIsDelimeter(String token) {
        return DELIM.contains(token);
    }

}
