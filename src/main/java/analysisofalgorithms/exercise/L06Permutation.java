package analysisofalgorithms.exercise;

public class L06Permutation {

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
    public void solution(){
        char[] charArr = {'a', 'b', 'c', 'd'};

        int bitmask = 1;
//        System.out.println("Asdasdasdasd");
        // 5개의 출력 포맷이 나온다.

        // 5개 출력 -> 다음
        // 5개 출력 -> 다음
        System.out.println(Integer.toBinaryString(1 << 63));
        // 32 자리
        // 11_00000_00000_00000_00000_00000_00000
        // 10_00000_00000_00000_00000_00000_00000
    }

    private String permutation(char[] charArr, int begin, int end){
        // 종료식
        if(begin > end){
            return null;
        }

        // 점화식

        return "";
    }
}
