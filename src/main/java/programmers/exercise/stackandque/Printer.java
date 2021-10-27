package programmers.exercise.stackandque;

import java.util.*;

/**
 * [문제설명]
 * 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다.
 * 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다.
 * 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.
 *
 * 1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
 * 3. 그렇지 않으면 J를 인쇄합니다.
 * 예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.
 *
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.
 *
 * 현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를
 * 알려주는 location이 매개변수로 주어질 때,
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.
 *
 * [제한사항]
 * 현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
 * 인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
 * location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
 *
 * [입출력 예]
 * priorities	      location	return
 * [2, 1, 3, 2]	          2	      1
 * [1, 1, 9, 1, 1, 1]     0	      5
 *
 * [입출력 예 설명]
 * 예제 #1
 *
 * 문제에 나온 예와 같습니다.
 *
 * 예제 #2
 *
 * 6개의 문서(A, B, C, D, E, F)가 인쇄 대기목록에 있고 중요도가 1 1 9 1 1 1 이므로 C D E F A B 순으로 인쇄합니다.
 */
public class Printer {
    public void exec(){
        int[] priorities = {1, 1, 3, 3, 1,  // 5
                4, 1, 1, 2, 2,  // 10
                4, 1, 1, 1, 1,  // 15
                1, 1, 9, 1, 1,  // 20
                1, 2, 2, 1, 1,  // 25
                1, 1, 1, 1, 7,  // 30
                7, 1, 1, 2, 3}; // 35
        int location = 2;

//        int[] priorities = {2, 1, 3, 2};
//        int location = 0;
        //int[] priorities = {1, 1, 9, 1, 1, 1};
        //int location = 3;

        //int[] priorities = {1, 1, 9, 8, 7, 1};
        //int location = 1; // 6이 답으로 나와야 함

        //int[] priorities = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        //int location = 7; // 9 ?? 8 이 나와야 정답

//        int[] priorities = {3, 2, 9, 1, 4, 7, 8, 9, 2, 3, 4, 5, 5};
//        int location = 11;

        priorities = new int[]{1, 7, 1, 9, 7, 6, 4, 3, 1, 1, 1}; // 11개
        location = 4; // 11이 나옴

        // 3, 2

        System.out.println("exec >> " + solution(priorities, location));
        // System.out.println("exec >> " + solution_others(priorities, location));
    }

    public int solution(int[] priorities, int location) {
        Map<Integer, Integer> hm = new HashMap<>(); // <앨리먼트번호, 앨리먼트우선순위값>
        Iterator it;

        int myPriority = priorities[location];

        int lastCursor = 0;
        for (int i = 0; i < priorities.length; i++) {
            if(myPriority <= priorities[i]){
                if(priorities[lastCursor] < priorities[i]){ // 가장 앞에 있는 친구가 스타트 포인트
                    lastCursor = i;
                }
                hm.put(i, priorities[i]);
            }
        }

        int priority = priorities[lastCursor]; // 가장높은 우선순위 값
        int nextCursor = 0;
        int nextPriority = 0;
        int tempCnt = 0;

        int loc;
        int pri;
        int nCnt = 0; // 최종 리턴 벨류
        boolean hasBefore = false;

        while(priority > 0){
            if(nextCursor > 0){
                lastCursor = nextCursor;
            }
            nextCursor = 0;     // 초기화
            nextPriority = 0;   // 초기화
            hasBefore  = false; // 초기화
            tempCnt = 0;

            it = hm.keySet().iterator();
            while(it.hasNext()){
                loc = (Integer)it.next();  // location 정보
                pri = hm.get(loc);         // priority 정보

                if(pri == priority){       // 탐색 하고자 하는 [우선순위] 같으면서 카운트를 센다.
                    // 상위 레벨에 대한 처리
                    if(myPriority < pri){
                        nCnt++;
                        if (loc < lastCursor) {
                            nextCursor = loc;
                            hasBefore = true;
                        }
                        if (!hasBefore && lastCursor < loc) {
                            nextCursor = loc;
                        }
                    } else if(myPriority == pri){
                        if(loc <= location){
                            if (loc >= lastCursor) { // 마지막 커서 이후에 값이 존재하면
                                tempCnt=0;
                                nCnt++;
                            } else {
                                tempCnt++;
                            }
                        } else {
                            if (loc > lastCursor) { // 마지막 커서 이후에 값이 존재하면
                                nCnt++;
                            }
                        }
                    }
                };
                // skip
                if(priority > pri){
                    if(pri > nextPriority){
                        nextPriority = pri;
                    }
                }
            }
            nCnt += tempCnt;
            System.out.println(priority + " 까지 총 " + nCnt + " 개");
            priority = nextPriority;
        }
        return nCnt;
    }

    public int solution_others(int[] priorities, int location) {
        int answer=0;

        boolean[] table = new boolean[priorities.length];
        int[] copy_arr = Arrays.copyOf(priorities,priorities.length);

        Arrays.sort(copy_arr);

        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<copy_arr.length;i++){
            stack.push(copy_arr[i]);
        }
//뒤부터 시작
        int point =0;
        for(int k=0;k<priorities.length;k++){
            int num = stack.pop();
            for(int i=0;i<priorities.length;i++){
                int start = i+point;
                while (start>=priorities.length){
                    start=start-priorities.length;
                }
                System.out.println("start = " + start);

                if(priorities[start]==num&&!table[start]){
                    answer++;
                    point=start;
                    table[start]=true;

                    if(start==location){
                        return answer;
                    }

                    break;
                }
            }
        }
        return answer;
    }
}

