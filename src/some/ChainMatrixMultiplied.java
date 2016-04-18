package some;
/**
 * Created by sghipr on 9/04/16.
 *
 * 矩阵链相乘
 * A(100*5) B(5 * 20) C(20 * 7)
 * 其中 A（100 ＊ 5）表示矩阵A的规模为100行*5列的矩阵.
 * 假如直接计算ABC即需要100 ＊ 5 ＊ 20 ＋ 100 ＊ 20 ＊ 7 = 24000次标量运算.
 * A＊（BC） ＝ 5 * 20 * 7 + 100 * 5 * 7 = 4200次标量运算.
 * 即通过恰当地括号组合之后的矩阵链乘法的效率比直接运算要快得多.
 * 那么问题来了,应该通过怎样的括号组合方法来使得矩阵链的运行时间最短?
 *
 * 问题描述:总共有A1A2A3...An个矩阵,每个矩阵Ai的规模为pi-1 * pi
 * 假设有矩阵链的输入为p = <p0,p1,p2,...pn> 即对应的是矩阵A1,A2,...An;
 * 求应该对矩阵链如何组合之后的矩阵相乘时的标量计算量最小.
 *
 * 解决方法：
 * 总共有A1A2A3...An个矩阵.
 * 用Pk表示前k个矩阵的括号组合的組数.
 * 那么总共有sum(pk * p(n - k))組括号组合数. k = 1,2,3,4,...n-1;
 * 可以知道所有的括号组合組数为指数级别的，因此通过暴力搜索的方法是不可行的.
 *
 * 假设A1A2A3...An的最佳分割是k,即分为A1A2...Ak 与 Ak+1Ak+2...An两組.
 * 那么容易得知A1A2...Ak与Ak+1Ak+2...An应该进行单独地求解.即这两个子问题地解也应该是独立地最优解.
 * 假如它不是独立地最优解地話，那么将A1A2...Ak的解放入到Ak+1Ak+2..An中，得到的解更优的話，那么与已知矛盾(即k不就应该是最佳分割点)
 * 假设m[i,j]表示矩阵鏈AiAi+1...Aj的最优解(最少标量运算), A(p*q) * B(q*r)的标量运算为pqr;
 * 那么
 * m[i,j] = 0     i=j
 * m[i,j] = min{m[i,k] + m[k + 1,j] + pi-1 * pj * pk}     i=<k<j  i < j
 *
 * 因此得出了原问题的递归式.
 * 那么根据自底向上地方法,判断计算m[i,j]需要用到哪些变量.即需要所有小于j - i + 1长度的矩阵链的最优解.
 * s[i,j]为AiAi+1...Aj的最佳分割点k,即将之分割为AiAi+1Ai+2...Ak,Ak+1Ak+2...Aj
 * 假设需要求A1A2...An的矩阵链,即m[1,n]的最优解.
 * 即 for l = 2 to n //链长
 *         for i = 1 to n-l+1
 *              j = i + l - 1
 *              m[i,j] = 正无穷
 *              for k = i to j - 1
 *                  if m[i,k] + m[k + 1,j] + pi-1 * pk * pj < m[i,j]
 *                      m[i,j] = m[i,k] + m[k + 1,j] + pi-1 * pk * pj
 *                      s[i,j] = k
 *
 *
 * 同时根据s[i,j]还原出AiAi+1...Aj的最佳分割点.
 * ChainMatrixMultipled-Print(i,j,s)
 *      if i == j
 *          print Ai
 *      else
 *          print "("
 *          print ChainMatrixMultipled-Print(i,s[i,j],s)
 *          print CahinMatrixMultipled-Print(s[i,j] + 1,j,s)
 *          print ")"
 */
public class ChainMatrixMultiplied {

    /**
     * 矩阵个数.
     */
    private int N;

    /**
     * 矩阵链的输入
     **/
    private int[] p;

    /**
     * 矩阵AiAi+1...Aj的最佳解.
     */
    private int[][] m;

    /**
     * 矩阵AiAi+1...Aj的最佳分割点.
     */
    private int[][] s;

    public ChainMatrixMultiplied(int[] p){
        this.p = p;
        N = p.length - 1;
        m = new int[N + 1][N + 1];
        s = new int[N + 1][N + 1];
        ChainMatrixDP();
    }

    public void ChainMatrixDP(){
        for(int i = 1; i <= N; i++)
            m[i][i] = 0;
        /**
         * 总共有三层循环,因此它的时间复杂度度为O（N^3)
         * 空间复杂度为O(N^2)
         * 其中N为矩阵链中矩阵的个数.
         */
        for(int l = 2; l <= N; l++)
            for(int i = 1; i <= N - l + 1; i++){
                int j = i + l - 1;
                for(int k = i; k < j; k++){
                    int cost = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if(k == i || cost < m[i][j]){
                        m[i][j] = cost;
                        s[i][j] = k;
                    }
                }
            }
    }

    public void printBestSoluation(int i,int j){
        printBestSoluation(i,j,s);
    }

    public void printBestSoluation(int i,int j, int[][] s){
        if(i == j)
            System.out.printf("A%d",i);
        else{
            System.out.print("(");
            printBestSoluation(i,s[i][j],s);
            printBestSoluation(s[i][j] + 1,j,s);
            System.out.print(")");
        }
    }

    public static void main(String[] args){
        int[] p = {30,35,15,5,10,20,25};
        ChainMatrixMultiplied chainMatrixMultiplied = new ChainMatrixMultiplied(p);
        chainMatrixMultiplied.printBestSoluation(1,6);
    }

}
