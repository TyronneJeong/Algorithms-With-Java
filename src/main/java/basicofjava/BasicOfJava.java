package basicofjava;

import basicofjava.exercise.CollectionExample;
import basicofjava.exercise.MapExample;

public class BasicOfJava {
    public static void main(String[] args) {
        CollectionExample collectionExample = new CollectionExample();
        MapExample mapExample = new MapExample();
        try {
            collectionExample.exec();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
