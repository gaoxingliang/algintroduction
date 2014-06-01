package gxl.javacounter;

import sun.nio.ch.DirectBuffer;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2014/6/1.
 */
public class JavaCounter
{

    public static void main(String[] args) throws Exception {

        int threads = 3;
        final int itr = 3 * 1000000;
        System.out.println(String.format("Threads %s , inc %s" , threads, itr));
        ExecutorService es = Executors.newFixedThreadPool(threads);
        final File file = new File("counter.long");
        file.createNewFile();
        System.out.println(file.getAbsolutePath());

        for(int x=0;x<threads;x++)
        {
            es.submit( new Callable<Void>(){
                @Override
                public Void call() throws Exception {
                    inc(file,itr);
                    return null;
                }
            });
        }
        es.shutdown();
    }


    private static void inc(File file, int iteration) throws Exception
    {
        JavaCounter counter = new JavaCounter(file.getName());
        int cnt=0;
        long start = System.nanoTime();
        int failCount=0;
        while(cnt<iteration)
        {
            if(counter.increment())
            {
                cnt++;
            }
            else
            {
                failCount++;
            }
        }
        long total = System.nanoTime() - start;
        double tp = (TimeUnit.SECONDS.toNanos(iteration) / total) / 1000000d;
        System.out.println(String.format(" TPS mops %s , Failed CAS %s , time %s ms , Current Value %s" , tp ,failCount, TimeUnit.NANOSECONDS.toMillis(total), counter.get()));
    }

    MappedByteBuffer mem;
    long startAddress;
    public JavaCounter(String fileName) throws Exception
    {
        FileChannel fc  = new RandomAccessFile(fileName,"rw").getChannel();
         mem = fc.map(FileChannel.MapMode.READ_WRITE,0,8);
         startAddress = ((DirectBuffer)mem).address();
    }

    public boolean increment() {
        long orignalValue = readVolatile(startAddress);
        long value = convert(orignalValue);
        return UnsafeUtils.unsafe.compareAndSwapLong(null,
                startAddress,orignalValue, convert(value + 1));
    }

    public long get() {
        long orignalValue = readVolatile(startAddress);
        return convert(orignalValue);
    }

    // Only unaligned is implemented
    private static long readVolatile(long position) {
        if (UnsafeUtils.unaligned()) {
            return UnsafeUtils.unsafe.getLongVolatile(null, position);
        }
        throw new UnsupportedOperationException();
    }

    private static long convert(long a) {
        if (UnsafeUtils.unaligned()) {
            return (UnsafeUtils.byteOrder.equals(ByteOrder.BIG_ENDIAN)? a : Long.reverseBytes(a));
        }
        throw new UnsupportedOperationException();
    }

}
