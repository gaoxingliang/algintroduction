package alg;

/**
 * Created by Administrator on 2014/4/6.
 * 这是研究字符替换最短距离的java demo
 * 根据博客：http://www.cnblogs.com/grenet/archive/2010/06/01/1748448.html
 */
public class LevenDistance
{
    public static void main(String []args)
    {
        System.out.println(distance("GGATCGA", "GAATTCAGTTA"));
    }

    /**
     * 这里返回最后匹配的字符串
     * @param a
     * @param b
     * @return
     */
    public static String minmatch(String a,String b)
    {
        int res[][] = distanceConten(a,b);
        int n = res.length;
        int m = res[0].length;
        //int i =
        return null;
    }

    /**
     * 这里返回的是2个字符串之间最短编辑距离的矩阵
     * @param a
     * @param b
     * @return
     */
    private static int [][] distanceConten(String a,String b)
    {
        //null check
        int n = a.length()+1;
        int m =b.length()+1;
        int res[][] = new int [n][m];
        //cal the first row
        for (int i=0;i<n;i++)
        {
            res[i][0] = i;
        }

        for (int j=0;j<m;j++)
        {
            res[0][j] =j;
        }
        for (int i=1;i<n;i++)
        {
            for (int j=1;j<m;j++)
            {
                if (a.charAt(i-1) == b.charAt(j-1))
                {
                    res[i][j] = res[i-1][j-1];
                }else
                {
                    res[i][j] = min(res[i-1][j-1],res[i-1][j],res[i][j-1])+1;
                }
            }
        }
        return res;
    }

    /**
     * 这里返回的是最短编辑距离
     * @param a
     * @param b
     * @return
     */
    public static int distance(String a,String b)
    {
        return distanceConten(a,b)[a.length()][b.length()];
    }

    public static int min(int a, int b, int c)
    {
        return Math.min(c,Math.min(a,b));
    }
}
