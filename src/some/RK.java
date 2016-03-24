package some;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sghipr on 16-3-23.
 * 字符串匹配算法.
 * 采用的是RK指纹识别算法.
 * 其实重点在于理解,哈希值与一般的进制数的不同.哈希值可以有冲突，但二进制数必须是唯一的.
 * * 模式串 M 长度m;
 * 文本串 N 长度n;
 * 主要的算法思想:
 * 1.计算模式串的hash值.利用霍納法则，可以在O(m)的时间内计算出它的哈希值.当作一个R进制数值来计算模式串M;
 * 即hash = a0 + R*(a1 + R*(a2 + R(a3 + ...R(an-1)...)这是从低位到高位的递归的方法.
 * 也可以采用从高位到低位的非递归的方法.
 * hash = 0
 * for(int i = 0; i < M.length; i++)
 *      hash = R * hash + M.charAt(i)
 * 一般情况下需要选取R的值应该要比任意的文本串中的值都要大.
 * 当然也可以不等，但是，这种情况下可以是哈希值,但是不可以是唯一的数值，即哈希值可以有冲突,但是进制值則必须是唯一的.
 * 2.针对文本串,从0开始计算t0(0,m-1)的子序列的哈希值.需要O(m)的时间.
 * 但是对后续的所有的ts(s,s + m - 1)的时间都是O（1）,即常数时间.
 *ts+1 = R* ( ts - Ts * R^(m - 1)) + Ts+m;
 * 即只需要保留前一个子序列的哈希值即可.
 *
 */
public class RK {

    private int R = 256;
    private int Q = 997;

    public int hash(String string,int M){
        int hash = 0 ;
        for(int i = 0; i < M && i < string.length(); i++)
            hash = (hash * R + string.charAt(i)) % Q;
        return hash;
    }

    public boolean check(String subText,String pattern,int subTextHash,int patternHash){
        if(subTextHash == patternHash){
            for(int i = 0; i < pattern.length(); i++){
                if(subText.charAt(i) != pattern.charAt(i))
                    return false;
            }
            return true;
        }
        return false;
    }

    public List<Integer> match(String text,String pattern){
        List<Integer> indexList = new ArrayList<>();
        int m = pattern.length();
        if(text.length() < pattern.length()){
            System.err.println("text length less pattern length");
            System.exit(1);
        }
        int patternHash = hash(pattern,m);
        int beforets = hash(text.substring(0,m),m);
        if(check(text.substring(0,m),pattern,beforets,patternHash))
            indexList.add(0);
        int RM = 1;
        for(int i = 0; i < m - 1; i++)
            RM = (RM * R) % Q;//取模的性质.a==c(mod m) => ab%m == b*(a mod m)递归计算.//而且这里是m - 1次方.
        for(int i = 1; i <= text.length() - m; i ++){
            //注意这种处理的方式.
            int subTextHash = (beforets + Q - (RM * text.charAt(i - 1)) % Q) % Q;
            subTextHash = (R * subTextHash + text.charAt(i - 1 + m)) % Q;
            if(check(text.substring(i,i + m),pattern,subTextHash,patternHash))
                indexList.add(i);beforets = subTextHash;
        }
        return indexList;
    }

    public static void main(String[] args){
        RK rk = new RK();
        String text = "This is a good day,maybe we should go out to have good day!";
        String pattern = "good day";
        List<Integer> indexList = rk.match(text,pattern);
        if(indexList.size() > 0){
            for(int start : indexList)
                System.out.printf("\"%s\" start %d in \"%s\"\n",pattern,start,text);
        }
        else
            System.out.printf("\"%s\" do not contains \"%s\"",text,pattern);
    }
}
