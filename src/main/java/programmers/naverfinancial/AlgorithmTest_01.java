package programmers.naverfinancial;

import java.util.Arrays;
import java.util.HashMap;

public class AlgorithmTest_01 {
    public void exec(){
        System.out.println("------------문제 1번 시작------------");


//        String[] id_list = {"A B C D", "A D", "A B D", "B D"};
//        int k = 2; // 7이 정답
        String[] id_list = {"JAY", "JAY ELLE JAY MAY", "MAY ELLE MAY", "ELLE MAY", "ELLE ELLE ELLE", "MAY"};
        int k = 3; // 8이 정답



        System.out.println(solution(id_list, k));



        System.out.println("------------문제 1번 종료------------");
    }

    public int solution(String[] id_list, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] dailyCustomers = {};
        for (int i = 0; i < id_list.length; i++) {
            dailyCustomers = Arrays.asList(id_list[i].split(" "))
                    .stream()
                    .distinct()
                    .toArray(String[]::new);

            for (int j = 0; j < dailyCustomers.length; j++) {
                if(map.containsKey(dailyCustomers[j])){
                    if(map.get(dailyCustomers[j]) < k){
                        map.put(dailyCustomers[j], map.get(dailyCustomers[j])+1);
                    }
                } else {
                    map.put(dailyCustomers[j], 1);
                }
            }
        }
        return map.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
