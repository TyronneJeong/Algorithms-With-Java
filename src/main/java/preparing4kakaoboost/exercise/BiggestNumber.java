package preparing4kakaoboost.exercise;

//0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
//
//예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
//
//0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.
//
//제한 사항
//numbers의 길이는 1 이상 100,000 이하입니다.
//numbers의 원소는 0 이상 1,000 이하입니다.
//정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
//입출력 예
//numbers	return
//[6, 10, 2]	"6210"
//[3, 30, 34, 5, 9]	"9534330"

public class BiggestNumber {
    public void exec(){
        int[] numbers = {6, 10, 2};
        System.out.println(solution(numbers));
    }
    private int[] numbers;
    private int[] answer;
    private long biggestNumber;
    public String solution(int[] numbers) {
        this.numbers = numbers;
        this.answer = new int[numbers.length];
        this.biggestNumber = 0L;
        calcBiggestNumber(0);
        return String.valueOf(biggestNumber);
    }

    // 재귀라고 해서 굳이 파라미터를 계속 순환 시킬 필요는 없는 듯.
    private void calcBiggestNumber(int cursor){
        // 종료
        if(numbers.length == cursor){
            this.biggestNumber = compareToEachValues(biggestNumber, answer);
            return;
        }
        // 전체 내용 조합
        for(int ix = 0; ix < numbers.length; ix ++){
            if(!isContains(ix, 0, cursor)){
                answer[cursor] = ix;
                calcBiggestNumber(cursor+1); // 반복 연결 고리
            }
        }
    }

    // 배열내 타겟 넘버의 포함 여부를 검사
    private boolean isContains(int target, int startIdx, int endIdx){
        if(startIdx == endIdx) return false;
        if(this.answer[startIdx] == target) return true;
        return isContains(target, startIdx+1, endIdx);
    }

    // 값의 크기 비교
    StringBuffer sb;
    long compValue;
    private long compareToEachValues(long priorBest, int[] answer){
        sb = new StringBuffer();
        compValue = 0;
        for(int idx : answer){
            sb.append(this.numbers[idx]);
        }
        compValue = Long.parseLong(sb.toString());
        if(priorBest > compValue){
            return priorBest;
        } else {
            return compValue;
        }
    }
}
