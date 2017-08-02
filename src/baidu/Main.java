package baidu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	//帽子排序问题
	public static int paixu(int length,int[] num){
		if(length  < 3)
			return -1;
		Arrays.sort(num);
		int count = 1;
		for(int i=1;i<length;i++){
			if(num[i] != num[i-1]){
				count ++;
				if(count == 3)
					return num[i];
			}
		}
		return -1;
	}
	
	//这道题是依次走过的，因此不需要动态规划来做
	public static int huijia(int length,int[] N){
		int sum = 0;
		//求顺序走过的路径和
		for(int i=1;i<length;i++)
			sum += Math.abs(N[i]-N[Math.max(i-1, 0)]);
		//求忽略一个点的最大走过的路径
		int diff = 0;
		for(int i=1;i<length-1;i++){
			int d = Math.abs(N[i]-N[i-1])+ Math.abs(N[i+1]-N[i])-Math.abs(N[i+1]-N[i-1]);
			diff = Math.max(d, diff);
		}
		return sum-diff;
	}
	
	//每次取一个数放在数组末尾，最终使得数组有序
	public static int shuzu(int length,int[] num){
		HashMap<Integer, Integer> map = new HashMap<>();
		int count = 0;
		int i=0;
		for(;i<length;i++){
			map.put(num[i], i);
		}
		Arrays.sort(num);
		for(int j=0;j<length-1;j++){
			if(map.get(num[j+1])<map.get(num[j])){
				map.put(num[j + 1], i);
				count++;
			}
		}
		return count;
	}
	
	//只有k个小于符号即('<'')和n-k-1个大于符号(即'>')
	//对于1至n任意的排列中有多少个排列可以使用这些符号使其为合法的不等式数列。
	//dp[i][j]代表有i个数字和j个小于号能构成的数量,最大应该为dp[n][k]
	//四种情况1⃣dp[i-1][j],一个>放在开头
	//2⃣dp[i-1][j-1],一个<放在末尾
	//3⃣dp[i-1][j]*j,一个>放在j个<之间
	//4⃣dp[i-1][j-1]*(i-j-1).一个<放在(i-j-1)个>之间
	public static int pailie(int n,int k){
		int[][] dp = new int[n+1][k+1];
		//第一行0个数字，j个小于号，都应该为0
		//第二行第一个1个数字，0个小于号，0个大于号，应该为1
		dp[1][0] = 1;
		//第一列，i个数字，0个小于号，i-1个小于号
		for(int i=2;i<=n;i++)
			dp[i][0] = 1;
		for(int i=2;i<=n;i++){
			for(int j=1;j<=k;j++){
				dp[i][j] = (dp[i-1][j]+dp[i-1][j-1]+dp[i-1][j]*j+dp[i-1][j-1]*(i-j-1))%2017;
			}
		}
		return dp[n][k];
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine()){
			int l = scanner.nextInt();
			int num = scanner.nextInt();
			System.out.println(pailie(l, num));
		}
	}
}
