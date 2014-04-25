package googlecodejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * google code jam demo
 * https://code.google.com/codejam/contest/351101/dashboard#s=p1
 *
 */
public class Reverse {
	public static void main(String[] args) throws Exception{
		File f = new File("c:\\B-large-practice.in");
		BufferedReader br = new BufferedReader(new FileReader(f));
		int count = Integer.valueOf(br.readLine());
		BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\2.txt"));
		String s;
		for (int i=0;i<count;i++)
		{
			s=br.readLine();
			bw.write("Case #"+(i+1)+": ");
			bw.write(sx(s));
		}
		br.close();
		bw.close();
	}
	
	
	public static String sx(String src)
	{
		String [] sx1= src.split(" ");
		
		StringBuilder sb= new StringBuilder();
		for(int i=sx1.length-1;i>=0;i--)
		{
			sb.append(sx1[i]).append(' ');
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append('\n');
		return sb.toString();
	}
}
