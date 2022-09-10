package preparing4kakaoboost.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HIndex {

    public void exec(){
        int[] citations = {3, 0, 6, 1, 5}; // 3
        citations = new int[]{10, 10, 10, 10, 10}; // 5
        citations = new int[]{0, 0, 0, 0, 0}; // 0
        System.out.println(solution(citations));
    }

    public int solution(int[] citations) {
        List<Integer> list = Arrays.stream(citations).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        int hIdx= 0; for( int ix = 0 ; ix < list.size(); ix ++ ) if( ix+1 <= list.get(ix) ) hIdx = ix + 1 ; return hIdx;
    }
}
