package some;

/**
 * Created by sghipr on 4/20/16.
 * 计算三元组的最小值.
 * 问题描述:
 * 已知有三个数组且都是有序排列的.分别为ａ,ｂ,ｃ
 * 现在分别从数组ａ,ｂ,ｃ中选取一个元素ｉ,ｊ,ｋ，使得max{|a[i] - b[j]|,|b[j] - c[k]|,|a[i] - c[k]|} 这个值最小.
 *
 * 解题思路:
 * 三元组的大小，实质上是受最小的那个元素的影响.
 * 因此分别用i,j,k来标志数组a,b,c中的元素.
 * 当a[i]最小时，c[k]最大，则计算c[k] - a[i] = curMax,同时移动a[i]到下一个位置.然后继续比较a[i],b[j],c[k]的大小.计算curMax > c[k] - a[i],则更新三元组的最小值.
 * 直到某一个数组完全遍历全.
 */
public class ThreeGroup {


    private int minThreeDistance;

    public ThreeGroup(int[] a,int [] b, int[] c){
        minThreeDistance = threeGroupDistance(a,b,c);
    }

    public int threeGroupDistance(int[] a,int[] b, int[] c){

        int[] indexs = {0,0,0};
        boolean start = true;
        int distance = 0;
        while(indexs[0] < a.length && indexs[1] < b.length && indexs[2] < c.length){
            int[] minAndMaxIndex = getMinAndMaxIndex(a[indexs[0]],b[indexs[1]],c[indexs[2]]);
            if(start || distance > minAndMaxIndex[2]){
                distance = minAndMaxIndex[2];
                start = false;
            }
            indexs[minAndMaxIndex[0]]++;
        }
        return distance;
    }

    public int[] getMinAndMaxIndex(int ai, int bj, int ck){

        int minIndex = 0;
        int minValue = ai;
        int maxIndex = 0;
        int maxValue = ai;

        if(bj > maxValue){
            maxValue = bj;
            maxIndex = 1;
        }

        if(bj < minValue){
            minValue = bj;
            minIndex = 1;
        }

        if(ck > maxValue){
            maxIndex = 2;
            maxValue = ck;
        }

        if(ck < minValue){
            minIndex = 2;
            minValue = ck;
        }
        return new int[]{minIndex,maxIndex,maxValue - minValue};
    }

    public int getMinThreeDistance(){
        return  minThreeDistance;
    }

    public static void main(String[] args){

        int[] a = {5,16,20};
        int[] b = {13,14,15,17,35};
        int[] c = {19,22,24,29,32,42};

        ThreeGroup threeGroup = new ThreeGroup(a,b,c);
        System.out.println("MinThreeGroupDistance:\t" + threeGroup.getMinThreeDistance());
    }




}
