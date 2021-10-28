package programmers.codechallenge;

public class TripleConversion {
    public void exec(){
        int n = 45; // 7
        System.out.println(solution(n));
    }

    public int solution(int n) {
        String numb = convert2BaseNumber(String.valueOf(n), 10, 3);
        char[] charArr = numb.toCharArray();
        char tempCh;
        int loopCnt = charArr.length;
        for (int i = 0; i < loopCnt/2; i++) {
            tempCh = charArr[i];
            charArr[i] = charArr[loopCnt-1-i];
            charArr[loopCnt-1-i] = tempCh;
        }
        numb = convert2BaseNumber(String.valueOf(charArr), 3, 10);
        return Integer.valueOf(numb);
    }

    // 진법 변환
    private String convert2BaseNumber(String strNumb, int fromBase, int toBase){
        int numb = Integer.valueOf(strNumb);
        String rtnVal = "";
        if(fromBase > toBase){
            // 10진법을 2진법으로 변환하는 법
            // 예 30
            // 2로 나눔 - 15 나머지 0
            // 2로 나눔 -  7 나머지 1
            // 2로 나눔 -  3 나머지 1
            // 2로 나눔 -  1 나머지 1
            // 2로 나눔 -  0 나머지 1
            // -> 11110 이 30이다.
            //
            int a, b, c;
            a = numb / toBase; // 몫
            b = numb % toBase; // 나머지

        } else if(fromBase < toBase){
            int interval = 1;
            // 3진법을 10진법으로 바꾸는 방법
            // 예) 1011 - 31
            // 1 * 1
            // 1 * 3
            // 0 * 9
            // 1 * 27
        } else {
            return strNumb;
        }
        return rtnVal;
    }
}
