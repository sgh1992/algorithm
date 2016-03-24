package some;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sghipr on 16-3-24.
 * 观察排列时的规律
 * 例如:XYZ
 * 利用X与Y,Z进行交换,即第一个数字与第二个数字进行交换.从而得到YXZ,ZYX.
 * 然后YXZ,ZYX中利用第二个字符与第三个字符进行交换,得到YZX,ZXY.
 * 最后利用XYZ中的第二个字符与第三个字符进行交换得到XZY;
 *
 * 总的来说,就是依次将一个数字与它后续的数字进行交换,来得到全排列的.
 * 当数据中存在重复元素时,假如需要将i与j位置的字符进行交换.則需要确保[i,j)范围内不存在与位置j相等的元素.
 */
public class StringArrangementSwap {

    private char[] arr;

    public StringArrangementSwap(char[] arr){
        this.arr = arr;
    }

    public StringArrangementSwap(String string){
        arr = string.toCharArray();
    }

    public boolean existSame(int start,int end){
        for(int i = start; i < end; i++)
            if(arr[i] == arr[end])
                return true;
        return false;
    }

    public void swap(int i,int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public List<String> allRrrange(){
        List<String> arrangeList = new ArrayList<>();
        allRrrange(0,arrangeList);
        return arrangeList;
    }

    public void allRrrange(int start,List<String> arrangeList){
        if(start >= arr.length - 1){
            arrangeList.add(new String(arr));
            return ;
        }
        for(int i = start; i < arr.length; i++){
            if(!existSame(start,i)){
                swap(start,i);
                allRrrange(start + 1,arrangeList);
                //每次交换之后,都需要将之交换回来!!!原地排序.
                swap(start,i);
            }
        }
    }
    public static void main(String[] args){
        String string = "XYZ";
        StringArrangementSwap arrangementSwap = new StringArrangementSwap(string);
        List<String> arrangeList = arrangementSwap.allRrrange();
        for(String arrange : arrangeList)
            System.out.println(arrange);
    }

}
