package site.tddmanifesto.stringcalculator;

public class StringCalculator {

    public int add(String s) {
        if (s.isBlank()) {
            return 0;
        } else {
            String [] strings = s.split("[,\n]");
            int sum = 0;
            for (String str: strings) {
                sum += Integer.parseInt(str);
            }
            return sum;
        }
    }

}
