package programmers2.hanhwalife;

import java.util.ArrayList;

public class Quest02 {
    // 실행 메서드
    public void exec(){
        System.out.println("~~ Question 02 Start ~~");
        // variables
        int[] scores = {1, 3, 7, 8, 10, 15};
        int k = 3;

        // [1,2,12,14,15]	K=2,	Ans = 4
        // solution
        System.out.println("리턴 결과 ==> " + solution(scores, k)); // 답은 5가 나와야 함
        System.out.println("\n\n~~ Question 02 End ~~\n\n");
    }

    private int solution(int[] scores, int k){

        // 시작인덱스는 1
        int loopCnt = scores.length;
        StringBuffer sb = new StringBuffer();

        System.out.println(recursionSum(scores, k));
        return 0;
    }

    // k 개의 조합 가능한 모든 수를 찾자. 전역 변수를 이용하여 믹싱 가능?
    private String recursionSum(int[] scores, int k){
        if(k == 0) return "";
        int loopCnt = k;

        for(int ix = 0; ix < loopCnt; ix++){
            System.out.print(ix);
            recursionSum(scores, --k);
        }
        System.out.println();
        return null;
    }

    private int sumBetweenFromAndTo(int[] arr, int fromIdx, int toIdx){
        return Math.abs(arr[fromIdx] - arr[toIdx]);
    }
}
