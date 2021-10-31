package programmers.naverfinancial;

/**
 * [문제]
 * 입력값에 n 에 따라 n*n 크기의 행렬이 주어지고 이는 시계방향으로 돌면서 값을 기입한다.
 * 이때, jump 에 해당하는 구간 만큼 건너서 수를 입력하여야 하며
 * 최종 n*n-1 번째 항목의 y, x 위치를 리턴 하여라.
 */
public class AlgorithmTest_02 {
    public void exec(){
        System.out.println("------------문제 2번 시작------------");
        int n = 3;
        int jump = 3;
        solution(n, jump); // 2,1
//        Arrays.stream(solution(value)).forEach(e-> System.out.loopCntln(e));
        System.out.println("------------문제 2번 종료------------");
    }

    public int[] solution(int n, int jump) {
        int[] answer = {0, 0};
        int[][] arr = new int[n][n];
        int loopCnt = n;

        int rPadding = -1;
        int btPadding = 0;
        int topPadding = 1;

        int numb = 1;
        int tails = 1;

        int cycle = 2;

        int margin = 0;
        while(cycle > 0) {
            // 1회차
            for (int i = loopCnt; i > 0; i--) {
                for (int j = 0; j < loopCnt; j++) {
                    rPadding += topPadding;
                    if (arr[btPadding][rPadding] == 0) {
                        if ((tails+margin) % jump == 0) {
                            arr[btPadding][rPadding] = numb;
                            numb++;
                        }
                    }
                    tails++;
                }
                loopCnt--;
                for (int j = 0; j < loopCnt; j++) {
                    btPadding += topPadding;

                    if (arr[btPadding][rPadding] == 0) {
                        if ((tails+margin) % jump == 0) {
                            arr[btPadding][rPadding] = numb;
                            numb++;
                        }
                    }
                    tails++;
                }
                topPadding = topPadding * (-1);
            }
            System.out.println(cycle);
            cycle--;
        }

        
        System.out.println(tails);
        // 출력
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr[i].length; j++) {
                System.out.printf("%2d ", arr[i][j]);
            }
            System.out.println();
        }


        return answer;
    }
}
