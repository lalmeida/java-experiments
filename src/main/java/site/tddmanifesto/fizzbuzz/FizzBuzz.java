package site.tddmanifesto.fizzbuzz;

public class FizzBuzz {
    public String format(Integer i) {

        if (i>0) {
            if (i%3==0 && i%5==0) {
                return "fizzbuzz";
            } else if (i%3 == 0) {
                return "fizz";
            } else if (i%5 == 0) {
                return "buzz";
            }
        }
        return i.toString();

    }

}
