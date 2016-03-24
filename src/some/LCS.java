package some;
/**
 * Created by sghipr on 16-3-23.
 * 计算两个序列的最长公共子序列LCS
 * 可以将这个问题转换成子问题求解.
 * 假设序列 X<x1,x2,....xn>
 *    序列 Y<y1,y2,...ym>
 * 定义序列的前缀Xi = <x1,x2,...xi> Yj = <y1,y2,...yj> 其中i = 0,1,2,...,n;j = 0,1,2,...m
 * 其中X0 与Y0 是 空串.
 * 假设Z＝<z1,z2,z3,...zk> 是序列X与Y的最长公共子序列LCS
 * 那么存在以下三种情况.
 * 1)当xn = ym 时,zk = xn = ym,且Zk-1是Xn-1与Ym-1的LCS；
 * 2）当xn != ym时，zk != xn,且Z是Xn-1与Y的LCS；
 * 3）当xn != ym时,zk != ym,且Z是X与Ym-1的LCS；
 *
 * 下面的这种方法是一种简单的暴力搜索算法.只是简单地运用了递归的方法，而没有思考到用从底到上的动态规划的方法来进行处理.
 * 而且这种方法没有办法找出最长的公共子序列是具体的值.
 */
public class LCS {

    private String string1;
    private String string2;

    public LCS(String string1,String string2){
        this.string1 = string1;
        this.string2 = string2;
    }

    public void setString1(String string1){
        this.string1 = string1;
    }

    public void setString2(String string2){
        this.string2 = string2;
    }

    public int lcs(){
        int n = string1.length() - 1;
        int m = string2.length() - 1;
        int len = lcs(string1,string2,n,m);
        return len;
    }

    public int lcs(String string1,String string2,int n,int m){
        if(n < 0 || m < 0)
            return 0;
        if(string1.charAt(n) == string2.charAt(m)) {
            return 1 + lcs(string1, string2, n - 1, m - 1);
        }
        else
            return Math.max(lcs(string1,string2,n - 1,m),lcs(string1,string2,n,m - 1));
    }

    public static void main(String[] args){
        String string1 = "abcdefghijk";
        String string2 = "agjhpqr";
        LCS lcs = new LCS(string1,string2);
        int len = lcs.lcs();
        System.out.printf("\"%s\" with \"%s\" lcs length is:%d ",string1,string2,len);
    }


}
