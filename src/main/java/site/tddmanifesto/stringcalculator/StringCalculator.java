package site.tddmanifesto.stringcalculator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        List<Integer> negativeNumbers = new ArrayList<>();
        for (int i = 0; i < numericPart.length() ; ) {
            if (processingANumber(numericPart, i)) {
                int endOfNumberIndex = getEndOfNumberIndex(numericPart, i);
                int number = Integer.parseInt(numericPart, i, endOfNumberIndex, 10);
                if (number >= 0){
                    sum += number;
                } else {
                    negativeNumbers.add(number);
                }

                i = endOfNumberIndex;
            } else { //we are processing the delimiter
                i = processDelimiter(numericPart, expectedDelimiter, i);
            }
        }
        if (!negativeNumbers.isEmpty()){
            throw new IllegalArgumentException("Negative number(s) not allowed: "+ formatErrorMsg(negativeNumbers));
        }
        return sum;
    }

    private String formatErrorMsg(List<Integer> negativeNumbers) {
        StringBuilder sb = new StringBuilder();
        for (Iterator<Integer> i = negativeNumbers.iterator(); i.hasNext(); ) {
            sb.append(i.next().toString());
            if (i.hasNext()) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    private boolean processingANumber(String numberString, int i) {
        return Character.isDigit(numberString.charAt(i)) || '-' == numberString.charAt(i);
    }

    private int getEndOfNumberIndex(String numberString, int i) {
        int j = i;
        while ( j < numberString.length() && isPartOfANumber(numberString.charAt(j))) j++;
        return j;
    }

    private boolean isPartOfANumber(char c) {
        return Character.isDigit(c) || c == '-';
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
