package googlecodejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * https://code.google.com/codejam/contest/351101/dashboard#s=p0
 */
public class StoreCredit {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		File f = new File("c:\\A-large-practice.in");
		BufferedReader br = new BufferedReader(new FileReader(f));
		int count = Integer.valueOf(br.readLine());
		BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\2.txt"));
		int res;
		String []x;
		int a[];
		boolean continued=true;
		for (int i=0;i<count;i++)
		{
			continued=true;
			res = Integer.valueOf(br.readLine());
			br.readLine();
			x = br.readLine().split(" ");
			a=xx(x);
			int len = a.length;
			for(int j=0;j<len&&continued;j++)
			{
				if (a[j]>res)
				{
					continue;
				}
				for(int k=j+1;k<len;k++)
				{
					if (a[k]>res)
					{
						continue;
					}
					if (a[k]+a[j]==res)
					{
						continued=false;
						bw.write("Case #"+(i+1)+": "+(j+1)+" "+(k+1));
						bw.write("\n");
						break;
					}
				}
			}
		}
		br.close();
		bw.close();
		
		
	}
	
	public static int [] xx(String [] x)
	{
		int a[]= new int[x.length];
		
		for(int i=0;i<x.length;i++)
		{
			a[i]=Integer.valueOf(x[i]);
		}
		return a;
	}

}
