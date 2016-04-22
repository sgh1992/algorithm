package some;

import java.util.Scanner;

/**
 * Created by sghipr on 4/22/16.
 * 括号匹配问题.
 * 以这样一种方式给定输入形式:(??...) 即最开始的字符与最后一个字符分别是 ( 与 ) ,中间有若干个?字符,并且每个?字符都可以随意地匹配 ( 或者 ) 字符.
 * 因为每种匹配方式的代价是不一样的，假设给出若干个?，并且给出它匹配左右括号的代价各是多少。
 * 现在要求,在满足括号匹配，且代价最低的?转换是什么。
 * 输入:(??)
 *      1 2 第一，二个数字分别代表问号匹配左右括号的代价.
 *      2 8
 *
 *输出: 4
 *    ()()
 *
 * 解题思路:
 * 1.从左至右地进行循环.左括号的个数一定要比右括号的个数要多.
 * 2.左右括号的个数一定要相等，且等于总的一半.
 * 按照这种思路进行递归处理.
 **/
public class MatchingBrackets {
    public void soluation(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String string = scanner.nextLine();
            char[] chars = string.substring(1,string.length() - 1).toCharArray();
            if(chars.length % 2 != 0){
                System.out.println(-1);
                continue;
            }
            int[] values = new int[chars.length * 2];
            for(int i = 0; i < values.length; i++)
                values[i] = scanner.nextInt();

            String best = match(values,chars.length + 2,0,0,"");
            int sumValue = getValue(best,values);
            System.out.println(sumValue);
            System.out.println(best.substring(1,best.length() - 1));

        }
        scanner.close();
    }

    public String match(int[] values,int length,int left, int right,String best){
        if(left < right || left * 2 > length || right * 2 > length)
            return null;

        if(left + right == length)
            return best;

        String leftBest = match(values,length,left + 1,right,best + "(");
        String rightBest = match(values,length,left,right + 1,best + ")");
        best = evaluateBest(leftBest,rightBest,values);
        return best;
    }
    public String evaluateBest(String leftStr,String rightStr,int[] values){

        if(leftStr == null)
            return rightStr;
        if(rightStr == null)
            return leftStr;

        if(getValue(leftStr,values) <= getValue(rightStr,values))
            return leftStr;
        else
            return rightStr;
    }

    public int getValue(String str,int[] values){

        char[] chars = str.toCharArray();
        int sumValues = 0;
        for(int i = 1; i < chars.length - 1; i++){
            if(chars[i] == '(')
                sumValues += values[(i-1) * 2];
            else
                sumValues += values[(i-1) * 2 + 1];
        }
        return sumValues;
    }

    public static void main(String[] args){
        MatchingBrackets matchingBrackets = new MatchingBrackets();
        matchingBrackets.soluation();
    }
}
