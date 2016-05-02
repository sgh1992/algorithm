package some;

import java.util.*;

public class Zipper {
    public String zipString(String iniString) {
        // write code here
        if(iniString.length() == 0)
            return iniString;
        StringBuilder builder = new StringBuilder();
        int count = 1;
        int index = 1;
        while(index < iniString.length()){
            if(iniString.charAt(index) == iniString.charAt(index - 1))
                count++;
            else{
                builder.append(iniString.charAt(index - 1)).append(count);
                count = 1;
            }
            index++;
        }

        builder.append(iniString.charAt(index - 1)).append(count);

        if(iniString.length() <= builder.length())
            return iniString;

        return builder.toString();
    }

    public static void main(String[] args){
        Zipper zipper = new Zipper();
        System.out.println(zipper.zipString("aabcccccaaa"));
    }

}