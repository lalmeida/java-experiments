package site.tddmanifesto.stringcalculator;

public class StringCalculator {

    public int add(String s) {
        if (s.isBlank()) {
            return 0;
        } else if (!s.contains(",")) {
            return Integer.parseInt(s);
        } else {
            String [] str = s.split(",");
            return Integer.parseInt(str[0]) + Integer.parseInt(str[1]);
        }
    }

}
