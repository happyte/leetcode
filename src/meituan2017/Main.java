package meituan2017;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	//n为组数，s为每个组的学生,要保证不分到自己的试卷，保证收上去的试卷足够分
	public static String fenshijuan(int n,int s[]){
		//数组进行逆序
		Arrays.sort(s);
		int[] num = new int[s.length];
		for(int i=0;i<s.length;i++){
			num[i] = s[s.length-i-1];
		}
		int sum = 0;
		for(int i=1;i<num.length;i++)
			sum += num[i];
		if(num[0]>sum)
			return "NO";
		else
			return "YES";
	}
	
	public static int K(int N,int[] num,int K){
		int len = num.length;
		int max = 0;
		for(int i=0;i<len;i++){
			int sum = 0;
			int j=i;
			for(;j<len;j++){
				sum += num[j];
			}
			if(sum%K == 0){
				System.out.println("sum:"+sum+" j:"+j+" i:"+i);
				max = Math.max(max, j-i);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] num = new int[]{1,2,3,4,5};
		System.out.println(K(5, num, 5));
	}

}
