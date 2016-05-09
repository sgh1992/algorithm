package some;

/**
 * Created by sghipr on 5/9/16.
 * 字符串匹配算法.
 * 即给定一个字符串S1与字符串S2，判定S2是字符串S1的子串.
 * 这里使用一种暴力求解地方法.
 */
public class StringMatch {

    public static int search(String text, String pattern){
        int N = text.length();
        int M = pattern.length();
        int j = 0;
        int i = 0;
        for(; i < N && j < M; i++){
            if(text.charAt(i) == pattern.charAt(j))
                j++;
            else{
                i = i - j;
                j = 0;
            }
        }
        if(j == M)
            return i - j;
        return -1;
    }
    public static void main(String[] args){

        String s1 = "ABCABBCEFKOOIOPD";
        String pa = "KOOI";
        System.out.println(StringMatch.search(s1, pa));

    }
}
