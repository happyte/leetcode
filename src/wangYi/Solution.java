package wangYi;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		while(scanner.hasNext()){
//			String[] strs = scanner.nextLine().split(" ");
//            int[] A = new int[strs.length];
//            for(int i=0;i<strs.length;i++){
//            	A[i] = Integer.parseInt(strs[i]);
//            }
//            System.out.println(calc(A));
//		}
//		int[] A = new int[]{5,-3,15,13};
//		System.out.println(calc(A));
//		int[] A = new int[]{1,1,1,1,3,4,3};
//		System.out.println(palindrome(4, A));
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int n = Integer.parseInt(scanner.nextLine());
            String[] strs = scanner.nextLine().split(" ");
			int[] A = new int[strs.length];
            for(int i=0;i<strs.length;i++){
                A[i] = Integer.parseInt(strs[i]);
            }
            System.out.println(palindrome(n,A));
		}
	}
	
	private static int palindrome(int n,int[] nums){
		int count = 0;
		int temp = 0;
		int sum = 0;
		//从后往前
		int i = nums.length-1;
		int j = 0;
		int index = 0;
		for(;i>=0;i--){
			//从前往后
			for(j=index;j<nums.length;j++){
				if(temp < nums[i]){
					System.out.println("temp:"+temp+" "+"num[i]:"+nums[i]);
					temp+=nums[j];
					count++;
				}
				if(temp > nums[i]){
					System.out.println("==============");
					count++;
				}
				if(temp == nums[i]){
					break;
				}
			}
			index = j+1;
			temp = 0;
			sum += count-1;
			count = 0;
			if((i-j)==1)
				break;
		}
		return sum;
	}
	
	//复杂度过大
	private static int elegantNum(int n){
		int radio = (int) Math.sqrt(n);
		int count = 0;
		int radioCount = 0;
		int diaCount = 0;
		//先求x,y轴的
		for(int i=0;i<=radio;i++){
			if(i*i == n)
				radioCount++;
		}
		for(int j=0;j<=radio;j++){
			if(j*j == n)
				radioCount++;
		}
		//求对角线是否有满足的
		for(int k=0;k<=radio;k++){
			if(k*k*2 == n)
				diaCount++;
		}
		//x,y正半轴，且y<x
		for(int i= 0;i<=radio;i++){
			for(int j=0;j<i;j++){
				if((i*i+j*j) == n)
					count++;
			}
		}
		if(radioCount == 0)
			return 4*(2*count+diaCount);
		return 4*(2*count+diaCount-1);
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
}
