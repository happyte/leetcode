package toutiao;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


public class Main {
	/**
	 * a<=b<=c
	   b-a<=10
	   c-b<=10
	   20 35 23 40 需要增加两个 
	   20 23 35 40
	 */
	public static int kecheng(int[] num){
		int len = num.length;
		int count = 0;
		int index = 0;
		Arrays.sort(num);
		while(index<len){
			//a1,a2,a3满足a2-a1<=10,a3-a2<=10,那么下次从a4再取3个
			//a1,a2,a3满足a2-a1<=10,a3-a2>10,那么count+1,a2后面需要添加一门课，下次从a3开始取3个
			if(index+1<len&&index+2<len&&num[index+1]-num[index]<=10){
				if(num[index+2]-num[index+1]<=10){
					index += 3;
				}
				else {
					count++;
					index += 2;
				}
			}
			//a2-a1>10且a2-a1<=20,在a1和a2之间加一门课，下次从a3开始取
			else if (index+1<len&&num[index+1]-num[index]<=20) {
				count++;
				index += 2;
			}
			//a2-a1>20,在a1前后各添加一门课，下次从a2开始取
			else if (index+1<len&&num[index+1]-num[index]> 20) {
				count += 2;
				index++;
			}
		}
		return count;
	}
	
	/**
	 * 给定整数m以及n各数字A1,A2,..An，将数列A中所有元素两两异或，共能得到n(n-1)/2个结果，请求出这些结果中大于m的有多少个。 
	 * 第一行包含两个整数n,m. 
	 * 第二行给出n个整数A1，A2，...，An。
	 * 复杂度超限
	 * 3 10  
	 * 6 5 10
	 */
	public static int yihuo(int n,int m,int[] num){
		int count = 0;
		for(int i=0;i<n-1;i++){
			for(int j=i+1;j<n;j++){
				System.out.println("i:"+i+" j:"+j+" ^:"+(num[i]^num[j]));
				if((num[i]^num[j])>m){
					count++;
				}
			}
		}
		return count;
	}
	
	/**
	 * 对于n=11, m=4, 按字典序排列依次为1, 10, 11, 2, 3, 4, 5, 6, 7, 8, 9, 因此第4个数是2.
	 * 11 4
	 * 2
	 * 内存超限 
	 */
	public static long dict(long n,long m){
		//第一个数是从1开始的
		long cur = 1;
		for(long i=1;i<m;i++){
			if(cur*10<=n){
				cur = cur*10;
			}
			else {
				if(cur>=n){
					cur = cur/10;
				}
				cur++;
				while(cur%10==0)
					cur = cur/10;
			}
		}
		return cur;
	}
	
	private static void printInfo(int num){
		System.out.println(Integer.toBinaryString(num));
	}
	
	//10000000000000000 5555555555555555
	public static void main(String[] args) {
		int number = -8;
		//原始数二进制
		printInfo(number);
		printInfo(number<<2);
		printInfo(number>>2);
		printInfo(number>>>2);
	}
}
