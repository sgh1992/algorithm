package some;

import java.util.Arrays;

/**
 * Created by sghipr on 2/05/16.
 * 矩阵旋转旋转90度后的矩阵
 * 注意,这里要求是原地旋转.即不能添加其它的缓存矩阵.
 *
 * 算法思想:主要是通过观察数据得到一个规律.
 * 即(x,y) 90度旋转过后得到 (y,n - x)
 * 旋转的方式是通过由外向内逐渐进行的.
 * 其中layer < rank/2
 */
public class MatrixRotate {

    public static int[][] rotate(int[][] mat,int n){
        for(int layer = 0; layer < n/2; layer++){
            for(int first = layer; first < n - 1 - layer; first++){
                int top = mat[layer][first];
                int right = mat[first][n - 1 - layer];
                int bottom = mat[n - 1 - layer][n - 1 - first];
                int left = mat[n - 1- first][layer];

                mat[layer][first] = left;
                mat[first][n - 1 - layer] = top;
                mat[n - 1 - layer][n - 1 - first] = right;
                mat[n - 1 - first][layer] = bottom;
            }
        }

        return mat;
    }

    public static void print(int[][] matrix){
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }

    public static void main(String[] args){
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        MatrixRotate.print(MatrixRotate.rotate(matrix, 3));

    }
}
