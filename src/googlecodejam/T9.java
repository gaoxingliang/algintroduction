package googlecodejam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * https://code.google.com/codejam/contest/351101/dashboard#s=p2
 */
public class T9 {
	public static void main(String[] args) throws Exception {
		File f = new File("c:\\C-large-practice.in");
		BufferedReader br = new BufferedReader(new FileReader(f));
		int count = Integer.valueOf(br.readLine());
		BufferedWriter bw = new BufferedWriter(new FileWriter("c:\\2.txt"));
		String s;
		char ch[];
		for (int i = 0; i < count; i++) {
			
			s = br.readLine();
			System.out.println("i="+i+" ="+s);
			ch = s.toCharArray();
			bw.write("Case #" + (i + 1) + ": ");
			for (int j = 0; j < ch.length; j++) {
				if (j==0)
				{
					bw.write(get(ch[j],(char)0));
				}else
				{
					bw.write(get(ch[j],ch[j-1]));
				}
			}
			bw.write('\n');
		}
		br.close();
		bw.close();
	}

	public static Map<Character, String> map = new HashMap<Character, String>();

	static
	{
		map.put('a', "2");
		map.put('b', "22");
		map.put('c', "222");
		map.put('d', "3");
		map.put('e', "33");
		map.put('f', "333");
		map.put('g', "4");
		map.put('h', "44");
		map.put('i', "444");
		map.put('j', "5");
		map.put('k', "55");
		map.put('l', "555");
		map.put('m', "6");
		map.put('n', "66");
		map.put('o', "666");
		map.put('p', "7");
		map.put('q', "77");
		map.put('r', "777");
		map.put('s', "7777");
		map.put('t', "8");
		map.put('u', "88");
		map.put('v', "888");
		map.put('w', "9");
		map.put('x', "99");
		map.put('y', "999");
		map.put('z', "9999");
		map.put(' ', "0");
	}
	
	public static String get(char ch, char beforechar) {

		String temp = map.get(ch);
		if (beforechar!=0)
		{
		if(map.get(ch).charAt(0)==map.get(beforechar).charAt(0))
		{
			return " "+temp;
		}
		}
		return temp;

		//
	}
}
