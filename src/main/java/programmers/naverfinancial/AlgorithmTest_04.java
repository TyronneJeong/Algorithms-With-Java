package programmers.naverfinancial;

/**
 * 주어지는 배열 값은 n*n 행렬의 지도 이다.
 * 왼쪽 상단 에서 시작하여 오른쪽 하단으로 향하는 경로중
 * 가장 비용이 적게 드는 경로를 찾고 그 결과값을 리턴 하시오.
 *
 * 단 0을 만나면 부호가 반대가 된다.
 */
public class AlgorithmTest_04 {
    public void exec(){
        System.out.println("------------문제 4번 시작------------");
//        int[][] board = {{1, -7, -2, 1, -1},{2, 3, 0, -1, -2},{1, -1, 6, -1, -2},{-1, 1, -2, 0, 4},{-10, 5, -3, -1, 1}};
        int[][] board = {{-10, 20, 30},{-10, 0, 10},{-20, 40, 1}}; // 61 점
        System.out.println(solution(board));
        System.out.println("------------문제 4번 종료------------");
    }

    public int solution(int[][] board) {
        int answer = 0;
        return answer;
    }
}
