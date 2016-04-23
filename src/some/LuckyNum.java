package some;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by sghipr on 4/22/16.
 * 计算幸运数字.
 *
 * 问题描述.
 * 假设包含4或者7的数字就是幸运数字.例如4,7,74,474等.
 * 而18457，574中包含47,74等幸运数字，因此这些数字又被称为伪幸运数字.
 * 现在，给出一个数字a和一个幸运数字b.
 * 求大于a且包含幸运数字b的最小整数.
 *
 * 输入:100   47
 * 输出:147
 *
 * 解题思路:
 * 最开始的想法是利用最小公共子序列来进行求解.即所求的X与幸运数字b的最小公共子序列为b，但是需要针对每个可能的数字都与幸运数字b进行一一比对.因些不可行.
 * 假如 a <= b,则返回b
 * 若a > b
 * 假如 18488 47
 * 首先从个位数字开始进行比对.
 *
 */
public class LuckyNum {

    public void soluation() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileInputStream("/home/sghipr/luckyNum"));
        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if(a <= b)
                System.out.println(b);
            else {
                int result = getValue(a,b);
                System.out.println(result);
            }
        }
        scanner.close();
    }


    public boolean match(char[] a,char[] b){

        int i = a.length - 1;
        int j = b.length - 1;
        while(i >= 0 && j >= 0){
            if(a[i] == b[j])
                j--;
            i--;
        }
        if(j < 0)
            return true;
        return false;

    }

    public int getValue(int a,int b){

        char[] aStr = String.valueOf(a).toCharArray();
        char[] bStr = String.valueOf(b).toCharArray();
        StringBuilder result = new StringBuilder();
        int i = aStr.length - 1;
        int j = bStr.length - 1;

        /**
         * 首先判断a中是否包含有b，如果包含，則直接返回a.
         */
        if(match(aStr,bStr))
            return a;

        //注意,i,j一定是指向的是完全匹配时的高一位的数字.
        while(i >= 0 && j >= 0){
            if(aStr[i] <= bStr[j]) {
                if(i == aStr.length - bStr.length && aStr[i] < bStr[0])
                    j = -1;
                else
                    j--;
            }
            i--;
        }

        int k = 0;
        //如果i >= 0时，則将最高位等一系列的结果按原先的结果存入到数组中.
        for(; k <= i; k++){
            result.append(aStr[k]);
        }
        //第i + 1 位一定是存储的是bStr[0]
        result.append(bStr[0]);
        if(i < 0)
            k = 0;
        else
            k++;

        //最高位确定之后的结果到最后bStr.length - 1的结果都是0.
        for(;k < aStr.length - bStr.length + 1; k++){
            result.append(0);
        }

        for(k = 1; k < bStr.length; k++)
            result.append(bStr[k]);

        return Integer.parseInt(result.toString());
    }

    public static void main(String[] args) throws FileNotFoundException {

        LuckyNum luckyNum = new LuckyNum();
        luckyNum.soluation();
    }


}
