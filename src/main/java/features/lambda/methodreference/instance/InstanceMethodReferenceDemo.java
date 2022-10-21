package features.lambda.methodreference.instance;

import java.util.Comparator;

class DomainObject {
    private String description;

    public DomainObject(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int compareTo (DomainObject obj) {
        return description.compareTo(obj.getDescription());
    }

}

class HashComparator {

    int subtract(DomainObject domainObj1, DomainObject domainObj2) {
        return domainObj1.hashCode() - domainObj2.hashCode();
    }

}

class InstanceMethodReferenceDemo {

    public static void main (String[] args) {

        HashComparator hashComparator = new HashComparator();

        DomainObject d1 = new DomainObject("d1");
        DomainObject d2 = new DomainObject("d2");

        // without using Method Reference
        System.out.println(hashComparator.subtract(d1, d2));

        // with Instance Method Reference - invoking object is not a parameter of the functional interface
        Comparator<DomainObject> instanceWithFunction1 = hashComparator::subtract;
        System.out.println( instanceWithFunction1.compare( d1, d2 ));

        // with Instance Method Reference - using invoking object as the first parameter of the functional interface
        Comparator<DomainObject>   instanceWithFunction2 = DomainObject::compareTo;
        System.out.println( instanceWithFunction2.compare( d1, d2 ));





    }

}