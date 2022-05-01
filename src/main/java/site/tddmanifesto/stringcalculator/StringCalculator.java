package site.tddmanifesto.stringcalculator;

public class StringCalculator {

    public int add(String s) {
        String[] parts = s.split("\n");
        String delimiter = parts[0].substring(2);
        return processNumbers(parts[1], delimiter);
    }

    private int processNumbers(String numberString, String expectedDelimiter) {
        int sum = 0;
        for (int i = 0; i < numberString.length() ; ) {
            int j = i;
            while ( j < numberString.length() && Character.isDigit(numberString.charAt(j))) j++;
            if (j>i) { //if we are processing a number
                int number = Integer.parseInt(numberString, i, j,10);
                sum += number;
                i = j;
            } else { //it should be the separator
                String actualDelimiter = numberString.substring(i,i+expectedDelimiter.length());
                if (expectedDelimiter.equals( actualDelimiter)) {
                    i = i + expectedDelimiter.length();
                } else {
                    throw new IllegalArgumentException("'"+expectedDelimiter+ "' expected, but '" + actualDelimiter + "' found at position " +i+"." );
                }
            }
        }
        return sum;
    }

}
