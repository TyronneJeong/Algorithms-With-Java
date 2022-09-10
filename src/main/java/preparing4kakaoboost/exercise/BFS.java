package preparing4kakaoboost.exercise;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public void exec(){
        solution(0);
    }

    private boolean[] visitYn; // 방문여부 저장 배열


    private void solution(int start){
        // 넓이 우선 탐색은 재귀적 구현이 불가능 하다.
        // 큐를 이용하여 탐색 대상을 확인 방식

        Queue<Integer> q = new LinkedList<>();
        q.add(start); // 0번 스타트
        // q.offer() : 둘다 동일 기능. 하지만 add 는 이셉션을 포함하고 있다.

        // 대기중인 큐가 없을 때 까지 반복
        while(!q.isEmpty()){
            // front, peek (첫번째 값), poll (마지막값 호출 후 삭제)
            int x = q.peek(); // 첫번째 값 호출 - 삭제는 하지 않는다.
            int y = q.poll(); // 큐에 내용물 제거
            System.out.println(x  +" / " + y);
        }
    }
}
