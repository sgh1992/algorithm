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
        int root = N/2 - 1;
        while(root >= 1){
            adjustHeapUp(arrays,root);
            root--;
        }
    }

    private void adjustHeapUp(int[] arrays,int root){
        while (root < N/2){
            int max = root * 2 + 1;
            if(root * 2 + 2< N -1 && arrays[root * 2 + 2] > arrays[root * 2 + 1])
                max = root * 2 + 2;
            if(arrays[root] >= arrays[max])
                return;

            int temp = arrays[root];
            arrays[root] = arrays[max];
            arrays[max] = temp;
            root = max;
        }
    }

    public void sort(int[] array){

        Heap heap = new Heap(array);
        int root = heap.N;
        while(root > 0){

        }
    }



    public int[] maxHeap(){
        return arrays;
    }

    public String toString(){
        return Arrays.toString(arrays);
    }

    public static void main(String[] args){

        int[] arrays = {27,17,3,16,13,10,1};

        Heap heap = new Heap(arrays);

        System.out.println(heap.toString());

    }


}
