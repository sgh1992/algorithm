package some;

/**
 * Created by sghipr on 5/9/16.
 * 旋转.
 * 判断字符串s2是否由s1旋转而得到的.
 */
public class ReverseEqual {

    public boolean checkReverseEqual(String s1, String s2) {
        // write code here
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();


        if(array1.length != array2.length)
            return false;
        int N = array1.length;

        int i = 0;
        while(i++ < N){
            rotateToLeft(array2,1);
            if(judge(array1,array2))
                return true;
        }
        return false;
    }



    public boolean judge(char[] array1, char[] array2){

        if(array1.length != array2.length)
            return false;

        for(int i = 0; i < array1.length; i++)
            if(array1[i] != array2[i])
                return false;

        return true;
    }



    public void rotateToLeft(char[] array,int r){
        swap(array, 0, array.length - r - 1);
        swap(array,array.length - r, array.length - 1);
        swap(array,0,array.length - 1);
    }

    public void swap(char[] array, int i, int j){
        while(i < j){
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args){

        ReverseEqual reverseEqual = new ReverseEqual();
        String s1 = "ookvnorsiwwddjkpqasgbqjqrgsyriuxsruxidwprrxagjxzbwwgztsgndyfoelcgfzqzxzximtdwjlkhouxqrdzdguhxweqkvqabcltywygpbgeprmhbltnhkhynvpuszoqdrspwldgecrbvzsiywkfzphczpxyeyzhdhnegmodldikwnjhlwer";
        String s2 = "sgndyfoelcgfzqzxzximtdwjlkhouxqrdzdguhxweqkvqabcltywygpbgeprmhbltnhkhynvpuszoqdrspwldgecrbvzsiywkfzphczpxyeyzhdhnegmodldikwnjhlwerookvnorsiwwddjkpqasgbqjqrgsyriuxsruxidwprrxagjxzbwwgzt";

        System.out.println(reverseEqual.checkReverseEqual(s1,s2));

    }

}
