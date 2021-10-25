package analysisofalgorithms.exercise;

import org.w3c.dom.ls.LSOutput;

import java.util.StringTokenizer;

/**
 * 멱집합과 순열
 * 멱집합과 순열은 문제해결 방식에 있어 가능한 모든 수를 나열 한 후 찾고자 하는 답을 선택 하는 방식의 대표적인 알고리즘 풀이법으로
 * 멱집합은 순서가 무관 한 경우(원소가 등장 하기만 하는 모든 경우의 수),
 * 순열은 순서에 상관 관계를 지닌 경우 (원소의 위치별로 등장 하는 모든 경우의 수) 를 찾는 대표적인 알고리즘 풀이법이다.
 *
 * 상태공간트리(state space tree)
 * - 문제를 해결하기 위한 모든 경우의 수를 나열한 트리를 의미한다. search tree
 *
 * 모든 가능한 수를 나열한 후 원하는 답을 찾는 방식의 문제의 대표적인 예로
 * - 배낭문제 와 TSP 가 있다.
 *
 * 배낭문제 (Knapsack Problem) = O(2^n) = 지수시간 exp
 * - n 개의 아이탬 (무게와 가격속성을 가진 아이템) 배낭의 용량을 초과하지 않으면서 가격의 합이 최대가 되도록 아이탬을 담으려면?
 * - n 개의 아이탬으로 구성된 모든 조합을 나열할 후, 가격의 합이 최대가 되는 조합을 선택하면 된다.
 * - 이때 n 개의 아이탬으로 조합가능한 모든 집합(멱집합)을 구하는 시간 복잡도가 O(2^n)이 된다.
 *
 * TSP (Traveling Salesperson's Problem)
 * - 세일즈맨이 방문지를 여행하는 순서를 어떤식으로 구성을 해야 최대의 효율을 보이는지를 찾는 문제.
 * - 가능한 모든 경로를 도출 한 후, 가장 시간이 적게 걸리는 루트를 찾으면 된다.
 * - 이때 n개의 경로들 마다 상대 지점으로 가는 모든 거리값이 지수로 나눠지며, 그 경로의 수는 O(2^n)개에 해당된다.
 */
public class L06Permutation {
    public void solution(){
        char[] charArr = {'a', 'b', 'c'};
        String sol = permutation(charArr, 0);
        System.out.println(sol);
    }

    /**
     * 순열[Permutation]
     * 서로 다른 n개의 원소들 중 r 개를 골라 순서를 고려해 나열한 경우의 수.
     *
     * - 알고리즘 동작 원리
     * 4개의 원소가 있는 집합에서 조합 가능한 모든 경우의 수는.
     * 4 = 4개의 각 항목 모두 첫번째 자리에 위치 할 수 있다.
     * 3 = 첫번째 위치한 항목이 어느것이건 나머지 3개의 항목이 뒤 따를 수 있다.
     * 2 = 첫번째와 두번째 항목이 선택된 이후 남은 두가지 항목이 뒤 따를 수 있다.
     * 1 = 앞선 3가지 항목을 제외한 남은 하나의 항목이 따른다.
     *
     * 발생 가능한 모든 경우의 수는 = 4 * 3 * 2 * 1 = 24개가 된다.
     * 이는 factorial 과 동일하다.
     */
    private String permutation(char[] charArr, int cursor){
        if(cursor > charArr.length){
            return ""; // recursion 종료식
        } else {
            // 전체 갯수를 구하는것 맞춤
            // 그럼 전체 카운트를 하면서 하나씩 케이스를 까주면 되는거 아닌가?
            // 케이스를 까주자.


            char selectChar;

            for (int i = cursor; i < charArr.length; i++) {
                selectChar = charArr[i];
                System.out.println("Loop Cnt : >>" + i);

                // 여기서 도출 해야 맞음.
                // 여기가 하나씩 계수중이니까.

                // a, b, c
                // a, c, b
                // b, a, c
                // b, c, a
                // c, a, b
                // c, b, a


            }
            return permutation(charArr, cursor+1);
        }
    }
}