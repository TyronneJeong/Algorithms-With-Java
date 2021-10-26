package programmers.exercise.skilltest;

public class NoMoreMath {
    public void exec(){
        int[] asd = {1,2,3,4,5, 3, 2, 1, 3, 4};
        System.out.println(solution(asd));
    }

    public int[] solution(int[] answers) {
        int loopCnt = answers.length;
        int[] answer;

        int[] memberA = new int[loopCnt];
        int[] memberB = new int[loopCnt];
        int[] memberC = new int[loopCnt];

        int scoreA = 0;
        int scoreB = 0;
        int scoreC = 0;

        // A 패턴
        for (int i = 0; i < loopCnt; i++) {
            memberA[i] = i % 5 + 1;
        }

        // B패턴
        int[] source = {1, 3, 4, 5};
        int sourceCnt = 0;
        for (int i = 0; i < loopCnt; i++) {
            if(i % 2 == 0){
                memberB[i] = 2;
            } else {
                memberB[i] = source[sourceCnt%4];
                sourceCnt++;
            }
        }

        // C 패턴
        int[] source2 = {3, 1, 2, 4, 5};
        int source2Cnt = 0;
        for (int i = 0; i < loopCnt; i++) {
            if(i % 2 == 0){
                memberB[i] = source2[source2Cnt%5];
            } else {
                memberB[i] = source2[source2Cnt%5];
                source2Cnt++;
            }
        }

        for (int i = 0; i < loopCnt; i++) {
            if(answers[i] == memberA[i]){
                scoreA++;
            }
            if(answers[i] == memberB[i]){
                scoreB++;
            }
            if(answers[i] == memberC[i]){
                scoreC++;
            }
        }

        // scoring
        if(scoreA > scoreB){
            if(scoreA > scoreC){
                answer = new int[1];
                answer[0] = 1;
            } else if(scoreA == scoreC){
                answer = new int[2];
                answer[0] = 1;
                answer[1] = 3;
            } else {
                answer = new int[1];
                answer[0] = 3;
            }
        } else {
            if(scoreB > scoreC){
                answer = new int[1];
                answer[0] = 2;
            } else if(scoreB == scoreC){
                answer = new int[2];
                answer[0] = 2;
                answer[1] = 3;
            } else {
                answer = new int[1];
                answer[0] = 3;
            }
        }

        if(scoreB > scoreC){
            if(scoreB > scoreA){
                answer = new int[1];
                answer[0] = 2;
            } else if(scoreB == scoreA){
                answer = new int[2];
                answer[0] = 2;
                answer[1] = 3;
            } else {
                answer = new int[1];
                answer[0] = 1;
            }
        } else {
            if(scoreA < scoreC){
                answer = new int[1];
                answer[0] = 3;
            } else if(scoreA == scoreC){
                answer = new int[2];
                answer[0] = 1;
                answer[1] = 3;
            } else {
                answer = new int[1];
                answer[0] = 1;
            }
        }

        if(scoreC > scoreA){
            if(scoreC > scoreA){
                answer = new int[1];
                answer[0] = 3;
            } else if(scoreC == scoreA){
                answer = new int[2];
                answer[0] = 1;
                answer[1] = 3;
            } else {
                answer = new int[1];
                answer[0] = 1;
            }
        } else {
            if(scoreC < scoreA){
                answer = new int[1];
                answer[0] = 1;
            } else if(scoreC == scoreA){
                answer = new int[2];
                answer[0] = 1;
                answer[1] = 3;
            } else {
                answer = new int[1];
                answer[0] = 3;
            }
        }

        if(scoreA == scoreB && scoreB == scoreC){
            answer = new int[3];
            answer[0] = 1;
            answer[1] = 2;
            answer[2] = 3;
        }
        return answer;
    }
}
