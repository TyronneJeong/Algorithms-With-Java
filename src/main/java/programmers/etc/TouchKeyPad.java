package programmers.etc;

/**
 * [문제설명]
 * 스마트폰 전화 키패드의 각 칸에 다음과 같이 숫자들이 적혀 있습니다.
 *
 * 이 전화 키패드에서 왼손과 오른손의 엄지손가락만을 이용해서 숫자만을 입력하려고 합니다.
 * 맨 처음 왼손 엄지손가락은 * 키패드에 오른손 엄지손가락은 # 키패드 위치에서 시작하며,
 * 엄지손가락을 사용하는 규칙은 다음과 같습니다.
 *
 * 엄지손가락은 상하좌우 4가지 방향으로만 이동할 수 있으며 키패드 이동 한 칸은 거리로 1에 해당합니다.
 * 왼쪽 열의 3개의 숫자 1, 4, 7을 입력할 때는 왼손 엄지손가락을 사용합니다.
 * 오른쪽 열의 3개의 숫자 3, 6, 9를 입력할 때는 오른손 엄지손가락을 사용합니다.
 * 가운데 열의 4개의 숫자 2, 5, 8, 0을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.
 * 4-1. 만약 두 엄지손가락의 거리가 같다면, 오른손잡이는 오른손 엄지손가락, 왼손잡이는 왼손 엄지손가락을 사용합니다.
 * 순서대로 누를 번호가 담긴 배열 numbers, 왼손잡이인지 오른손잡이인 지를 나타내는 문자열 hand가 매개변수로 주어질 때,
 * 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return 하도록 solution 함수를 완성해주세요.
 *
 * [제한사항]
 * numbers 배열의 크기는 1 이상 1,000 이하입니다.
 * numbers 배열 원소의 값은 0 이상 9 이하인 정수입니다.
 * hand는 "left" 또는 "right" 입니다.
 * "left"는 왼손잡이, "right"는 오른손잡이를 의미합니다.
 * 왼손 엄지손가락을 사용한 경우는 L, 오른손 엄지손가락을 사용한 경우는 R을 순서대로 이어붙여 문자열 형태로 return 해주세요.
 *
 * [입출력 예]
 * numbers                              hand        result
 * [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]   "right"   "LRLLLRLLRRL"
 * [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]   "left"    "LRLLRRLLLRR"
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]      "right"   "LLRLLRLLRL"
 *
 * 입출력 예에 대한 설명
 * 입출력 예 #1
 *
 * 순서대로 눌러야 할 번호가 [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]이고, 오른손잡이입니다.
 *
 * 왼손위치	오른손위치 눌러야할숫자	사용한손   설명
 *    *	       #	     1         L      1은 왼손으로 누릅니다.
 *    1	       #	     3         R      3은 오른손으로 누릅니다.
 *    1	       3	     4         L      4는 왼손으로 누릅니다.
 *    4	       3	     5         L      왼손 거리는 1, 오른손 거리는 2이므로 왼손으로 5를 누릅니다.
 *    5	       3	     8         L      왼손 거리는 1, 오른손 거리는 3이므로 왼손으로 8을 누릅니다.
 *    8	       3	     2         R      왼손 거리는 2, 오른손 거리는 1이므로 오른손으로 2를 누릅니다.
 *    8	       2	     1         L      1은 왼손으로 누릅니다.
 *    1	       2	     4         L      4는 왼손으로 누릅니다.
 *    4	       2	     5         R      왼손 거리와 오른손 거리가 1로 같으므로, 오른손으로 5를 누릅니다.
 *    4	       5	     9         R      9는 오른손으로 누릅니다.
 *    4	       9	     5         L      왼손 거리는 1, 오른손 거리는 2이므로 왼손으로 5를 누릅니다.
 *    5	       9	     -         -
 * 따라서 "LRLLLRLLRRL"를 return 합니다.
 *
 * 입출력 예 #2
 *
 * 왼손잡이가 [7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]를 순서대로 누르면 사용한 손은 "LRLLRRLLLRR"이 됩니다.
 *
 * 입출력 예 #3
 *
 * 오른손잡이가 [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]를 순서대로 누르면 사용한 손은 "LLRLLRLLRL"이 됩니다.
 */
public class TouchKeyPad {
    public void exec(){
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right"; // LRLLLRLLRRL
                               // LRLLLRLLLRR
        System.out.println(solution(numbers, hand));
    }

    public String solution(int[] numbers, String hand) {
        StringBuffer sb = new StringBuffer(numbers.length);
        int lastL = 0;
        int lastR = 0;
        int calcL = 10;
        int calcR = 12;
        for (int i = 0; i < numbers.length; i++) {
            switch(numbers[i]){
                case 2:
                case 5:
                case 8:
                case 0:
                    calcL = calcDistance(numbers[i], lastL);
                    calcR = calcDistance(numbers[i], lastR);

                    System.out.println("index --- >  " + i);
                    System.out.println("Target : >> " +numbers[i]);
                    System.out.println("current position : >> " +lastL + " / " + lastR);
                    System.out.println("calc distant >> " + calcL + " / " + calcR);

                    if(calcL > calcR){ // 오른손이 가까운 경우
                        sb.append("R");
                        lastR = numbers[i];

                        System.out.println("R");
                    } else if(calcL < calcR){ // 왼손이 가까운 경우
                        sb.append("L");
                        lastL = numbers[i];

                        System.out.println("L");
                    } else { // 모두 동일한 경우
                        if(hand.equals("right")){
                            sb.append("R");
                            lastR = numbers[i];

                            System.out.println("SR");
                        } else {
                            sb.append("L");
                            lastL = numbers[i];

                            System.out.println("SL");
                        }
                    }
                    break;
                case 1:
                case 4:
                case 7:
                    sb.append("L");
                    lastL = numbers[i];
                    break;
                case 3:
                case 6:
                case 9:
                    sb.append("R");
                    lastR = numbers[i];
                    break;
            }
        }
        return sb.toString();
    }

    // 서로다를때 동일하게 기준을 맞추고 시작하면 편리한 듯
    private int calcDistance(int target, int position){
        if (target == 0) target = 11;
        if(position == 3 || position == 6 || position == 9){
            position = position - 2;
        }

        if(position == 2 || position == 5 || position == 8 || position == 11){
            position = position - 1;
        }
        int distance = 0;
        int different = Math.abs(target - position);
        if(different < 3){
            distance = 1;
        } else if(different < 6){
            distance = 2;
        } else if(different < 9){
            distance = 3;
        } else {
            distance = 4;
        }
        return distance;
    }
}
