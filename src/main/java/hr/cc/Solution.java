package hr.cc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

       List<Operation> operations = readInput(System.in);

        List<String> outputLines = process(operations);

        printResults(outputLines);
    }

    private static void printResults(List<String> outputLines) {

        for (String line : outputLines) {
            System.out.println(line);
        }

    }

    static List<Operation> readInput(InputStream in) {

        Scanner scan = new Scanner(in);
        List<Operation> result = new ArrayList<>();

        while (scan.hasNextLine()) {
            String[] tokens = scan.nextLine().split(";");
            result.add(new Operation(Action.from(tokens[0]), Type.from(tokens[1]), tokens[2]));
        }
        return result;
    }

    static List<String> process(List<Operation> operations) {
        ArrayList<String> result = new ArrayList<>();

        for (Operation operation : operations) {
            if (operation.getAction() == Action.Split) {
                result.add(split(operation.getName()));
            } else {
                result.add(combine(operation.getType(), operation.getName()));
            }
        }
        return result;
    }

    private static String combine(Type type, String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (i == 0) {
                if (type == Type.Class) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            } else if (Character.isSpaceChar(c)) {
                c = name.charAt(++i); // advancing iterator and reading. Assuming there is always a non-space character after the space.
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }

            if (i == name.length() - 1 && type == Type.Method) { // if last character && is a method
                sb.append("()");// place parenthesis!
            }
        }
        return sb.toString();
    }

    private static String split(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (i == 0) { // first character should always be lower case
                sb.append(Character.toLowerCase(c));
            } else if (Character.isUpperCase(c)) { // else if upper case
                sb.append(' ');
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}

    class Operation {

        private final Action action;
        private final Type type;
        private final String name;

        public Operation(Action action, Type type, String name) {
            this.action = action;
            this.type = type;
            this.name = name;
        }

        public Action getAction() {
            return action;
        }

        public Type getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

    enum Action {

        Split("S"), Combine("C");

        private final String code;

        Action(String code) {
            this.code = code;
        }

        public static Action from(final String actionCode) {
            if (actionCode.equals(Split.code)) {
                return Split;
            } else if (actionCode.equals(Combine.code) ){
                return Combine;
            } else {
                throw new IllegalArgumentException("No enum constant for:"+actionCode+".");
            }
        }
    }


    enum Type {

        Method("M"), Class("C"), Variable("V");

        private final String code;

        Type(String code) {
            this.code = code;
        }

        public static Type from(String typeCode) {
            if (typeCode.equals(Class.code)) {
                return Class;
            } else if (typeCode.equals(Method.code) ){
                return Method;
            } else if (typeCode.equals(Variable.code) ){
                return Variable;
            } else {
                throw new IllegalArgumentException("No enum constant for:"+typeCode+".");
            }
        }
    }