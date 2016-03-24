package some;

/**
 * Created by sghipr on 16-3-23.
 * 实现霍納法则.
 * 即sum = a0 + x(a1 + x(a2 + x(a3 +...x(an-1)...)
 * 要求它在O（n)的时间内完成.
 */
public class Hornor {
    private int[] coefficient;//系数.注意给的数值是从高位到低位的.即123代表一百二十三.
    private int ary;//进制 x.

    public Hornor(int[] coefficient,int ary){
        this.coefficient = coefficient;
        this.ary = ary;
    }

    public int compute(){
        return compute(coefficient.length - 1,0);
    }

    /**
     * 这个方法是从高位到低位的计算方法,而且是非递归的计算方法
     * 注意与上述从低位到高位的计算方法区分开来.而且是递归的计算方法.
     * @param coefficient
     * @param ary
     * @return
     */
    public int compute(int[] coefficient,int ary){
        int hash = 0;
        for(int i = 0; i < coefficient.length; i++){
            hash = (ary * hash + coefficient[i]);
        }
        return hash;
    }

    public int compute(int index,int sum){
        if(index == 0)
            return coefficient[0];
        sum += (coefficient[index] + ary * compute(index - 1,sum));
        return sum;
    }



    public static void main(String[] args){
        int[] coefficient = {1,2,3,0,9};
        int ary = 10;
        Hornor hornor = new Hornor(coefficient,ary);
        System.out.println(hornor.compute());

        String string = null;
        string.hashCode();

    }
}
