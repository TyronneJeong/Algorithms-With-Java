package programmers.kakao;

import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * [문제설명]
 * 카카오톡 게임별의 하반기 신규 서비스로 다트 게임을 출시하기로 했다.
 * 다트 게임은 다트판에 다트를 세 차례 던져 그 점수의 합계로 실력을 겨루는 게임으로, 모두가 간단히 즐길 수 있다.
 * 갓 입사한 무지는 코딩 실력을 인정받아 게임의 핵심 부분인 점수 계산 로직을 맡게 되었다.
 * 다트 게임의 점수 계산 로직은 아래와 같다.
 *
 * 다트 게임은 총 3번의 기회로 구성된다.
 *
 * 각 기회마다 얻을 수 있는 점수는 0점에서 10점까지이다.
 * 점수와 함께 Single(S), Double(D), Triple(T) 영역이 존재하고 각 영역 당첨 시 점수에서 1제곱, 2제곱, 3제곱
 * (점수1 , 점수2 , 점수3 )으로 계산된다.
 *
 * 옵션으로 스타상(*) , 아차상(#)이 존재하며 스타상(*) 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배로 만든다.
 * 아차상(#) 당첨 시 해당 점수는 마이너스된다.
 *
 * 스타상(*)은 첫 번째 기회에서도 나올 수 있다. 이 경우 첫 번째 스타상(*)의 점수만 2배가 된다. (예제 4번 참고)
 *
 * 스타상(*)의 효과는 다른 스타상(*)의 효과와 중첩될 수 있다. 이 경우 중첩된 스타상(*) 점수는 4배가 된다. (예제 4번 참고)
 *
 * 스타상(*)의 효과는 아차상(#)의 효과와 중첩될 수 있다. 이 경우 중첩된 아차상(#)의 점수는 -2배가 된다. (예제 5번 참고)
 *
 * Single(S), Double(D), Triple(T)은 점수마다 하나씩 존재한다.
 *
 * 스타상(*), 아차상(#)은 점수마다 둘 중 하나만 존재할 수 있으며, 존재하지 않을 수도 있다.
 * 0~10의 정수와 문자 S, D, T, *, #로 구성된 문자열이 입력될 시 총점수를 반환하는 함수를 작성하라.
 *
 * [입력형식]
 * "점수|보너스|[옵션]"으로 이루어진 문자열 3세트.
 * 예) 1S2D*3T
 *
 * 점수는 0에서 10 사이의 정수이다.
 * 보너스는 S, D, T 중 하나이다.
 * 옵선은 *이나 # 중 하나이며, 없을 수도 있다.
 * 출력 형식
 * 3번의 기회에서 얻은 점수 합계에 해당하는 정수값을 출력한다.
 * 예) 37
 *
 * [입출력 예제]
 * 예제	dartResult	answer	설명
 * 1	1S2D*3T	37	11 * 2 + 22 * 2 + 33
 * 2	1D2S#10S	9	12 + 21 * (-1) + 101
 * 3	1D2S0T	3	12 + 21 + 03
 * 4	1S*2T*3S	23	11 * 2 * 2 + 23 * 2 + 31
 * 5	1D#2S*3S	5	12 * (-1) * 2 + 21 * 2 + 31
 * 6	1T2D3D#	-4	13 + 22 + 32 * (-1)
 * 7	1D2S3T*	59	12 + 21 * 2 + 33 * 2
 */
public class DartGame {
    public void exec(){
//        String dartResult = "1S2D*3T"; // 37
//        String dartResult = "1D2S#10S"; // 9
//        String dartResult = "1D2S0T"; // 3
        //String dartResult = "1S*2T*3S"; // 23
        String dartResult = "10D"; // 3

        System.out.println(solution(dartResult));
    }
    public int solution(String dartResult) {
        ArrayList<Integer> list = new ArrayList<>();
        int listIdx = 0;
        int value = 0;
        char[] charArr = dartResult.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if(i==0) {
                if(Character.isDigit(charArr[i+1])){
                    list.add(Integer.parseInt("" +charArr[i] + charArr[i+1]));
                    i++;
                } else {
                    list.add(Character.getNumericValue(charArr[i]));
                }
            } else if (Character.isDigit(charArr[i])){
                if(Character.isDigit(charArr[i+1])){
                    list.add(Integer.parseInt("" +charArr[i] + charArr[i+1]));
                    i++;
                } else {
                    list.add(Character.getNumericValue(charArr[i]));
                }
                listIdx++;
            } else if (!Character.isDigit(charArr[i])){
                value = list.get(listIdx);
                switch(charArr[i]){
                    case 'S': // 싱글
                        break;
                    case 'D': // 더블
                        list.set(listIdx, value*value);
                        break;
                    case 'T': // 트리플
                        list.set(listIdx, value*value*value);
                        break;
                    case '*': // 스타상
                        list.set(listIdx, value*2);
                        if(listIdx-1 >= 0){
                            value = list.get(listIdx-1);
                            list.set(listIdx-1, value*2);
                        }
                        break;
                    case '#': // 아차상
                        list.set(listIdx, value*-1);
                        break;
                    default:
                        break;
                }
            }
        }
        return list.stream().mapToInt(i->i).sum();
    }


    // 스택을 이용한 풀이.
    public int solution_stack(String dartResult) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (int i = 0; i < dartResult.length(); ++i) {
            char c = dartResult.charAt(i);
            if (Character.isDigit(c)) {
                sum = (c - '0');
                if (sum == 1 && i < dartResult.length() - 1 && dartResult.charAt(i + 1) == '0') {
                    sum = 10;
                    i++;
                }
                stack.push(sum);
            } else {
                int prev = stack.pop();
                if (c == 'D') {
                    prev *= prev;
                } else if (c == 'T') {
                    prev = prev * prev * prev;
                } else if (c == '*') {
                    if (!stack.isEmpty()) {
                        int val = stack.pop() * 2;
                        stack.push(val);
                    }
                    prev *= 2;
                } else if (c == '#') {
                    prev *= (-1);
                }
                stack.push(prev);
            }
        }
        int totalScore = 0;
        while (!stack.isEmpty()) {
            totalScore += stack.pop();
        }
        return totalScore;
    }

    // 정규표현식을 이용한 풀이
    static int[] sum = new int[3];
    static int solution_regex(String msg){
        String reg = "([0-9]{1,2}[S|T|D][*|#]{0,1})";
        Pattern p = Pattern.compile(reg+reg+reg);
        Matcher m = p.matcher(msg);
        m.find();
        for(int i=1; i<=m.groupCount(); i++){
            // 숫자부와 기호부 를 각 그룹으로 묶어서 호출 함.
            Pattern p1 = Pattern.compile("([0-9]{1,2})([S|T|D])([*|#]{0,1})");
            Matcher m1 = p1.matcher(m.group(i));
            m1.find();
            sum[i-1] = (int)Math.pow(Integer.parseInt(m1.group(1)), getpow(m1.group(2)));
            setting(i,m1.group(3));
        }
        return(sum[0]+ sum[1]+ sum[2]);
    }
    static void setting(int idx, String msg){
        if(msg.equals("*")){
            sum[idx - 1] *= 2;
            if(idx > 1 ){
                sum[idx - 2] *=2;
            }
        }
        else if(msg.equals("#")){
            sum[idx - 1] *=-1 ;
        }
    }
    static int getpow(String mag){
        if(mag.equals("S")){
            return 1;
        }
        else if(mag.equals("D")){
            return 2;
        }
        else {
            return 3;
        }
    }
}
