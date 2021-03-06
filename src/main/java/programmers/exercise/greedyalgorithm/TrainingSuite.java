package programmers.exercise.greedyalgorithm;

import java.util.Arrays;

/**
 * [문제설명]
 * 점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다. 다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
 * 학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다. 예를 들어,
 * 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다. 체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히
 * 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.
 *
 * 전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost, 여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가
 * 매개변수로 주어질 때, 체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.
 *
 * [제한사항]
 * 전체 학생의 수는 2명 이상 30명 이하입니다.
 * 체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
 * 여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
 * 여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다. 이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이
 * 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
 *
 * [입출력 예]
 * n	lost	reserve	return
 * 5	[2, 4]	[1, 3, 5]	5
 * 5	[2, 4]	[3]	4
 * 3	[3]	[1]	2
 *
 * [입출력 예 설명]
 * 예제 #1
 * 1번 학생이 2번 학생에게 체육복을 빌려주고, 3번 학생이나 5번 학생이 4번 학생에게 체육복을 빌려주면 학생 5명이 체육수업을 들을 수 있습니다.
 *
 * 예제 #2
 * 3번 학생이 2번 학생이나 4번 학생에게 체육복을 빌려주면 학생 4명이 체육수업을 들을 수 있습니다.
 */
public class TrainingSuite {
    public void exec(){
//        int n = 30;
//        int[] lost = {2, 3, 6, 11, 14, 20, 24}; // 2번 학생에게 빌려버림
//        int[] reserve = {3, 4}; // 3번은 여벌이 있다.

        int n = 5;
        int[] lost = {4, 2}; // 2번 학생에게 빌려버림
        int[] reserve = {3, 5}; // 3번은 여벌이 있다.

        System.out.println(solution(n, lost, reserve));;
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int lostLen = lost.length; // 도난당한 학생수
        int reserveLen = reserve.length ; // 여별의 체육복이 있는 학생수
        int saveCnt = 0;
        int errCnt = 0;

        lost = Arrays.stream(lost).sorted().toArray();
        reserve = Arrays.stream(reserve).sorted().toArray();

        for (int i = 0; i < lostLen; i++) {
            for (int j = 0; j < reserveLen; j++) {
                if(lost[i] == reserve[j]) {
                    lost[i] = -3;
                    reserve[j] = -1;
                    errCnt++;
                    break;
                }
            }
        }

        for (int i = 0; i < lostLen; i++) {
            for (int j = 0; j < reserveLen; j++) {
                if(reserve[j] == -1) continue;
                if(lost[i]-1 == reserve[j]){
                    saveCnt++;
                    reserve[j]=-1;
                    break;
                } else if(lost[i]+1 == reserve[j]){
                    saveCnt++;
                    reserve[j]=-1;
                    break;
                }
            }
        }
        return (n - (lostLen-errCnt) + saveCnt);
    }

    // -1 인 사람과 +1 점수를 각 구하여
    // 매칭되는 결과를 찾은 듯.
    public int solution_other(int n, int[] lost, int[] reserve) {
        int[] people = new int[n];
        int answer = n;

        for (int l : lost)
            people[l-1]--;
        for (int r : reserve)
            people[r-1]++;

        for (int i = 0; i < people.length; i++) {
            if(people[i] == -1) {
                if(i-1>=0 && people[i-1] == 1) {
                    people[i]++;
                    people[i-1]--;
                }else if(i+1< people.length && people[i+1] == 1) {
                    people[i]++;
                    people[i+1]--;
                }else
                    answer--;
            }
        }
        return answer;
    }
}
