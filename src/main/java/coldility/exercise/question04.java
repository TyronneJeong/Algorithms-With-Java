package coldility.exercise;

public class question04 {
    // 실행 메서드
    public static void exec(){
        int s = 0;
        System.out.println("The answer is >> "+ solution(".xxx...x","..x.xxxx"));
    }

    // 해결방법
    public static int solution(String L1, String L2) {
        char[] lane1 = L1.toCharArray();
        char[] lane2 = L1.toCharArray();
        int loopSize = lane1.length;

        int count = 0;
        int type = 0;
        for(int ix=0; ix<loopSize; ix++){
            if(lane1[ix] == '.' && lane2[ix] == '.'){
                continue;
            } else if(lane1[ix] == '.' && lane2[ix] == 'x'){
                if(lane1[ix] == 'x' && lane2[ix] == '.'){
                    continue;
                } else {
                    count++;
                }
            } else if(lane1[ix] == 'x' && lane2[ix] == '.'){
                if(lane1[ix] == '.' && lane2[ix] == 'x'){
                    continue;
                } else {
                    count++;
                }
            } else if(lane1[ix] == 'x' && lane2[ix] == 'x'){
                count++;
            }
        }
        return count;
    }

}
