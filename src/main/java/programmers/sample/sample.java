package programmers.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class sample {
    public void exec(){
        List<Integer> list = new ArrayList<>();


        list.add(3);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(2);


        solution(list, 2);
    }

    public int solution(List<Integer> arr, int x) {
        HashMap hMap = new HashMap();

        // 가장 작은 수 찾기
        // 정렬
        // 디스팅트

        arr = arr.stream().sorted().distinct().collect(Collectors.toList());
        int answer = 0;
        boolean exists = false;
        for(int ix=0; ix<=10000;ix++){
            exists = false;

            for(Integer i : arr){
                if(Integer.valueOf(ix) == i){
                    exists = true;
                    break;
                }
                if(Integer.valueOf(ix) == i+x){
                    exists = true;
                    break;
                }
                if(Integer.valueOf(ix) == i-x){
                    exists = true;
                    break;
                }
            }
            if(!exists) {
                System.out.println(ix);
                answer = ix;
                break;
            }
        }

        return 0;
    };
}


