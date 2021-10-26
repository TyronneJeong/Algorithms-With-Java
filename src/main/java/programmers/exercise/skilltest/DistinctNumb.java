package programmers.exercise.skilltest;

import java.util.ArrayList;
import java.util.Arrays;

public class DistinctNumb {
    public void exec(){
        int[] arr= {1,1,3,3,0,1,1};
        Arrays.stream(solution(arr)).forEach(e->{
            System.out.println(e);
        });

    }

    // 연속으로 나열된 숫자를 제거하기
    public int[] solution(int []arr) {
        ArrayList<Integer> ar = new ArrayList<>();
        int prevNumb = 99999999;
        for (int i = 0; i < arr.length; i++) {
            if(prevNumb == arr[i]) continue;
            ar.add(arr[i]);
            prevNumb = arr[i];
        }
        // return ar.stream().mapToInt(i->i).toArray();
        return ar.stream().mapToInt(Integer::intValue).toArray();
    }
}
