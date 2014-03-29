package ch2;

import java.math.BigInteger;

/**
 * Created by Administrator on 14-3-20.
 * 考虑2个n位二进制表示的整数相加
 */
public class exe2_1_4
{
    public static void main(String[] args)
    {
        int a[] = {0, 1, 1, 0, 1};
        int b[] = {1, 0, 1};
        add(a, b);
        BigInteger bi = null;
    }

    public static void add(int[] a, int[] b)
    {

        Print.log(a);
        Print.log(b);

        //要求第一个长 第二个段
        if (a.length < b.length)
        {
            int t[] = a;
            a = b;
            b = t;
        }


        int x = a.length, y = b.length;
        int c[] = new int[x + 1];
        int carry = 0;
        int i = 0;
        while (y > 0)
        {
            y--;
            if (a[y] + b[y] == 2)
            {
                c[x] = 0;
                carry = 1;
            }
            else
            {
                if (a[y] + b[y] == 1)
                {
                    if (carry == 1)
                    {
                        c[x] = 0;
                    }
                    else
                    {
                        c[x] = 1;
                        carry = 0;
                    }
                }
                else
                {
                    c[x] = carry;
                    carry = 0;
                }
            }
            System.out.print("carry:" + carry + " ");
            Print.log(c);
            x--;
        }
        Print.log(c);
        while (x-- > 0)
        {
            c[x] = a[x];
        }
        Print.log(c);
    }
}
