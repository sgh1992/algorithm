package some;

/**
 * Created by sghipr on 16-3-23.
 *
 * /**
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
 * 运用动态规划的方法来进行求解.
 * 从底至上地进行求解.
 * 这里需要用到C[0...m,0...n]即一个二維矩阵.行代表序列1,列代表序列2.第一行代表序列1是空串的情况;第一列代表序列2是空串的情况.
 * 从第二行或者第二列代表正式的字符开始.
 * 其中c(i,j)代表序列Xi,Yj的LCS的长度
 * b[1...m,1...n]代表的是每个c(i,j)是使用的哪两个序列的最长公共子序列,主要有从(Xi-1,Yj),(Xi,Yj-1),(Xi-1,Yj-1)这三对的最长公共子序列.
 * 当b[i,j] = 'L'代表（Xi,Yj-1)
 * b[i,j]= 'U' 代表(Xi-1,Yj)
 * b[i,j]='T' 代表当前字符是属于最长公共子序列的.即指向的是(Xi-1,Yj-1).
 * 当需要寻找其所对应的LCS的时候，只需要从b[m,n]开始来倒过来查找即可.
 * */
public class LCSDP {

    private String string1;
    private String string2;
    private int[][] c;
    private char[][] b;

    /**
     * 每一个对象都只能针对一对序列进行判断,如果需要临时变更序列的話，需要重新创建对象!
     * @param string1
     * @param string2
     */
    public LCSDP(String string1, String string2){
        this.string1 = string1;
        this.string2 = string2;
        c = new int[this.string1.length() + 1][this.string2.length() + 1];
        b = new char[this.string1.length()][this.string2.length()];
        lcs();
    }

    public void lcs(){
        //第一行与第一列的cij值一定是0.因为存在一个序列为空串的情况.
        for(int i = 0; i < string1.length(); i++)
            c[i][0] = 0;
        for(int j = 0; j < string2.length(); j++)
            c[0][j] = 0;

        /**
         * 这里要非常注意边界的情况.
         * c的数组的各个維数都要比实际的字符串长度大1.
         * b数组的维度与字符串的长度是一致的.
         */
        for(int i = 1; i <= string1.length(); i++)
            for(int j = 1; j <= string2.length(); j++){
                if(string1.charAt(i - 1) == string2.charAt(j - 1)) {//注意这里的边界情况.
                    c[i][j] = 1 + c[i - 1][j - 1];
                    b[i - 1][j - 1] = 'T';
                }
                else if(c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    b[i - 1][j - 1] = 'U';
                }
                else{
                    c[i][j] = c[i][j - 1];
                    b[i - 1][j - 1] = 'L';
                }
            }
    }

    public int lcsLength(){
        return c[string1.length() - 1][string2.length() - 1];
    }

    public String lcsPath(){
        if(lcsLength() == 0)
            return null;
        StringBuilder path = new StringBuilder();
        lcsPath(string1.length() - 1,string2.length() - 1,path);
        return path.reverse().toString();
    }
    public void lcsPath(int i,int j,StringBuilder path){

        if(i < 0 || j < 0)
            return;

        if(b[i][j] == 'T'){
            path.append(string1.charAt(i));
            lcsPath(i - 1,j - 1,path);
        }
        else if(b[i][j] == 'L'){
            lcsPath(i, j - 1, path);
        }
        else{
            lcsPath(i - 1,j,path);
        }
    }

    public static void main(String[] args){
        String string1 = "ABCDGEFQRPST";
        String string2 = "BGFEDRT";
        LCSDP lcsdp = new LCSDP(string1, string2);
        String lcsPath = lcsdp.lcsPath();
        if(lcsPath != null){
            System.out.printf("%s with %s have the LCS:%s",string1,string2,lcsPath);
        }
        else
            System.out.printf("%s with %s have not the LCS",string1,string2);
    }
}
