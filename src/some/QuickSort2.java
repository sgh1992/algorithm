package some;

import java.util.Arrays;

/**
 * Created by sghipr on 2/05/16.
 * 以另外一种思路来实现快速排序.
 */
public class QuickSort2 {

    private int[] array;

    public QuickSort2(int[] array){
        this.array = array;
        sort();
    }

    private void sort(){
        sort(0, array.length - 1);
    }

    private void sort(int p,int r){
        if(p < r){
            int q = partition(p,r);
            sort(p,q - 1);
            sort(q + 1,r);
        }
    }

    /**
     * p=<k<=i;  A[k] >= x
     * i + 1=< k =< j - 1; A[k] >x
     * j<=k<= r-1 ;A[k]未知.
     * @param p
     * @param r
     * @return
     */
    public int partition(int p, int r) {
        int x = array[r];
        int i = p - 1;
        int repeation = 0;//如果存在重复元素.則为了避免将算法的时间复杂度提高到O(N*N)增加一个额外的变量.
        for (int j = p; j < r; j++) {
            if (array[j] <= x) {
                if(array[j] == x)
                    repeation++;
                swap(++i,j);
            }
        }
        swap(++i,r);
        return i - repeation/2;
    }

    public void swap(int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public String toString(){
        return Arrays.toString(array);
    }

    public int[] sortResult(){
        return array;
    }

    public static void main(String[] args){
        int[] array = {6,6,6,6,6,6,6};
        QuickSort2 quickSort2 = new QuickSort2(array);
        System.out.println(quickSort2.toString());
    }



}
