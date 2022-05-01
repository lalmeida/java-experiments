package site.tddmanifesto.stringcalculator;

public class StringCalculator {

    public int add(String inputString) {
        String[] parts = inputString.split("\n");
        String delimiter = getDelimiter(parts[0]);
        return processNumericPart(parts[1], delimiter);
    }

    private String getDelimiter(String delimiterDefinitionPart) {
        return delimiterDefinitionPart.substring(2);
    }

    private int processNumericPart(String numericPart, String expectedDelimiter) {
        int sum = 0;
        for (int i = 0; i < numericPart.length() ; ) {
            if (processingANumber(numericPart, i)) {
                int endOfNumberIndex = getEndOfNumberIndex(numericPart, i);
                int number = Integer.parseInt(numericPart, i, endOfNumberIndex,10);
                sum += number;
                i = endOfNumberIndex;
            } else { //we are processing the delimiter
                i = processDelimiter(numericPart, expectedDelimiter, i);
            }
        }
        return sum;
    }

    private boolean processingANumber(String numberString, int i) {
        return Character.isDigit(numberString.charAt(i));
    }

    private int getEndOfNumberIndex(String numberString, int i) {
        int j = i;
        while ( j < numberString.length() && Character.isDigit(numberString.charAt(j)) ) j++;
        return j;
    }

    private int processDelimiter(String numberString, String expectedDelimiter, int i) {
        String actualDelimiter = numberString.substring(i, i + expectedDelimiter.length());
        if (expectedDelimiter.equals(actualDelimiter)) {
            i = i + expectedDelimiter.length();
        } else {
            throw new IllegalArgumentException("'"+ expectedDelimiter + "' expected, but '" + actualDelimiter + "' found at position " + i +"." );
        }
        return i;
    }

}
