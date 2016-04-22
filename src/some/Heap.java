package some;

import java.util.Arrays;

/**
 * Created by sghipr on 17/04/16.
 */
public class Heap {

    private int[] arrays;
    private int N;

    public Heap(int[] arrays){
        this.arrays = arrays;
        this.N = arrays.length;
        buildMaxHeap(arrays);
    }

    private void buildMaxHeap(int[] arrays){
        int root = arrays.length/2 - 1;
        while(root >= 0){
            sink(arrays,root,arrays.length);
            root--;
        }
    }
    /**
     * 下沉操作.
     * 指明了数组a,下标K,堆的大小n,有时堆的大小并不一定等于数组的大小.
     * @param a
     * @param k
     * @param n
     */
    public static void sink(int[] a,int k,int n){
        while(k * 2 + 1 < n){
            int max = 2 * k + 1;
            if(2 * k + 1 < n - 1 && a[2 * k + 2] > a[2 * k + 1]){
                max = 2 * k + 2;
            }
            if(a[k] >= a[max])
                return;
            int temp = a[k];
            a[k] = a[max];
            a[max] = temp;
            k = max;
        }
    }

    public int size(){
        return N;
    }

    /**
     * 堆排序.
     * @param array
     */
    public static int[] sort(int[] array){
        Heap heap = new Heap(array);
        int N = heap.size();
        while(N > 1){
            int temp = array[0];
            array[0] = array[N - 1];
            array[N - 1] = temp;
            N--;
            sink(array,0,N);
        }
        return array;
    }

    public String toString(){
        return Arrays.toString(arrays);
    }

    public static void main(String[] args){

        int[] arrays = {27,17,3,16,13,10,1};
        System.out.println(Arrays.toString(Heap.sort(arrays)));


    }


}
