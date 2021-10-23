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
        char[] resultArr = {};
        // 비트마스크 출력 예제
        System.out.println(Integer.toBinaryString(1 << 63));


        /**
         * a, b, c, d
         * a, b, d, c
         * a, c, d, b
         * a, c, b, d
         * a, d, b, c
         * a, d, c, b
         *
         * b, a, c, d
         * b, a, d, c
         * b, c, a, d
         * b, c, d, a
         * b, d, a, c
         * b, d, c, a
         *
         * c..
         * d..
         * 
         * 리커젼 특징은 하나의 원소가 선택 된 후 나머지 원소들 전체를 한번씩 대입하여 결과를 출력한다는 것
         * a, b, c, d 진행
         * [a] 고정
         *     b, c, d 진행
         *    [b] 고정
         *        c, d 진행 식
         *
         *  진행 커서가 존재해야 한다.
         *  현재 커서
         *  그리고 종료식을 만들기 위해 end 값도 가지고 있어야 함.
         *  커서와 end 가 같으면 리턴
         */

        String sol = permutation(charArr, resultArr, 0, 3, 0);
        System.out.println(sol);
    }

    private String permutation(char[] charArr, char[] resultArr, int begin, int end, int cursor){
        // 오류처리
        if(begin > end){
            return null;
        } else {
            // 종료식
            if(cursor > end ){// Loop 식 1
                return "";
            } else {

                // for 를 써야 할듯
//                for (int x = cursor; x <= end; x++) { // 현재 위치에서 부터 나머지를 찾기
//                    for (int i = begin; i <= end; i++) {
//                        System.out.println("move to : >>" + i + " / " + x);
//                    }
//                }
                char pickedChar;


                System.out.println("cursor and move >> "+cursor+"/"+begin);

                // 바깥에서 배열 길이만큼 루프 시키면 됨. 문자열 조합만 맞추자.
                // 이전에 어떤 글자가 선택 되었는지 알수 있는 방법은?
                // 결과 배열을 인자로 넘길까?

                // charArr / result / cursor


                if (begin < end) {
                    return charArr[cursor] + permutation(charArr, resultArr, begin+1, end, cursor);
                } else {
                    // 점화식을 둘로 분리하여 하나는 Loop 하나는 종료로 처리해야 할듯.
                    return charArr[cursor] + permutation(charArr, resultArr, 0, end, cursor + 1);
                }
            }
        }
    }
}
