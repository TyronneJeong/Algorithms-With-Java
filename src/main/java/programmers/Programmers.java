package programmers;

import programmers.exercise.hash.E01UncompletedRace;

public class Programmers {
    public static void main(String[] args) {
        System.out.println("start");
        E01UncompletedRace exec = new E01UncompletedRace();
        exec.exec();
        System.out.println("end");
    }
}
