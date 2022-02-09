package coldility.exercise;

import java.util.Arrays;
import java.util.HashMap;

public class demo {
    // 실행 메서드
    public static void exec(){
        int s = 0;
        int[] args = {3, 1, 2, 3, 3, 1, 2, 3};
        System.out.println("The answer is >> "+solution(args));
    }

    // 해결방법
    private static int solution(int[] A){
        // 해쉬맵, 해쉬테이블
        HashMap hmap = new HashMap<Integer, Integer>();

        // 아이탬 캐싱
        for(int item : A){
            hmap.put(item, item);
        }

        // 비교문
        for(int ix = 1; ix < 100000; ix++){
            if(hmap.get(ix) == null){
                return ix ;
            }
        }
        return 1;
    }
}
