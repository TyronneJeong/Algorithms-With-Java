package programmers2;

import programmers2.hanhwalife.Quest01;
import programmers2.hanhwalife.Quest02;
import programmers2.hanhwalife.Quest03;
import programmers2.kakaoDeclaration.Declaration;

public class Programmers2 {
    public Programmers2() {
    }

    public static void main(String[] args) {
        System.out.println("~~ 프로그램 시작 ~~");
        Quest01 q1 = new Quest01();
        Quest02 q2 = new Quest02();
        Quest03 q3 = new Quest03();

//        q1.exec();
        q2.exec();
//        q3.exec();



        String sample = "10/20 MON";
        System.out.println(sample.split("/| ")[2]);

        System.out.println("~~ 프로그램 종료 ~~");

    }
}