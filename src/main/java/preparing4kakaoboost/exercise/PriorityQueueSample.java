package preparing4kakaoboost.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PriorityQueueSample {
    public void exec(){
        solution();
    }

    private void solution(){
        // 특정 우선 순위를 지정 하여 queuing 할 수 있다.
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        pq.add(1);
        pq.add(3);
        pq.add(2);
        pq.add(4);
        pq.add(5);
        pq.add(1);
        System.out.println(pq.peek()); // 최상단 값 읽기
        System.out.println(pq.poll()); // 최상단 값 뽑기
        System.out.println(pq.poll()); // 다음 최상단값 뽑힘
    }
}

