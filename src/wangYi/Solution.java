package wangYi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	private static int palindrome(int n,int[] item){
		int leastTime = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			list.add(item[i]);
		}
		 //由于元素是两个两个操作的，所以最后一次剩余的肯定是1个,无需判断
		while(list.size() > 1){
			//首小于尾
			if(list.get(0) < list.get(list.size() - 1)){
				//取出链表中的第一第二个元素
				int a = list.get(0);
				int b = list.get(1);
				list.set(0, a+b);
				//把第一个移除
				list.remove(1);
				//操作加1
				leastTime++;
			}
			//首大于尾
			else if(list.get(0) > list.get(list.size() - 1)){
				//倒数第一和第二个元素相加
				int a = list.get(list.size() - 1);
				int b = list.get(list.size() - 2);
				list.set(list.size() - 2, a+b);
				//移除最后一个元素
				list.remove(list.size() - 1);
				leastTime++;
			}
			else{
				list.remove(0);
				list.remove(list.size() - 1);
			}
		}	
		return leastTime;
	}
	
	//复杂度过大
	private static int elegantNum(int n){
		//这里也是一个注意点,不能强转为int,否则就会坐标轴重复计算
		double radio = Math.sqrt(n);
		System.out.println(radio);
		int count = 0;
		//i<radio没有等号，就是避免x,y轴的重复计算
		for(int i=0;i<radio;i++){
			double j = Math.sqrt(n-i*i);
			if((int)j==j)
				count++;
		}
		return 4*count;
	}
	
	public static int tiaoshiban(int N,int M){
		int[] dp = new int[M+1];
		Arrays.fill(dp, 65536);
		dp[N] = 0;
		for(int i=N;i<=M;i++){
			ArrayList<Integer> res = yueshu(i);
			for(int j=0;j<res.size();j++){
				int m = res.get(j);
				if(i+m>M)
					continue;
				dp[i+m] = Math.min(dp[i]+1, dp[i+m]);
			}
		}
		for(int i=0;i<=M;i++)
			System.out.print(dp[i]+" ");
		System.out.println();
		return dp[M];
	}
	
	private static ArrayList<Integer> yueshu(int K){
		ArrayList<Integer> res = new ArrayList<>();
		for(int i=2;i<=Math.sqrt(K);i++){
			if(K%i==0){
				res.add(i);
				if(K/i != i)
					res.add(K/i);
			}
		}
		return res;
	}
	
	//暗黑字符串
	private static int anhei(int n){
		int[] dp = new int[n+1];
		dp[1] = 3;
		dp[2] = 9;
		int i=3;
		while(i<=n){
			dp[i] = 2*dp[i-1]+dp[i-2];
			i++;
		}
		return dp[n];
	}
	
	//rev
	private static int rev(String x){
		StringBuffer xBuffer = new StringBuffer(x);
		String xRev = xBuffer.reverse().toString();
		return Integer.parseInt(xRev);
	}
	
	//odd
	private static long odd(long n){
		if(n == 1)
			return 1;
		//奇数sum[n]=sum[n-1]+n
		if(n%2 == 1){
			return odd(n-1)+n;
		}
		else{
			return odd(n/2)+n*n/4;
		}
	}
	
	//6或8倍卖苹果
	private static int maipingguo(int n){
		int max6Num = n/6;
		int minCount = Integer.MAX_VALUE;
		int tempCount = 0;
		for(int i=0;i<=max6Num;i++){
			if((n-6*i)%8==0){
				tempCount = i+(n-6*i)/8;
				if(tempCount < minCount)
					minCount = tempCount;
			}
		}
		if(minCount == Integer.MAX_VALUE)
			return -1;
		return minCount;
	}
	
	//A+B,B+C,A-B,B-C
	private static String calc(int[] nums){
		String str = "";
		int A = (nums[0]+nums[2])/2;
		int B1 = (nums[2]-nums[0])/2;
		int B2 = (nums[1]+nums[3])/2;
		int C = (nums[3]-nums[1])/2;
		if(B1 != B2)
			return "NO";
		return str+A+" "+B1+" "+C;
	}
	
	public static void main(String[] args) {
		System.out.println(tiaoshiban(4, 24));
	}
}
