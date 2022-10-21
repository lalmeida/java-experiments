package features.lambda.methodreference.staticz;


interface StringFunc {
    String func (String str);
}

class MyStringOps {

    static String strReverse(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = str.length() -1 ; i >=0 ; i--) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
}

class StaticMethodReferenceDemo {

    public static void main (String[] args) {

        String s = "Lambdas are powerful.";

        // without Method Reference
        System.out.println(MyStringOps.strReverse( s ));

        // with Method Reference
        StringFunc instanceWithFunction = MyStringOps::strReverse;
        System.out.println( instanceWithFunction.func ( s ) );

        // with Method Reference - Standard functional interfaces
        java.util.function.Function<String, String> instanceWithFunction2 = MyStringOps::strReverse;
        System.out.println( instanceWithFunction2.apply ( s ) );

    }

}
