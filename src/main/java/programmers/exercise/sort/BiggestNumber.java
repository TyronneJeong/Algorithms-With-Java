package programmers.exercise.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * [문제설명]
 * 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
 *
 * 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고,
 * 이중 가장 큰 수는 6210입니다.
 *
 * 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때,
 * 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
 *
 * [제한사항]
 * numbers의 길이는 1 이상 100,000 이하입니다.
 * numbers의 원소는 0 이상 1,000 이하입니다.
 * 정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
 *
 * [입출력 예]
 * numbers                return
 * [6, 10, 2]              "6210"
 * [3, 30, 34, 5, 9]    "9534330"
 */
public class BiggestNumber {
    public void exec(){
//        int[] numbers = {3, 30, 34, 5, 9}; // 9534330
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 21, 31, 41, 51, 61, 71, 81, 91};
//        int[] numbers = {1, 2, 3,  9,  91};
        System.out.println(solution(numbers));
    }

    // 미결 - 테스트 케이스 에서 부분 실패
    public String solution(int[] numbers) {
        String answer = "";
        final char[] sam = new char[1];
        String[] strArray = Arrays.stream(numbers).mapToObj(String::valueOf).toArray(String[]::new);
        Arrays.sort(strArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int rtnVal = 0;
                if(o1.charAt(0) == o2.charAt(0)){
                    if(o1.replaceFirst("0++$", "")
                            .equalsIgnoreCase(o2.replaceFirst("0++$", ""))){
                        if(o1.length() < o2.length()){
                            rtnVal = -1;
                        } else if(o1.length() == o2.length()){
                            rtnVal = 0;
                        } else {
                            rtnVal = 1;
                        }
                    } else {
                        if(o1.length() > o2.length()){
                            sam[0] = o1.charAt(o2.length());
                            if(o2.charAt(0) < sam[0]){
                                rtnVal = o2.compareTo(o1);
                            } else {
                                rtnVal = o1.compareTo(o2);
                            }
                        } else {
                            rtnVal = o2.compareTo(o1);
                        }
                    }
                } else {
                    rtnVal = o2.compareTo(o1);
                }
                return rtnVal;
            }
        });
        for(String item : strArray){
            answer += item;
        }
        return answer;
    }
}
