package programmers.naverfinancial;

import java.util.Arrays;
import java.util.HashMap;

/**
 * [문제]
 * 주어진 명단은 매장에서 물건을 구매한 고객명단이다.
 * 물건을 구매한 고객들에게는 10%의 할인 쿠폰이 주어지는데 1일당 2매의 제한이 있고
 * 1인당 총 k 만큼의 발급제한이 있다.
 *
 * 그렇다면 주어진 고객명단을 확인한 결과 발급된 총 할인 쿠폰의 매수는 몇매인지를 리턴하여라.
 */
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
