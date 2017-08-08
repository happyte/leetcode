package xiaozhaoII;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	//找出其中最长的公共连续子串,希望你能帮助他,并输出其长度
	public static int zifuchuan(String s1,String s2){
		int l1 = s1.length();
		int l2 = s2.length();
		int[][] dp = new int[l1+1][l2+1];
		int max = 0;
		for(int i=1;i<=l1;i++){
			for(int j=1;j<=l2;j++){
				if(s1.charAt(i-1) == s2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1]+1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}
	
	//65456 456789 13
	public static int zhengchu(int a,int b,int c){
		while(a%c!=0)
			a++;
		return (b-a)/c+1;
	}
	
	//N根木棒的三角形取法
	public static int mubang(int n,int[] num){
		Arrays.sort(num);
		int count = 0;
		for(int i=0;i<num.length;i++){
			for(int j=i+1;j<num.length;j++){
				for(int k=j+1;k<num.length;k++){
					if(num[i]+num[j]>num[k])
						count++;
					else
						break;
				}
			}
		}
		return count;
	}
	
	//平衡数
	public static String pinghengshu(String num){
		int length = num.length();
		for(int i=1;i<length;i++){
			long left = 1;
			long right = 1;
			for(int j=0;j<i;j++){
				left *= (num.charAt(j)-'0');
			}
			System.out.println("left:"+left);
			for(int j=i;j<length;j++){
				right *= (num.charAt(j)-'0');
			}
			System.out.println("right:"+right);
			if(left == right)
				return "YES";
		}
		return "NO";
	}
	
	public static int juxing(int n,int[] x,int[] y){
		//找到x轴的最小最大值，找到y轴的最小最大值
		int xMin = 65536;
		int xMax = -65536;
		int yMin = 65536;
		int yMax = -65536;
		for(int i=0;i<n;i++){
			if(x[i]>xMax)
				xMax = x[i];
			if(x[i]<xMin)
				xMin = x[i];
			if(y[i]>yMax)
				yMax = y[i];
			if(y[i]<yMin)
				yMin = y[i];
		}
		System.out.println("xMax: "+xMax+" xMin:"+xMin);
		System.out.println("yMax: "+yMax+" yMin:"+yMin);
		return (yMax-yMin)*(xMax-xMin);
	}
	
	//字符串两个位置交换，种类
	public static int jiaohuan(int n,String[] strs){
		Map<String, Integer> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		for(int i=0;i<n;i++){
			//对每个字符串进行从小到大排序
			char[] arr = strs[i].toCharArray();
			Arrays.sort(arr);
			String newStr = new String(arr);
			if(!set.contains(newStr)){
				set.add(newStr);
			}
		}
		return set.size();
	}
	
	//n数组存储的是第i个物品有几个0，m数组存储第i个物品有几个1，x代表总共有的物品数
	//N是拥有的可用的0的个数，M是拥有的可用1的个数
	//dp[i][j]表示用i个0和j个1所能构成的最多物品数
	//dp[M][N]是最终要求的结果
	public static int xinshijie(int[] n,int[] m,int x,int M,int N){
		int[][] dp = new int[N+1][M+1];
		//dp[i][j] = max{没选该物品dp[i][j],选了该物品dp[i-n[k]][j-m[k]]+1}
		for(int k=1;k<=x;k++){
			for(int i=N;i>=n[k];i--){
				for(int j=M;j>=m[k];j--){
					dp[i][j] = Math.max(dp[i][j], dp[i-n[k]][j-m[k]]+1);
				}
			}
		}
		return dp[N][M];
	}
	
	public static void main(String[] args) {
		int[] A = new int[]{1,2,3,4,5,9996,9997,9998,9999,10000};
		System.out.println(mubang(10, A));
//		Scanner scanner = new Scanner(System.in);
//		String[] canshu = scanner.nextLine().split(" ");
//		int x = Integer.valueOf(canshu[0]);
//		int N = Integer.valueOf(canshu[1]);
//		int M = Integer.valueOf(canshu[2]);
//		int[] n = new int[x+1];
//		int[] m = new int[x+1];
//		for(int i=1;i<=x;i++){
//			char[] arr = scanner.nextLine().toCharArray();
//			for(int j=0;j<arr.length;j++){
//				if(arr[j] == '0'){
//					n[i]++;
//				}
//				else {
//					m[i]++;
//				}
//			}
//		}
//		System.out.println(xinshijie(n, m, x, M, N));
	}
}
