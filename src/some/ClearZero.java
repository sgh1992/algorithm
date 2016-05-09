package some;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sghipr on 5/9/16.
 */
public class ClearZero {

    public int[][] clearZero(int[][] mat, int n){

        // write code here
        Set<Integer> zeroColumns = new HashSet<>();
        for(int i = 0; i < mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                if(zeroColumns.contains(j))
                    continue;
                if(mat[i][j] == 0){
                    for(int k = 0; k < mat[0].length; k++)
                        mat[i][k] = 0;
                    for(int k = 0; k < mat.length; k++)
                        mat[k][j] = 0;
                    zeroColumns.add(j);
                    break;
                }
            }
        }

        return mat;

    }

    public static void main(String[] args){

        int[][] mat = {{1,2,3},{4,5,6}};
        ClearZero clearZero = new ClearZero();
        clearZero.clearZero(mat,mat.length);
        for(int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++)
                System.out.print(mat[i][j] + "\t");
            System.out.println();
        }

    }


}
