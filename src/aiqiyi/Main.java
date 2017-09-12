package aiqiyi;

import java.util.Scanner;

public class Main {
	public static void repeat(long x1,int k1,long x2,int k2){
		StringBuffer s1 = new StringBuffer();
		StringBuffer s2 = new StringBuffer();
		for(int i=0;i<k1;i++)
			s1.append(String.valueOf(x1));
		for(int j=0;j<k2;j++)
			s2.append(String.valueOf(x2));
		System.out.println(s1.toString());
		System.out.println(s2.toString());
		if(s1.toString().length() > s2.toString().length())
			System.out.println("Greater");
		else if (s1.toString().length() < s2.toString().length()) {
			System.out.println("Less");
		}
		else {
			int flag = s1.toString().compareTo(s2.toString());
			if(flag > 0)
				System.out.println("Greater");
			else if(flag < 0)
				System.out.println("Less");
			else 
				System.out.println("Equal");
		}
	}
	
	public static int pinfanggen(int n,int m){
		int count = 0;
		for(int i=1;i<=n;i++){
			int j = 1;
			while(j<=m){
				double shu = Math.sqrt(i*j);
				if((int)shu == shu){
					break;
				}
				else {
					j++;
				}
			}
			int temp = 1;
			int k = j;
			while(k<=m){
				count++;
				temp++;
				k = j*temp*temp;
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) {
		
	}
	
}
