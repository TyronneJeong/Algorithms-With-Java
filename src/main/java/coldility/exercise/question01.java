package coldility.exercise;

import java.util.HashMap;

public class question01 {
    // 실행 메서드
    public static void exec(){
        String s = "SRR";
        System.out.println("The answer is >> "+ solution(s));
    }

    // 해결방법
    public static int solution(String G) {
        // convert String to array
        char[] chArr = G.toCharArray();
        HashMap hmap = new HashMap<Character, Integer>();

        hmap.put("R", 0);
        hmap.put("S", 0);
        hmap.put("P", 0);
        // aggregate each gesture
        for(int ix = 0; ix<chArr.length; ix++){
            if(chArr[ix] == 'R'){
                hmap.put("R", (int)hmap.get("R")+1);
            } else if(chArr[ix] == 'S'){
                hmap.put("S", (int)hmap.get("S")+1);
            } else if(chArr[ix] == 'P'){
                hmap.put("P", (int)hmap.get("P")+1);
            }
        }

        int rockScore = 0;
        int scissorScore = 0;
        int paperScore = 0;

        // calculate which one has maximum values
        rockScore = ((int)hmap.get("S") * 2) + (int)hmap.get("R");
        scissorScore = ((int)hmap.get("P") * 2) + (int)hmap.get("S");
        paperScore = ((int)hmap.get("R") * 2) + (int)hmap.get("P");

        if(rockScore >= scissorScore && rockScore >= paperScore){
            return rockScore;
        } else if(scissorScore >= rockScore && scissorScore >= paperScore){
            return scissorScore;
        } else if(paperScore >= rockScore && paperScore >= scissorScore){
            return paperScore;
        } else {
            return 0;
        }
    }
}
