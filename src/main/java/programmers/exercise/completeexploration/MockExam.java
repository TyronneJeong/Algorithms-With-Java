package programmers.exercise.completeexploration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * [문제설명]
 * 수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
 *
 * 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 * 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 * 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
 *
 * 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때, 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 *
 * [제한조건]
 * 시험은 최대 10,000 문제로 구성되어있습니다.
 * 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
 * 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
 *
 * [입출력 예]
 * answers	return
 * [1,2,3,4,5]	[1]
 * [1,3,2,4,2]	[1,2,3]
 *
 * [입출력 예 설명]
 * 입출력 예 #1
 *
 * 수포자 1은 모든 문제를 맞혔습니다.
 * 수포자 2는 모든 문제를 틀렸습니다.
 * 수포자 3은 모든 문제를 틀렸습니다.
 * 따라서 가장 문제를 많이 맞힌 사람은 수포자 1입니다.
 *
 * 입출력 예 #2
 *
 * 모든 사람이 2문제씩을 맞췄습니다.
 */
public class MockExam {
    public void exec(){
//        int[] answers = {1,2,3,4,5}; // 1
        int[] answers = {1,3,2,4,2}; // 1
        
        Arrays.stream(solution(answers)).forEach(e->{
            System.out.println("result is ");
            System.out.println(e);
        });
    }

    // 완전 탐색을 사용한다는 기준에서는 쉬운 문제라는 의미.
    public int[] solution(int[] answers) {
        int loopCnt = answers.length;
        int[][] patterns = new int[3][loopCnt];

        // 1, 2, 3, 4, 5 패턴
        for (int i = 0; i < loopCnt; i++) {
            patterns[0][i] = i%5+1;
        }

        // 2, 1, 2, 3, 2, 4, 2, 5 패턴
        int[] repeatNumbers = {1, 3, 4, 5};
        int point = 0;
        for (int i = 0; i < loopCnt; i++) {
            if(i % 2 != 0){ // index가 홀수 일때만 1, 3, 4, 5
                patterns[1][i] = repeatNumbers[point];
                point = (point + 1) % 4;
            } else {
                patterns[1][i] = 2;
            }
        }

        // 33, 11, 22, 44, 55 패턴
        int[] repeatNumbers2 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int point2 = 0;
        for (int i = 0; i < loopCnt; i++) {
            patterns[2][i] = repeatNumbers2[point2];
            point2 = (point2 + 1) % 10;
        }

        // 매칭결과작성
        int[] score = new int[3];
        for (int i = 0; i < loopCnt; i++) {
            if(answers[i] == patterns[0][i]){
                score[0]++;
            }
            if(answers[i] == patterns[1][i]){
                score[1]++;
            }
            if(answers[i] == patterns[2][i]){
                score[2]++;
            }
        }

        // 리턴 배열 작성
        ArrayList<Integer> ar = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if(i == 0) {
                ar.add(0, i+1);
            } else if(score[ar.get(0)-1] < score[i]){
                ar.removeAll(ar);
                ar.add(0, i+1);
            } else if(score[ar.get(0)-1] == score[i]){
                ar.add(i+1);
            }
        }
        return ar.stream().mapToInt(Integer::intValue).toArray();
    }
}
