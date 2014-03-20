package ch2;

/**
 * Created by Administrator on 14-3-19.
 * this a print util
 */
public class Print
{

    public static void log(int a[])
    {
        for(int i:a)
        {
            System.out.print(i + ",");
        }
        System.out.println();
    }
    public static void log(Object... a)
    {
        if ( a ==null)
        {
            System.out.println("null");
        }
        for(Object t : a)
        {
            System.out.print(t + ",");
        }
        System.out.println();
    }

}
