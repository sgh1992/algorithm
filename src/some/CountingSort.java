package some;

import java.util.Arrays;

/**
 * Created by sghipr on 2/05/16.
 * 计数排序.
 */
public class CountingSort {
    public static int[] sort(int[] A){
        return sort(A,max(A));
    }
    public static int[] sort(int[] A,int k){
        int[] B = new int[A.length];
        int[] C = new int[k + 1];
        for(int i = 0; i < C.length; i++)
            C[i] = 0;

        for(int i = 0; i < A.length; i++)
            C[A[i]] += 1;

        for(int i = 1; i <= k; i++)
            C[i] += C[i - 1];

        /**
         * 注意，从高位到低位，它是一个稳定的排序.
         */
        for(int i = A.length - 1; i >= 0; i--){
            B[C[A[i]] - 1] = A[i];
            C[A[i]] -= 1;
        }
        return B;
    }

    public static int max(int[] A){
        int k = A[0];
        for(int i = 1; i < A.length; i++)
            if (k < A[i])
                k = A[i];
        return k;
    }

    public static void main(String[] args){
        int[] A = {2,1,3,3,9,7,6,6};
        int[] sort = CountingSort.sort(A);
        System.out.println(Arrays.toString(sort));
    }
}
