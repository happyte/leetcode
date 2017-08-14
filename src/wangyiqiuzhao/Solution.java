package wangyiqiuzhao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	
	//1.彩色的砖头
	//如果最多存在一对不同颜色的相邻砖块,那么这行砖块就很漂亮的
	//WXYZ输出应该为0
	public static int zhuantou(String str){
		Set<Character> set = new HashSet<>();
		for(int i=0;i<str.length();i++){
			set.add(str.charAt(i));
		}
		if(set.size() == 1)
			return 1;
		if(set.size()>2)
			return 0;
		return 2;
	}
	
	//2.等差数列
	//通过任意次交换，是否能变成等差数列
	public static String dengcha(int n,int[] num){
		Arrays.sort(num);
		boolean flag = true;
		for(int i=1;i<num.length-1;i++){
			if(num[i+1]-num[i]!=num[i]-num[i-1]){
				flag = false;
				break;
			}
		}
		if(flag)
			return "Possible";
		return "Impossible";
	}
	
	//3.交错01串
	//最长的01子串
	public static int onezero(boolean[] bools){
		//从数组中的每个元素开始找，后面一位是否取反
		int max = 0;
		for(int i=0;i<bools.length;i++){
			int count = 0;
			for(int j=i+1;j<bools.length;j++){
				if(bools[j]==!bools[j-1])
					count++;
				else
					break;
			}
			System.out.println("count:"+(count+1));
			max = Math.max(max, count+1);
		}
		return max;
	}
	
	//4.操作序列
	//这是道规律题
	public static String nixu(int n,long[] num){
		StringBuffer sb = new StringBuffer();
		//n为奇数的时候，前n/2+1个数字为奇数的递减，例如7,5,3,1,2,4,6
		//n为偶数的时候，前n/2个数字为偶数的递减，例如8,6,4,2,1,3,5,7
		for(int i=n;i>=1;i-=2){
			sb.append(num[i-1]+" ");
		}
		if(n%2==1){
			for(int i=2;i<=n-1;i+=2){
				if(i == n-1)
					sb.append(num[i-1]);
				else
					sb.append(num[i-1]+" ");
			}
		}
		else {
			for(int i=1;i<=n-1;i+=2){
				if(i == n-1)
					sb.append(num[i-1]);
				else
					sb.append(num[i-1]+" ");
			}
		}
		return sb.toString();
	}
	
	//5.独立的小易
	//小易想独自生活，每天要吃一个水果和付x元房租，如果他现在有f个水果和d元钱，商店的水果每个p元，问他能独自生活多少天
	//x元租金，f个水果，d元钱，水果店每个水果p元
	public static long shenghuo(long x,long f,long d,long p){
		//如果有水果的这段时间连租金都付不起
		if(d/x<=f)
			return d/x;
		long sum = f;
		sum += (d-f*x)/(p+x);
		return sum;
	}
	
	//6.堆棋子
	//输出n个整数,第i个表示棋盘上有一个格子至少有i个棋子所需要的操作数
	public static String qizi(int n,int[] x,int[] y){
		StringBuffer sb = new StringBuffer();
		//sb.append("0 ");
		//有i个棋子需要找到合适的位置,一个棋子就不需要操作了
		for(int i=1;i<=n;i++){
			//找出前i个棋子x和y的最大值
			//棋子的坐标为(j,k)
			long min = Long.MAX_VALUE;
			for(int j=0;j<n;j++){
				for(int k=0;k<n;k++){
					long sum = 0;
					PriorityQueue<Integer> pq = new PriorityQueue<>(i,new Comparator<Integer>() {
						@Override
						public int compare(Integer o1, Integer o2) {
							return o2-o1;
						}
					});
					for(int c=0;c<n;c++){
						int xrow = x[j];
						int ycol = y[k];
						int distance = Math.abs(xrow-x[c])+Math.abs(ycol-y[c]);
						sum += distance;
						pq.add(distance);
						//队列是由大到小排列的
						if(pq.size()>i){
							sum -= pq.poll();
						}
					}
					min = Math.min(min, sum);
					System.out.println("i:"+i+" min:"+min);
				}
			}
			if(i == n)
				sb.append(min);
			else
				sb.append(min+" ");
		}
		return sb.toString();
	}
	
	//7.疯狂队列
	//疯狂值为每队相邻排列学生身高差的绝对值总和，顺序排列的疯狂值是最小的，现在求出队列最大可能的疯狂值
		public static long fengkuangzhi(int n,long[] num){
			long[] arr = new long[n];
			Arrays.sort(num);
			//每次插入两个值
			int level = num.length/2;
			for(int i=0;i<level;i++){
				//奇数的插法和偶数是一样的，关键在于最中间的位置是最大数字还是最小数字,两边的插入方法还是小,大
				if(num.length%2==1){
					int mid = num.length/2;
					if(i%2==0){
						arr[mid-i-1] = num[i];
						arr[mid+i+1] = num[i+1];
					}
					else {
						arr[mid-i-1] = num[num.length-i-1];
						arr[mid+i+1] = num[num.length-i-2];
					}
				}
				//偶数个，例如先插1,2位置
				else {
					int mid = num.length/2;
					//i是2的倍数的话，插入是小，大
					if(i%2==0){
						arr[mid-i-1] = num[i];
						arr[mid+i] = num[num.length-i-1];
					}
					//i不是2的倍数的话，插入是大，小
					else {
						arr[mid-i-1] = num[num.length-i-1];
						arr[mid+i] = num[i];
					}
				}
			}
			if(num.length%2==1){
				arr[num.length/2] = num[n-1];
			}
			long sum = 0;
			for(int i=1;i<n;i++){
				sum += Math.abs(arr[i]-arr[i-1]);
			}
			for(int i=0;i<n;i++){
				System.out.print(arr[i]+" ");
			}
			System.out.println("");
			return sum;
		}
		
	//8.小易喜欢的数列
	//数列的长度是n,大小范围都在1到k之间
	//对于位置相邻的两个数A和B(A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
	//dp[i][j]表示表示长度为i，j表示数列最后一位是j(1<=j<=k)，的数列个数
	//dp[i][j] += dp[i-1][m] (1<=m<=k),先将i-1数列的长度即为sum,再记下对应的每个j的非法值，最终dp[i][j] = sum - invalid
	public static int shulie(int n,int k){
		int mod = 1000000007;
		int[][] dp = new int[n+1][k+1];
		//dp[1][k] = 1,长度为1，且以1...k结尾只有一种结果
		for(int i=0;i<=k;i++)
			dp[1][i] = 1;
		for(int i=2;i<=n;i++){
			int sum = 0;
			//先不考虑非法数列，都添加进来
			for(int j=1;j<=k;j++){
				sum = (sum+dp[i-1][j])%mod;
			}
			//对每个以j结尾的数列找到非法数列
			for(int j=1;j<=k;j++){
				int inValid = 0;
				int p = 2;
				while(p*j<=k){
					//如果最后一个为j，那么倒数第二位是j的倍数是非法的,无论前面的dp[i-1][p*j]是多少，再加入最后一位j永远是非法的
					inValid = (inValid+dp[i-1][p*j])%mod;
					p++;
				}
				dp[i][j] = (sum-inValid+mod)%mod;
			}
		}
		for(int i=0;i<=n;i++){
			for(int j=0;j<=k;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println("");
		}
		int sum = 0;
		for(int i=0;i<=k;i++){
			sum = (sum+dp[n][i])%mod;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		System.out.println(shulie(n, k));
	}
}
