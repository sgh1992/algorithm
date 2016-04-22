package some;

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

    public void soluation(){

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if(a <= b)
                System.out.println(b);
            else {


            }
        }

    }

    public int getValue(int a,int b){

        char[] aStr = String.valueOf(a).toCharArray();
        char[] bStr = String.valueOf(b).toCharArray();
        StringBuilder result = new StringBuilder();
        int i = aStr.length - 1;
        int j = bStr.length - 1;
        while(i >= 0 && j >= 0 && aStr.length - i <= bStr.length){
            if(aStr[i] <= bStr[j])
                j--;
            i--;
        }

        if(i * j <= 0){
            result.append(bStr[0]);
            for(int k = 0; k < aStr.length - bStr.length + 1; k++){
                result.append(0);
            }
            for(int k = bStr.length - 1; k > 0 ; k++){
                result.append(bStr[k]);
            }
        }
        else{

        }
    }

}
