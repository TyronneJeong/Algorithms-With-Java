package programmers.codechallenge;

public class TripleConversion {
    public void exec(){
        int n = 45; // 7
        System.out.println(solution(n));
    }

    public int solution(int n) {
        String numb = convert2BaseNumber(String.valueOf(n), 10, 3);
        numb = convert2BaseNumber(reverseString(numb), 3, 10);
        return Integer.valueOf(numb);
    }

    // 진법 변환
    private String convert2BaseNumber(String strNumb, int fromBase, int toBase){
        int numb = Integer.valueOf(strNumb, fromBase);
        StringBuffer sb = new StringBuffer();
        if(fromBase > toBase){
            int quotient = numb;
            while(quotient  > 0){
                sb.append(quotient % toBase); // 나머지 remainder
                quotient = quotient / toBase; // 몫 quotient
            }
            return sb.reverse().toString();
        } else if(fromBase < toBase){
            return String.valueOf(numb);
        } else {
            return strNumb;
        }
    }

    // 스트링 역전
    private String reverseString(String arg) {
        char[] charArr = arg.toCharArray();
        char tempCh;
        int loopCnt = charArr.length;
        for (int i = 0; i < loopCnt/2; i++) {
            tempCh = charArr[i];
            charArr[i] = charArr[loopCnt-1-i];
            charArr[loopCnt-1-i] = tempCh;
        }
        return String.valueOf(charArr);
    }

    // StrignBuilder 에 reverse 가 있네;;;;;;;
    public int solution_other(int n) {
        String a = "";

        while(n > 0){
            a = (n % 3) + a;
            n /= 3;
        }
        a = new StringBuilder(a).reverse().toString();


        return Integer.parseInt(a,3);
    }
}
