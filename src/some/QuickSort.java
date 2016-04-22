package some;

import java.util.Arrays;

/**
 * Created by sghipr on 4/18/16.
 * 快速排序.
 */
public class QuickSort {


    private int[] array;

    public QuickSort(int[] array){
        this.array = array;
        sort();
    }

    public void sort(){
        sort(array,0,array.length - 1);
    }

    private void sort(int[] a,int p,int r){
        if(p < r){
            int q = partition(a,p,r);
            sort(a,p,q - 1);
            sort(a,q + 1,r);
        }
    }

    private int partition(int[] a,int p, int q){
        int x = a[p];
        int povit = p;
        while(p < q){
            //注意，这里正是因为这种策略，才将
            while (p < q && a[q] > x)
                q--;
            while(p < q && a[p] < x)
                p++;
            if(p == q)
                break;
            int temp = a[p];
            a[p] = a[q];
            a[q] = temp;
            p++;
            q--;
        }
        a[povit] = a[p];
        a[p] = x;
        return p;
    }

    public int[] result(){
        return array;
    }


    public static void main(String[] args){
        int[] array = {3,7,2,1,6};
        QuickSort quickSort = new QuickSort(array);
        System.out.println(Arrays.toString(quickSort.result()));
    }


}
