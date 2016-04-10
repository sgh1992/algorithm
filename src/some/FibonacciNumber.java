package some;

/**
 * Created by sghipr on 9/04/16.
 * 利用动态规划的方法来计算斐波那契数
 * 典型的问题的最优解的子问题的解也是最优解.
 * F0 = 0
 * F1 = 1
 * Fi = Fi-1 + Fi-2     (i >= 2)
 */
public class FibonacciNumber {

    private int N;
    private int[] fibonacNums;
    public FibonacciNumber(int N){
        this.N = N;
        fibonacNums = new int[N + 1];
        DP();
    }

    public void DP(){
        fibonacNums[0] = 0;
        fibonacNums[1] = 1;
        for(int i = 2; i <= N; i++)
            fibonacNums[i] = fibonacNums[i - 1] + fibonacNums[i - 2];
    }

    public int fibonacs(int num){
        if(num > N){
            System.err.printf("the nums:%d exceed N",num);
            System.exit(1);
        }
        return fibonacNums[num];
    }

    public static void main(String[] args){

        int N = 10;
        int num = 9;
        FibonacciNumber fibonacciNumber = new FibonacciNumber(N);
        System.out.printf("the fibonaccNum of %d is %d",num,fibonacciNumber.fibonacs(num));
    }


}
