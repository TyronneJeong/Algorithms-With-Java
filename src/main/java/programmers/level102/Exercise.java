package programmers.level102;

/**
 * 문제 설명
 * 이 문제에는 표준 입력으로 두 개의 정수 n과 m이 주어집니다.
 * 별(*) 문자를 이용해 가로의 길이가 n, 세로의 길이가 m인 직사각형 형태를 출력해보세요.
 *
 * 제한 조건
 * n과 m은 각각 1000 이하인 자연수입니다.
 * 예시
 * 입력
 *
 * 5 3
 * 출력
 *
 * *****
 * *****
 * *****
 */
public class Exercise {
    public void exec(){

        System.out.println(solution(123));
    }



    public int solution(int n) {
        int answer = 0;
        for (int i:Integer.toString(n).toCharArray()) {
            answer+=Character.getNumericValue(i);
        }
        return answer;
    }


    public int[] solution(long n) {
        char[] arr = Long.toString(n).toCharArray();
        int[] rtnArr = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            rtnArr[i] = Character.getNumericValue(arr[arr.length-1-i]);
        }
        return rtnArr;
    }

//    public long solution(long n) {
//        double dv = Math.sqrt(n);
//        if((double)(long)dv != dv) return -1;
//        return (long)Math.pow(dv+1, 2);
//    }
//
//    public int[] solution(int[] arr) {
//        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        int finalArr  = Arrays.stream(arr).sorted().toArray()[0];
//        arr = list.stream().filter(e->e != finalArr).mapToInt(Integer::intValue).toArray();
//        if(arr.length == 0) arr = new int[]{-1};
//        return arr;
//    }
//
//    public int solution(int num) {
//        int answer = -1;
//        long _num = num;
//        for (int i = 0; i < 500; i++) {
//            if(_num == 1) return answer+1;
//            if(_num % 2 == 0){
//                _num = _num / 2;
//            } else {
//                _num = _num * 3 + 1;
//            }
//            answer++;
//        }
//        answer = -1;
//        return answer;
//    }
//
//
//
//    public double solution(int[] arr) {
//        double answer = 0;
//        int lCnt = arr.length;
//        for (int i = 0; i < lCnt; i++) {
//            answer += arr[i];
//        }
//        return answer/lCnt;
//    }
//
//    public boolean solution(int x) {
//        char[] chArr = String.valueOf(x).toCharArray();
//        int mother = 0;
//        for (int i = 0; i < chArr.length; i++) {
//            mother += Character.getNumericValue(chArr[i]);
//        }
//        return x%mother == 0;
//    }
//
//    public String solution(String phone_number) {
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < phone_number.lastIndexOf("")-4; i++) {
//            sb.append("*");
//        }
//        return sb.toString() + phone_number.substring(phone_number.lastIndexOf("")-4);
//    }
//
//    public int[][] solution(int[][] arr1, int[][] arr2) {
//        int loopCnt = arr1.length;
//        int depth = arr1[0].length;
//        int[][] answer = new int[loopCnt][depth];
//        for (int i = 0; i < loopCnt; i++) {
//            for (int j = 0; j < depth; j++) {
//                answer[i][j]= arr1[i][j] +  arr2[i][j];
//            }
//        }
//        return answer;
//    }
//
//    public long[] solution(int x, int n) {
//        long[] answer = new long[n];
//        long cumulativeValue = 0;
//        for (int i = 0; i < n; i++) {
//            cumulativeValue += x;
//            answer[i] =cumulativeValue;
//        }
//        return answer;
//    }
//
//    private void soluition() {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//        for (int i = 0; i < b; i++) {
//            for (int j = 0; j < a; j++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }
//    }
}
