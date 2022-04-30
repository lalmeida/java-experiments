package site.tddmanifesto.stringcalculator;

public class StringCalculator {

    public int add(String s) {
        if (s.isBlank()) {
            return 0;
        } else if (!s.contains(",")) {
            return Integer.valueOf(s);
        } else {
            String [] str = s.split(",");
            return Integer.valueOf(str[0]) + Integer.valueOf(str[1]);
        }
    }

}
