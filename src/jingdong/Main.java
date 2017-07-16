package jingdong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String[] num = scanner.nextLine().split(" ");
			String[] a = scanner.nextLine().split(" ");
			String[] b = scanner.nextLine().split(" ");
			System.out.println(jihe(Integer.parseInt(num[0]), Integer.parseInt(num[1]), a, b));
		}
	}
	
	private static String jinzhi(int n){
		int sum = 0;
		for(int i=2;i<n;i++){
			int tmp = n;
			while(tmp>0){
				sum += tmp%i;
				tmp=tmp/i;
			}
		}
		int a = gcd(sum, n-2);
		System.out.println("a:"+a);
		return (sum/a)+"/"+((n-2)/a);
	}
	
	private static int gcd(int a,int b){
		if(b == 0)
			return a;
		return gcd(b, a%b);
	}
	
	private static int xinyunshu(int n){
		int count = 0;
		for(int i=0;i<=n;i++){
			int fSum = 0;
			int gSum = 0;
			String f = String.valueOf(i);
			for(int j=0;j<f.length();j++){
				fSum += f.charAt(j)-48;
			}
			int tmp = i;
			while(tmp>0){
				gSum += tmp%2;
				tmp = tmp/2;
			}
			if(fSum == gSum)
				count++;
			System.out.println("i:"+i+" "+"fSum:"+fSum+" "+"gSum:"+gSum);
		}
		return count;
	}
	
	//内存超出限制,A、B两个集合会有重复的元素,用pytho写没有问题
	private static String jihe(int n,int m,String[] a,String[] b){
		String str = "";
		if(n>a.length)
			n = a.length;
		if(m>b.length)
			m = b.length;
		Set<Integer> set = new TreeSet<>();
		for(int i=0;i<n;i++)
			set.add(Integer.parseInt(a[i]));
		for(int j=0;j<m;j++)
			set.add(Integer.parseInt(b[j]));
		for(Integer val:set)
			str = str + val + " ";
		System.out.println(str.length());
		return str.substring(0, str.length()-1);
	}
}
