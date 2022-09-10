package programmers3;

public class BinomialCoefficient {
    public void exec(){
        solution();
    }

    private void solution(){
        System.out.println(binomial(2, 2));
    }


    private int binomial(int n, int k){
        if(n == k || k == 0)
            return 1;
        else
            return binomial(n - 1, k) + binomial(n - 1, k-1);
    }
}
