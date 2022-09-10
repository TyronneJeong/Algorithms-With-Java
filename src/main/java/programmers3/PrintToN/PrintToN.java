package programmers3.PrintToN;

public class PrintToN {
    public void exec(){
        solution(4, 12);
    }


    private int N;
    private int number;
    private int depth;
    private int minDepth;

    public int solution(int N, int number) {
        this.N = N;
        this.number = number;
        doSomething(0, 0, 0);
        return this.minDepth > 8 ? -1 : this.minDepth;
    }

    String temp;
    private void doSomething(int curSum, int depth, int op){
        System.out.println("현재 숫자 : >>" + curSum);
        // 종료식 - 목표점수 달성 시 종료
        if(curSum == number){
            System.out.println("~~~~~~~~~~~~~~~~~~~도착~~~~~~~~~~~~~~~~~~~");

            if(this.minDepth > depth){
                System.out.println("완성!!! DEPTH 는 : "+depth);
                this.minDepth = depth;
            }
            else if(this.minDepth == 0){
                System.out.println("최초생성!!! DEPTH 는 : "+depth);
                this.minDepth = depth;
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~리턴~~~~~~~~~~~~~~~~~~~");
            return;
        }

        if(depth > 8) return;

        // 호출식(조건)
        if(curSum < number && op != 3){
            System.out.println("덧셈 Depth => "+depth);
            doSomething(curSum + N, ++depth, 1);
        }
        if(curSum > 0 && curSum < number){
            System.out.println("곱셈 Depth => "+depth);
            temp = String.valueOf(N);
            doSomething(curSum + Integer.parseInt(temp+temp), ++depth, 2);
        }
        if(curSum > number && op != 1){
            System.out.println("뺄셈 Depth => "+depth);
            doSomething(curSum - N, ++depth, 3);
        }
        if(curSum > number){
            System.out.println("나눗셈 Depth => "+depth);
            doSomething(curSum - 1, ++depth, 4);
        }
    }
}
