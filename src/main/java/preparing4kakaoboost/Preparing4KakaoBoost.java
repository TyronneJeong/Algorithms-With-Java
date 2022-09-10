package preparing4kakaoboost;

import preparing4kakaoboost.codingtest.solve01;
import preparing4kakaoboost.exercise.BFS;
import preparing4kakaoboost.exercise.PriorityQueueSample;
import preparing4kakaoboost.kakao2022.problem05;

import java.util.HashMap;

// 채점기준
// 테스트케이스를 모두 통과해야 한다.
// 부분점수 없다.



public class Preparing4KakaoBoost {
    public static void main(String[] args) {
//        solve01 s = new solve01();
//        s.exec();
        int z;
        System.out.println("1번 유형");
        z= 0;
        for(int ix = 0; ix < 10; ix = z++){
            System.out.println(ix);
        }

        System.out.println("2번 유형");
        z= 0;
        for(int ix = 0; ix < 10; ix = ++z){
            System.out.println(ix);
        }

    }
}

class sample01{
    private int a;
    private int b;
    private int c;
    private HashMap hmap;

    sample01(){
        this.b++;
    }

    sample01(int c){
        this.c = c;
    }

    public void doSomething(){
        int innerA;
        System.out.println("sample");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(hmap);
        hmap.put("sample", "sample");
    }
}

