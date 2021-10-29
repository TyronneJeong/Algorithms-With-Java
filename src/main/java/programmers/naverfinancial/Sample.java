package programmers.naverfinancial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Sample {
    public void exec(){
        int[][] v = {{1, 4}, {3, 4}, {3, 10}};
        Arrays.stream(solution(v)).forEach(e-> System.out.println(e));
    }

    public int[] solution(int[][] v) {
        HashMap<Integer, Integer> mapX = new HashMap<>();
        HashMap<Integer, Integer> mapY = new HashMap<>();
        for (int i = 0; i < v.length; i++) {
            if(mapX.containsKey(v[i][0])){
                mapX.put(v[i][0], mapX.get(v[i][0])+1);
            } else {
                mapX.put(v[i][0], 1);
            }

            if(mapY.containsKey(v[i][1])){
                mapY.put(v[i][1], mapY.get(v[i][1])+1);
            } else {
                mapY.put(v[i][1], 1);
            }
        }
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        mapY.forEach((K, V)->{
           if(V == 1){
               y.set(K);
           }
        });
        mapX.forEach((K, V)->{
            if(V == 1){
                x.set(K);
            }
        });
        return new int[]{x.get(), y.get()};
    }
}
