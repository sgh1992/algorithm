package some;

import sun.security.krb5.internal.PAData;

/**
 * Created by sghipr on 5/9/16.
 * 字符串匹配算法.
 * KMP算法.
 * KMP算法的核心思想就是当文本串与模式串不匹配时，文本指针并不需要进行回溯，重点定位模式串指针与文本的当前字符串进行匹配.
 * 当文本串i与模式串j并不匹配时，根据模式串 0,...,j-1来计算出当前的最长公共子串的长度.
 * 重点在于计算next[j]数组的值.
 * next数组是指当模式串j与文本串i不匹配时，文本串i应该要与模式串的第j个字符进行比较.
 * 其中next数组的计算方式,是通过逐步迭代来获得的.
 *
 *
 * 如果:P[j] == P[k]:
 *  则 next[j+1] = next[j] + 1 = k + 1
 *
 * 如果 P[j] != P[k]:
 *      k = next[k]
 *
 */
public class KMP {

    private String pattern;

    private int[] next;

    public KMP(String pattern){
        this.pattern = pattern;
        this.next = getNext(pattern);
    }

    public int[] getNext(String pattern){
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int j = 0;
        int k = -1;
        //注意理解.必须要确保P[i] == P[k]时，才更新next[j + 1] = next[j] + 1 = k + 1

        //k与j 是相对应的.即next[j] = k;且j + 1是通过next[j]而计算出来的.
        while(j < pattern.length() - 1){
            if(k == -1 || pattern.charAt(j) == pattern.charAt(k)){
                next[++j] = ++k;
            }
            else
                k = next[k];
        }
        return next;
    }

    /**
     * 如果文本串text中存在pattern的子串时，则返回pattern子串在text中首次出现的下标.
     * 否则返回-1.
     * 时间复杂度是多少呢.
     * 好像无法确定时间复杂度. O(N)?
     * @param text
     * @return
     */
    public int search(String text){

        int j = 0;
        int i = 0;
        while(i < text.length() && j < pattern.length()){
            if(j == -1 ||text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            else
                j = next[j];
        }
        if(j == pattern.length())
            return i - j;
        return -1;
    }

    public static void main(String[] args){
        String pattern = "ACAC";
        String text = "ABEFGCACACOPQ";
        KMP kmp = new KMP(pattern);
        System.out.println(kmp.search(text));
    }
}
