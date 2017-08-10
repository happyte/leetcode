package wangyichunzhao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

//海量数据处理，并发(互斥锁与信号量,阻塞非阻塞,阻塞队列非阻塞IO,轮询3种机制,IO复用)

public class Main {
	//内存爆炸,双核CPU同时处理任务
	public static int cpu(int[] num){
		//两个核的占用时间最好都是相同的，比如都是sum/2
		//dp[i][j]表示选前i个任务，容量为j的情况下可存放的时间。最大容量为sum/2
		int sum = 0;
		for(int i=0;i<num.length;i++)
			sum += num[i];
		int[] dp = new int[sum/2+1];
		for(int i=1;i<=num.length;i++){
			for(int j=sum/2;j>=num[i-1];j--){
				//不选第i个任务，容量为j,dp[i-1][j]
				//选第i个任务，dp[i-1][j-num[i]]
				dp[j] = Math.max(dp[j], dp[j-num[i-1]]+num[i-1]);
			}
		}
		int max = Math.max(dp[sum/2], sum-dp[sum/2]);
		return max<<10;
	}
	
	//男生女生排队，输入GGBBG
	public static int nannv(String str){
		//最终换成的形式只能为GGGBB或者BBGGG
		int g = 0;
		int b = 0;
		int gCount = 0;
		int bCount = 0;
		for(int i=0;i<str.length();i++){
			//换成前面都是G
			if(str.charAt(i) == 'G'){
				gCount += (i-g);
				g++;
			}
			//换成前面都是B
			else if (str.charAt(i) == 'B') {
				bCount += (i-b);
				b++;
			}
		}
		return Math.min(gCount, bCount);
	}
	
	//不重复输出数组中最后出现的那个元素
	public static String nixu(int n,int[] num){
		StringBuffer sb = new StringBuffer();
		Set<Integer> set = new LinkedHashSet<>();
		for(int i=n-1;i>=0;i--)
			set.add(num[i]);
		Object[] arr = set.toArray();
		for(int j=arr.length-1;j>=1;j--)
			sb.append(arr[j]+" ");
		sb.append(arr[0]);
		return sb.toString();
	}
	
	//魔力手环,n为长度，k为变化的次数
	//算法复杂度过大
	public static String moli(int n,long k,int[] num){
		System.out.println("k:"+k);
		StringBuffer sb = new StringBuffer();
		for(long i=0;i<k;i++){
			int temp = num[0];
			for(int j=0;j<n;j++){
				if(j == n-1)
					num[j] = (num[j]+temp)%100;
				else
					num[j] = (num[j]+num[j+1])%100;
			}
		}
		for(int i=0;i<n-1;i++)
			sb.append(num[i]+" ");
		sb.append(num[n-1]);
		return sb.toString();
	}
	
	//奇怪的表达式
	public static long qiguai(String str){
		int i = 0;
		long res = 0;
		while((str.charAt(i)-'0')>=0&&(str.charAt(i)-'0')<=9){
			res = 10*res+(str.charAt(i)-'0');
			i++;
		}
		for(int j=i;j<str.length();j++){
			if(str.charAt(j) == '+'){
				int k = j+1;
				long temp = 0;
				while((str.charAt(k)-'0')>=0&&(str.charAt(k)-'0')<=9){
					temp = 10*temp+(str.charAt(k)-'0');
					k++;
					if(k>=str.length())
						break;
				}
				res += temp;
			}
			if(str.charAt(j) == '-'){
				int k = j+1;
				long temp = 0;
				while((str.charAt(k)-'0')>=0&&(str.charAt(k)-'0')<=9){
					temp = 10*temp+(str.charAt(k)-'0');
					k++;
					if(k>=str.length())
						break;
				}
				res -= temp;
			}
			if(str.charAt(j) == '*'){
				int k = j+1;
				long temp = 0;
				while((str.charAt(k)-'0')>=0&&(str.charAt(k)-'0')<=9){
					temp = 10*temp+(str.charAt(k)-'0');
					k++;
					if(k>=str.length())
						break;
				}
				res *= temp;
			}
		}
		return res;
	}
	
	//集合，S = { p/q | w ≤ p ≤ x, y ≤ q ≤ z }
	public static int jihe(int w,int x,int y,int z){
		int count = 0;
		Set<Float> set = new HashSet<>();
		for(float p=w;p<=x;p++){
			for(float q=y;q<=z;q++){
				set.add(p/q);
			}
		}
		return set.size();
	}
	
	//涂格子,某一列中拥有相同颜色的最大的区域去涂画
	//需要连续的相同颜色的格子
	public static int tugezi(int n,char[][] matrix){
		int max = 0;
		for(int i=0;i<n;i++){
			StringBuffer sb = new StringBuffer();
			for(int j=0;j<n;j++){
				sb.append(matrix[j][i]);
			}
			int bLocal = pipei(sb.toString(), n, 'B');
			int wLocal = pipei(sb.toString(), n, 'W');
			System.out.println("bLocal:"+bLocal+" wLocal:"+wLocal);
			max = Math.max(max, Math.max(bLocal, wLocal));
		}
		return max;
	}
	
	public static int pipei(String p1,int n,char p2){
		int[] dp = new int[n];
		if(p1.charAt(0) == p2)
			dp[0] = 1;
		int max = dp[0];
		for(int i=1;i<n;i++){
			if(p1.charAt(i) == p2){
				dp[i] = dp[i-1]+1;
				max = Math.max(max, dp[i]);
			}
		}
		return max;
	}
	
	//打车走路到公司
	public static int dache(int n,int[] x,int[] y,int gx,int gy,int wktime,int txtime){
		//先走路到所有的打车点，再从打车点到公司
		int min = (Math.abs(gx)+Math.abs(gy))*wktime;
		for(int i=0;i<n;i++){
			int local = (Math.abs(x[i])+Math.abs(y[i]))*wktime;
			local += (Math.abs(gx-x[i])+Math.abs(gy-y[i]))*txtime;
			System.out.println("local:"+local);
			min = Math.min(min, local);
		}
		return min;
	}
	
	//小易记单词,s1是原始m个数据，s2是经过set处理的n个数据
	public static long jidanci(ArrayList<String> s1,ArrayList<String> s2){
		long total = 0;
		for(int i=0;i<s2.size();i++){
			if(s1.contains(s2.get(i))){
				int length = s2.get(i).length();
				total += length*length;
			}
		}
		return total;
	}
	
	//堆砖头
	//一种情况砖头总和不能被2整除
	//另一种情况是不能组合成sum/2的形式
	public static long duizhuantou(int[] num){
		int sum = 0;
		for(int i=0;i<num.length;i++)
			sum += num[i];
		if(sum%2==1)
			return -1;
		int[] dp = new int[sum/2+1];
		for(int i=0;i<num.length;i++){
			for(int j=sum/2;j>=num[i];j--){
				dp[j] = Math.max(dp[j], dp[j-num[i]]+num[i]);
			}
		}
		if(dp[sum/2] != sum/2)
			return -1;
		return dp[sum/2];
	}
	
	private static int[][] mutAndMod(int[][] a,int[][] b){
		int n1 = a.length;
		int n2 = a[0].length;
		int[][] ret = new int[n1][n2];
		for(int i=0;i<n1;i++){
			for(int j=0;j<n2;j++){
				int temp = 0;
				for(int k=0;k<n2;k++){
					temp += a[i][k] * b[k][j];
				}
				ret[i][j] = temp%100;
			}
		}
		return ret;
	}
	
	//88242 313 1991 4207 2483 1763 224 16 582 22943 28632 47682 378 90 88 43 117 19 8
	//199820/2=99910
	//99901
	public static void main(String[] args) {	
		int[] A = new int[]{3,3,7,3,1};
		System.out.println(cpu(A));
 	}
}
