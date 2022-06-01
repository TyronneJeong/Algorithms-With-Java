package preparing4kakaoboost.dfsbfs;

import java.util.Arrays;

//문제 설명
//n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다. 예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 하도록 solution 함수를 작성해주세요.
//
//제한사항
//주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
//각 숫자는 1 이상 50 이하인 자연수입니다.
//타겟 넘버는 1 이상 1000 이하인 자연수입니다.
//입출력 예
//numbers	target	return
//[1, 1, 1, 1, 1]	3	5
//[4, 1, 2, 1]	4	2
//입출력 예 설명
//입출력 예 #1
//
//문제 예시와 같습니다.
//
//입출력 예 #2
//
//+4+1-2+1 = 4
//+4-1+2-1 = 4
//총 2가지 방법이 있으므로, 2를 return 합니다.

public class TargetNumber {
    public void exec() {
        int[] numbers; int target;
        numbers = new int[]{1, 1, 1, 1, 1};
        target = 3;
        // 5

        numbers = new int[]{1, 1, 1, 1, 1};
        target = 3;
        // 5

        numbers = new int[]{1, 1, 1, 1, 1};
        target = 3;
        // 5

        solution(numbers, target);
//        System.out.println(solution(numbers, target));
    }



    private int[] numbers;
    private int target;
    private int cnt = 0;
    // 완전탐색문제
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        findTargetNumber(0, 0);

        int width = 4;
        this.answer = new int[width];
        printAllNumbers(width, 0);
        int answer = this.cnt;
        return answer;
    }

    // recursion 의 단점
    // 함수 호출에 따른 오버헤드 발생
    // 매개변수 전달 및 액티베이션 프레임 생성 등 -> 맴버변수 선언으로 해결
    private void findTargetNumber(int cursor, int sum){
        // 탈출조건
        if(cursor == this.numbers.length) {
            if(sum == this.target){
                this.cnt++;
            }
            return ; // base case
        }
        findTargetNumber(cursor + 1, sum + this.numbers[cursor] );
        findTargetNumber(cursor + 1, sum - this.numbers[cursor] );
    }


    // 숫자조합 다 생성하기
    // 겹치는 버전 vs 안 겹치는 버전
    private int[] answer;
    private int printAllNumbers(int width, int ix){
        // 원하는 목표 점 (DFS 의 깊이 마지막)
        if(ix == width){
            System.out.println(Arrays.toString(answer)); // 조합된 결과 출력
            return 0;
        }
        // 발산형 조건 들
        for( int iy = 0; iy < width; iy++ ){
            // 조건에 따른 진행 여부 - 가지를 분기 할 수 있다.
            // [문제에서 동일 숫자는 하나의 숫자로 본다는 조건이 들어가는 경우가 있음.]
            if(!isContains(iy, 0, ix)){
                answer[ix] = iy;
                printAllNumbers(width, ix+1); // 반복 연결 고리
            }
        }
        return -1;
    }

    // 배열내 타겟 넘버의 포함 여부를 검사
    private boolean isContains(int target, int startIdx, int endIdx){
        if(startIdx == endIdx) return false;
        if(this.answer[startIdx] == target) return true;
        return isContains(target, startIdx+1, endIdx);
    }
}
