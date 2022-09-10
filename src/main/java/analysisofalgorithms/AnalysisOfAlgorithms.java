package analysisofalgorithms;

import analysisofalgorithms.exercise.L06Permutation;
import analysisofalgorithms.exercise.L27Hashing;

public class AnalysisOfAlgorithms {

    public static void main(String[] args) {
        int z;
        System.out.println("1번 유형");
        z= 0;
        for(int ix = 0; ix < 10; ix = z++){
            System.out.println(ix);
        }

        System.out.println("2번 유형");
        z= 0;
        for(int ix = 0; ix < 10; ix = ++z){
            System.out.println(ix);
        }
    }
}