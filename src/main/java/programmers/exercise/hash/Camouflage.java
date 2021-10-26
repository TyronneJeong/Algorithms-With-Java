package programmers.exercise.hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * [문제 설명]
 * 스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.
 * 예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.
 *
 * [종류][이름]
 * 얼굴	동그란 안경, 검정 선글라스
 * 상의	파란색 티셔츠
 * 하의	청바지
 * 겉옷	긴 코트
 *
 * 스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.
 *
 * [제한사항]
 * clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
 * 스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
 * 같은 이름을 가진 의상은 존재하지 않습니다.
 * clothes의 모든 원소는 문자열로 이루어져 있습니다.
 * 모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
 * 스파이는 하루에 최소 한 개의 의상은 입습니다.
 *
 * [입출력 예]
 * clothes	                                                                                  return
 * {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}	     5
 * {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}	                 3
 */
public class Camouflage {
    public void exec(){
//        String[][] strArr = {{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}};
//        String[][] strArr = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};

        String[][] strArr = {{"crowmask", "A"},{"crowmask", "A"},{"crowmask", "A"},{"bluesunglasses", "B"},{"bluesunglasses", "B"},{"smoky_makeup", "C"}};

//        String[][] strArr = {{"crowmask", "A"},{"crowmask", "A"},{"crowmask", "A"},
//                {"bluesunglasses", "B"},{"bluesunglasses", "B"},
//                {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"}, {"smoky_makeup", "C"},
//                {"smoky_makeup", "D"},{"smoky_makeup", "D"},{"smoky_makeup", "D"},{"smoky_makeup", "D"},{"smoky_makeup", "D"},{"smoky_makeup", "D"},{"smoky_makeup", "D"}
//        };

//        String[][] strArr = {{"crowmask", "A"},
//                {"crowmask", "B"},
//                {"crowmask", "C"},
//                {"crowmask", "D"},
//                {"crowmask", "A"},
//                {"crowmask", "B"},
//                {"crowmask", "C"},
//                {"crowmask", "D"},
//                {"crowmask", "A"},
//                {"crowmask", "B"},
//                {"crowmask", "C"},
//                {"crowmask", "D"},
//                {"crowmask", "A"},
//                {"crowmask", "B"},
//                {"crowmask", "C"},
//                {"crowmask", "D"},
//                {"crowmask", "A"},
//                {"crowmask", "B"},
//                {"crowmask", "C"},
//                {"crowmask", "D"},
//                {"crowmask", "A"},
//                {"crowmask", "B"},
//                {"crowmask", "C"},
//                {"crowmask", "D"},
//                {"crowmask", "A"},
//                {"crowmask", "B"},
//                {"crowmask", "C"},
//                {"crowmask", "D"}
//        };


        System.out.println(solution(strArr));
    }

    public int solution(String[][] clothes) {
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            if(hm.containsKey(clothes[i][1])){
                hm.put(clothes[i][1], hm.get(clothes[i][1]) + 1);
            } else {
                hm.put(clothes[i][1], 1);
            }
        }
        int answer = 0;
        int cnt = 0;

        int val = 0; // 대입 값
        int a = 0; // 덧셈
        int b = 0; // 곱셈
        Iterator it = hm.keySet().iterator();
        while(it.hasNext()){
            val = hm.get(it.next());
            a = a + val;
            switch(cnt){
                case 0:
                    b = val;
                    cnt = 1;
                    break;
                case 1:
                    b *= val;
                    cnt = 3;
                    break;
                default:
                    cnt += 3;
                    b += (val * cnt) + 1;
                    break;
            }
        }

        if(hm.keySet().size() > 1){
            answer = a + b;
        } else {
            answer = a;
        }
        return answer;
    }

// 산술적인 계산 로직 (중복된 착용이 계산 누락 됨)
//    public int solution(String[][] clothes) {
//        HashMap<String, Integer> hm = new HashMap<>();
//        for (int i = 0; i < clothes.length; i++) {
//            if(hm.containsKey(clothes[i][1])){
//                hm.put(clothes[i][1], hm.get(clothes[i][1]) + 1);
//            } else {
//                hm.put(clothes[i][1], 1);
//            }
//        }
//        AtomicInteger single = new AtomicInteger();
//        AtomicInteger multiply  = new AtomicInteger();
//
//        int answer = 0;
//        AtomicInteger count = new AtomicInteger();
//        if(hm.keySet().size() > 1) {
//            hm.forEach((K, V) -> {
//                single.set(single.get() + V);
//                if(count.get() == 0){
//                    multiply.set(V);
//                    count.addAndGet(1);
//                } else {
//                    multiply.set(multiply.get() * V * count.get());
//                    count.addAndGet(2);
//                }
//            });
//            answer = single.get() + multiply.get();
//        } else {
//            hm.forEach((K, V) -> {
//                multiply.set(1 * V);
//            });
//            answer = multiply.get();
//        }
//        return answer;
//    }


//    public int solution(String[][] clothes) {
//        HashMap<String, Integer> hm = new HashMap<>();
//        for (int i = 0; i < clothes.length; i++) {
//            if(hm.containsKey(clothes[i][1])){
//                hm.put(clothes[i][1], hm.get(clothes[i][1]) + 1);
//            } else {
//                hm.put(clothes[i][1], 1);
//            }
//        }
//
//        int answer = 0;
//        int cnt = 0;
//        int[] cntArr = {0, 1, 3, 7};
//
//        int val = 0; // 대입 값
//        int a = 0; // 덧셈
//        int b = 0; // 곱셈
//        Iterator it = hm.keySet().iterator();
//        while(it.hasNext()){
//            String key = (String)it.next();
//            val = hm.get(key);
//            a = a + val;
//            if(cnt == 0){
//                b = val;
//            } else if(cnt == 1){
//                b = b * val;
//            } else {
//                b = b + (val * cntArr[cnt]) + 1;
//            }
//            cnt += 1;
//        }
//        if(hm.keySet().size() > 1){
//            answer = a + b;
//        } else {
//            answer = a;
//        }
//        return answer;
//    }
}
