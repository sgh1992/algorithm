package some;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sghipr on 16-3-24.
 * 给定一个組数据,计算出它的所有组合.
 * 可以运用位运算的技巧.
 * 假如,总共有n个元素，那么设定有n个二进制位,每个二进制位有0,1两种可能.
 * 当为0时表示集合中没有这个元素,当为1时,表示集合中包含有这个元素.
 * 那么总共有2^n种可能.
 * 即对应的数值为0,1,...2^n - 1;
 * 假如有X,Y,Z三个元素,001表示X，010表示Y,100表示Z.
 * 那么,011表示XY,111表示XYZ等.
 * 也就是根据0-2^n -1的二进制表示中，二进制位为1,則代表这个元素被选中.
 *
 */
public class Combination {

    private String string;

    public Combination(String string){
        this.string = string;
    }

    public List<String> allCombination(){
        List<String> combinationList = new ArrayList<>();
        int count = 1 << string.length();//所有组合的个数.,即2^n,从0开始;
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < count; i++){
            for(int j = 0; j <= string.length(); j++){
                int tmp = (1 << j) & i;//判定从右向左的第j个二进制位是否为1.
                if(tmp != 0)
                    stringBuilder.append(string.charAt(j));
            }
            if(stringBuilder.length() > 0) {
                combinationList.add(stringBuilder.toString());
                stringBuilder.delete(0,stringBuilder.length());
            }
        }
        return combinationList;
    }

    public static void main(String[] args){
        String string = "abcdefgopu";
        Combination combination = new Combination(string);
        List<String> all = combination.allCombination();
        for(String value : all)
            System.out.println(value);
        System.out.printf("sumNum:%d",all.size());
    }
}
