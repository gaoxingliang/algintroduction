package gxl.javacounter;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteOrder;
import java.security.AccessController;

/**
 * Created by Administrator on 2014/6/1.
 */
public class UnsafeUtils
{
    public static Unsafe unsafe;
    static
    {
        try
        {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            //从null中获取该对象 因为是类对象所以没关系
            unsafe = (Unsafe) field.get(null);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static ByteOrder byteOrder = ByteOrder.nativeOrder();
    public static boolean unaligned;
    public static boolean unalignedKnown = false;

    static boolean unaligned() {
        if (unalignedKnown)
            return unaligned;
        String arch = AccessController
                .doPrivileged(new sun.security.action.GetPropertyAction(
                        "os.arch"));
        unaligned = arch.equals("i386") || arch.equals("x86")
                || arch.equals("amd64") || arch.equals("x86_64");
        unalignedKnown = true;
        return unaligned;
    }

}
