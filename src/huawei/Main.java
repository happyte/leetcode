package huawei;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void ipcontain(String startIp1,String endIp1,String startIp2,String endIp2){
		String[] startIp1s = startIp1.split("\\.");
		String[] endIp1s = endIp1.split("\\.");
		String[] startIp2s = startIp2.split("\\.");
		String[] endIp2s = endIp2.split("\\.");
		int len = startIp1s.length;
		boolean flag1 = false;
		//3,4在1,2范围内
		for(int i=0;i<len;i++){
			if(Integer.valueOf(startIp2s[i])<Integer.valueOf(startIp1s[i])
			  || Integer.valueOf(endIp2s[i])>Integer.valueOf(endIp1s[i])){
				flag1 = true;
				break;
			}
		}
	
		
	}
	
	public static void paixu(String[] strs,int k){
		Arrays.sort(strs, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				int len1 = str1.length();
				int len2 = str2.length();
				if(len1<3&&len2<3){
					return Integer.valueOf(str1)-Integer.valueOf(str2);
				}
				else if(len1<3&&len2>=3){
					return Integer.valueOf(str1)-Integer.valueOf(str2.substring(len2-3));
				}
				else if (len1>=3&&len2<3) {
					return Integer.valueOf(str1.substring(len1-3))-Integer.valueOf(str2);
				}
				else {
					return Integer.valueOf(str1.substring(len1-3))-Integer.valueOf(str2.substring(len2-3));
				}
			}
		});
		for(int i=0;i<strs.length;i++){
			if(i == k-1)
				System.out.println(strs[i]);
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String[] strs = scanner.nextLine().split(" ");
			int k = scanner.nextInt();
			paixu(strs, k);
		}
	}
}
