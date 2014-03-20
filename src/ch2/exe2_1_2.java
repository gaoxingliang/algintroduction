package ch2;

/**
 * Created by Administrator on 14-3-19.
 * 习题2.1-2 重写插入排序 按非升序排列
 */
public class exe2_1_2 {
    public static void main(String []args){
        int a[] = new int[]{5,4,8,9,6,5};
        insert_sort(a);
    }

    public static void insert_sort(int a[])
    {
        int temp = 0;
        int n = a.length;
        int j=0;
        for(int i=1;i<n;i++)
        {
            for(j=i-1;j>=0;j--)
            {
                if(a[i]>a[j])
                {
                    //swap
                    a[j+1]=a[j];
                }
            }
            a[j+1]=a[i];


        }
        Print.log(a);
    }

}
