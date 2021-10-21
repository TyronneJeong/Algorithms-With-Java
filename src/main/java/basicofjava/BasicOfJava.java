package basicofjava;

import basicofjava.exercise.CollectionExample;

public class BasicOfJava {
    public static void main(String[] args) {
        CollectionExample collectionExample = new CollectionExample();
        try {
            collectionExample.exec();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
