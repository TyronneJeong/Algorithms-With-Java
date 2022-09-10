package preparing4kakaoboost.codingtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class solve01 {
    public void exec(){
        System.out.println("sovle01");
        int n = 5;
        List<Integer> list = new ArrayList<>(){{
            add(3);
            add(5);
        }};
        solution (6, list);
    }


    private static int totalCnt;
    private static boolean[] infectedYn;
    private static boolean[] infectedYn_BAK;
    private static int loopCnt;
    private static int infectedHousesCnt;
    public static int solution(int n, List<Integer> infectedHouses) {
        loopCnt = n;
        infectedYn = new boolean[n];
        infectedYn_BAK = new boolean[n];
        infectedHousesCnt = infectedHouses.size();

        totalCnt = 1;
        for(Integer idx : infectedHouses){
            infectedYn[idx-1] = true; // infection
            infectedYn_BAK[idx-1] = true; // infection
        }
        for(Integer i : infectedHouses){
            recursion(i-1, 0);
        }

        System.out.println(totalCnt);
        return totalCnt;
    }

    // visitYn
    static int leftIdx = 0;
    static  int rightIdx = 0;
    private static void recursion(int idx, int step){
        if(step + 1 == loopCnt - (infectedHousesCnt)){ // all blank fileds are filled up.
            totalCnt++;
            return;
        }
        // 초기화
        if(step == 0){
            infectedYn = Arrays.copyOf(infectedYn_BAK, loopCnt);
        }
        infectedYn[idx] = true;
        System.out.println("\n\n마스킹 여부 확인 " + Arrays.toString(infectedYn));
        System.out.println(idx + "진입" + "step 은 " + step);

        // turn left
        leftIdx = calcPosition(idx, -1);
        rightIdx = calcPosition(idx, +1);
        System.out.println("현재 커서 위치는 "+idx+" 이며 여기서 왼쪽은 "+leftIdx+" 과 오른 쪽은" + rightIdx);

        if(leftIdx != -1){
            recursion(leftIdx, step+1);
        }
        // turn right
        if(rightIdx != -1){
            recursion(rightIdx, step+1);
        }
    }
    // 현재 포지션에서 왼쪽 또는 오른쪽
    private static int calcPosition(int idx, int direction){
        if(direction < 0){ // 왼쪽
            for(int ix = 1; ix <= idx; ix++){
                if(infectedYn[idx - ix] != true) return idx - ix;
            }
        }
        else if(direction > 0){ // 오른쪽
            for(int ix = 1; ix < loopCnt - idx; ix++){
                if(infectedYn[idx + ix] != true) return idx + ix;
            }
        }
        return -1;
    }
}
