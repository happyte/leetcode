package meituan;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		while(scanner.hasNext()){
//			int n = Integer.parseInt(scanner.nextLine());
//			String[] strs = scanner.nextLine().split(" ");
//			int[] A = new int[n];
//			for(int i=0;i<n;i++){
//				A[i] = Integer.parseInt(strs[i]);
//			}
//			System.out.println(maxRectangle(A));
//		}
//		int[] A = new int[]{2,1,5,6,2,3};
//		System.out.println(maxRectangle(A));
//		combination(20);
//		System.out.println(dafuwen(6));
		System.out.println(substring("abcde", "cdedf"));
	}
	
	private static int maxRectangle(int[] height){
		int[] h = new int[height.length+1];
		int maxArea = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<height.length;i++){
			h[i] = height[i];
		}
		h[height.length] = 0;
		int i = 0;
		while(i<h.length){
			//当前栈为空或者当前加入的元素大于栈顶元素
			if(stack.isEmpty() || h[i]>h[stack.peek()]){
				stack.push(i++);
			}
			//压入栈索引对应的值小于栈顶的值
			else{
				int t = stack.pop();
				maxArea = Math.max(maxArea, h[t]*(stack.isEmpty()?i:i-stack.peek()-1));
			}
		}
		return maxArea;
	}
	
	private static int combination(int n){
		int[] coins= new int[]{1,5,10};
		int h = coins.length;
		//h代表有多少种硬币，n+1代表拼接目标0-n
		int[][] dp = new int[h][n+1];
		//第一行全部为1，因为无论多少钱，只有硬币为1只有一种情况
		Arrays.fill(dp[0], 1);
		for(int i=0;i<dp.length;i++){
			for(int j=0;j<dp[i].length;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println("");
		}
		System.out.println("");
		for(int i=1;i<h;i++){
			for(int j=1;j<=n;j++){
				int m=j/coins[i];
				for(int k=0;k<=m;k++){
					//假设已知i-1种情况可以拼凑成j块钱，那么i种情况拼凑成j块钱，
					//就是i-1拼凑成j-k*conis[i]的累加和，因为第i行有k种情况可以使用i元钱
					//比如10元的拼凑可以利用前一行拼凑成0元、5块和10元(都是利用1元拼凑的)的情况加上该行使用5元
					//dp[0][0]是个特殊点， 
					dp[i][j] = dp[i][j]+dp[i-1][j-k*coins[i]];
				}
			}
		}
		for(int i=0;i<dp.length;i++){
			for(int j=0;j<dp[i].length;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println("");
		}
		return dp[h-1][n];
	}
	
	//大富翁游戏
	private static int dafuwen(int n){
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		for(int i=2;i<=n;i++){
			for(int j=0;j<i;j++){
				dp[i] += dp[j];
			}
		}
		return dp[n];
	}
	
	//查找两个字符串的最长子串
	private static int substring(String s1,String s2){
		int l1 = s1.length();
		int l2 = s2.length();
		int max = 0;
		int[][] dp = new int[l1+1][l2+1];
		for(int i=1;i<=l1;i++){
			for(int j=1;j<=l2;j++){
				if(s2.charAt(j-1) == s1.charAt(i-1)){
					dp[i][j] = dp[i-1][j-1]+1;
					System.out.println("i:"+i+" "+"j:"+j+" "+"dp:"+dp[i][j]);
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}
	
}
