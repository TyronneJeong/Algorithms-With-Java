package coldility.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class question03 {
    // 실행 메서드
    public static void exec(){
        int[] s = {1, 2, 1};
        System.out.println("The answer is >> "+solution(s));
    }

    // 해결방법
    private static int solution(int[] A){
        // collecting duplicated number to list
        HashMap hmap = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        for(int ix=0; ix < A.length; ix++){
            if(hmap.get(A[ix]) != null){
                list.add(A[ix]);
            }
            hmap.put(A[ix], A[ix]);
        }

        // sorting in list item for ascending
        list = list.stream().sorted().collect(Collectors.toList());
        int idx = 0;
        int repeatedCnt = 0;
        for(int ix=1; ix<1000000000; ix++){
            if(list.size() > idx){
                if(hmap.get(ix) == null){
                    repeatedCnt += Math.abs(list.get(idx) - ix);
                    idx++;
                }
            } else {
                break;
            }
        }
        return repeatedCnt;
    }
}
