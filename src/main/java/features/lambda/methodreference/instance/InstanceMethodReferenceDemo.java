package features.lambda.methodreference.instance;

import java.util.function.Function;

interface StringFunc {
    String func (MyString str);
}

class MyString {

    private final String str;

    public MyString(String str) {
        this.str = str;
    }

    String reverse() {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = str.length() -1 ; i >=0 ; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}

class InstanceMethodReferenceDemo {

    public static void main (String[] args) {

        MyString myString = new MyString("Lambdas are powerful.");

        // without Method Reference
        System.out.println(myString.reverse());

        // with Method Reference
        StringFunc instanceWithFunction = MyString::reverse;
        System.out.println( instanceWithFunction.func ( myString ) );

        // with Method Reference - Standard functional interfaces
        Function<MyString,String> instanceWithFunction2 = MyString::reverse;
        System.out.println(instanceWithFunction2.apply(myString));

    }

}