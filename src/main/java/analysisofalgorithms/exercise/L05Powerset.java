package analysisofalgorithms.exercise;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class L05Powerset {
    /**
     * 멱집합
     * (Powerset)
     * 대상 집합의 모든 부분 집합을 구한 것을 멱집합(Powerset)이라고 한다.
     *
     * desc : 해당 집합의 모든 경우의 수를 구하는 것이 알고리즘의 핵심이다.
     * 경우의 수는 (2 ^ 원소의 수) 로 기존 집합에서 원소 하나가 추가 될때마다
     * 기존 원소의 2배만큼의 원소가 재발생 되므로 원소의 개수 만큼 제곱한 값이
     * 모든 경우의 수가 되는 원리이다.
     *
     * @DESC
     * ex ) 1, 2 의 모든 경우의 수. 2 * 2 = 4
     * - ∅, 1, 2, 12
     *
     * ex) 여기에 3이 추가 되면 기존 수에 3이 포함된 set 이 하나더 발생된다.
     * - 3, 13, 23, 123
     * 총 : ∅, 1, 2, 3, |  12, 13, 23, 123
     * #Example
     * ex) 여기에 4가 추가 되면 기존 수에 4가 포함된 set 이 하나더 발생된다.
     * - 4, 14, 24, 34 | 124, 134, 234, 1234
     * 총 : ∅, 1, 2, 3, | 4, 12, 13, 14, | 23, 24, 34, 123, | 124, 134, 234, 1234
     */
    public void solution(){
        char[] charArr = {'a', 'b', 'c', 'd', 'e', 'f'};
        String rtnVal = powerset(charArr, 0, 5, 1);
        System.out.println(rtnVal);
    }

    private String powerset(char[] charArr, int begin, int end, int width){
        if(begin >  end){ // 종료식
            return "[-]";
        } else {
            StringBuilder sb = new StringBuilder(width);
            for (int ix=begin; ix<begin+width; ix++){
                sb.append(charArr[ix]);
            }
            if(begin+width <= end){ // 점화식 1
                return sb.toString() + ", " + powerset(charArr, begin, end, width+1);
            } else { // 점화식 2
                return sb.toString() + ", " + powerset(charArr, begin+1, end, 1);
            }
        }
    }

    private void powerSetWithStream(){
        System.out.println("\n# stream.powerset");
        String[] strings = {"A", "B", "C", "D", "E", "F"};
        Stream.of(strings)
                .map(x->{
                    AtomicBoolean bAllowed = new AtomicBoolean(false);
                    int ascCnt = Stream.of(strings)
                            .filter(e->{
                                if(x.equalsIgnoreCase(e)) bAllowed.set(true);
                                return !bAllowed.get();})
                            .collect(Collectors.toList())
                            .size();
                    bAllowed.set(false);
                    int descCnt = Stream.of(strings)
                            .filter(e->{
                                if(x.equalsIgnoreCase(e)) bAllowed.set(true);
                                return bAllowed.get();})
                            .collect(Collectors.toList())
                            .size();
                    for (int ix = 0; ix <= descCnt; ix++){
                        Stream.of(strings)
                                .skip(ascCnt)
                                .limit(ix)
                                .forEach(d->System.out.print(d));
                        System.out.println("");
                    }
                    return x;
                })
                .forEach(e-> System.out.print(""));
    }
}
