package some;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sghipr on 16-3-24.
 * 给定一个字符串,列出它的所有排列组合.
 * 最直接的一种方法是用暴力搜索方法
 * 比如,给定的字符串是:XYZ;那么第一个字符是从 X,Y,Z 中任意地选择一个.假如选择的是X,接着选择第二个元素,且第二个元素不能在前面中被选择,依此进行.
 * 这里有个问题,如果字符串中存在重复元素怎么办呢.
 * 暴力搜索的时间复杂度度为O(n^n).
 */
public class StringArrangement {

    private String string;
    private HashMap<String,Integer> count = new HashMap<>();//用来记录每个字符出现的次数,用于后续的排列.
    public StringArrangement(String string){
        this.string = string;
        for(int i = 0; i < string.length(); i++){
            int num = 1;
            if(count.containsKey(String.valueOf(string.charAt(i))))
                num += count.get(String.valueOf(string.charAt(i)));
            count.put(String.valueOf(string.charAt(i)),num);
        }
    }
    public List<String> arrangement(){
        List<String> list = new ArrayList<>();
        arrangement(new String(),list);
        return list;
    }

    public void arrangement(String path,List<String> arrangeList){
        if(path.length() == string.length()) {
            arrangeList.add(path);
            return;
        }
        for(String letter : count.keySet())
            if(!path.contains(letter)){
                arrangement(path + letter, arrangeList);
            }
    }

    public static void main(String[] args){
        String string = "XYZEFGHIJK";
        StringArrangement stringArrangement = new StringArrangement(string);
        List<String> list = stringArrangement.arrangement();
        for(String arr : list)
            System.err.println(arr);
    }







}
