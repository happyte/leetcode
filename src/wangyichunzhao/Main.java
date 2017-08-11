package wangyichunzhao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

//海量数据处理，并发(互斥锁与信号量,阻塞非阻塞,阻塞队列非阻塞IO,轮询3种机制,IO复用)

public class Main {
	//1.双核CPU同时处理任务,因为任务都是1024的倍数，因此内存不会爆炸
	public static int cpu(int[] num){
		//两个核的占用时间最好都是相同的，比如都是sum/2
		//dp[i][j]表示选前i个任务，容量为j的情况下可存放的时间。最大容量为sum/2
		int sum = 0;
		for(int i=0;i<num.length;i++)
			sum += num[i];
		int[][] dp = new int[num.length+1][sum/2+1];
		for(int i=1;i<=num.length;i++){
			for(int j=1;j<=sum/2;j++){
				//不选第i个任务，容量为j,dp[i-1][j]
				//选第i个任务，dp[i-1][j-num[i]]
				if(j>=num[i-1])
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-num[i-1]]+num[i-1]);
				else
					dp[i][j] = dp[i-1][j];
			}
		}
		int max = Math.max(dp[num.length][sum/2], sum-dp[num.length][sum/2]);
		return max<<10;
	}
	
	//2.打车走路到公司
	public static int dache(int n,int[] x,int[] y,int gx,int gy,int wktime,int txtime){
		//先走路到所有的打车点，再从打车点到公司
		int min = (Math.abs(gx)+Math.abs(gy))*wktime;
		for(int i=0;i<n;i++){
			int local = (Math.abs(x[i])+Math.abs(y[i]))*wktime;
			local += (Math.abs(gx-x[i])+Math.abs(gy-y[i]))*txtime;
			min = Math.min(min, local);
		}
		return min;
	}
	
	//3.男生女生排队，输入GGBBG
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
	
	//4.不重复输出数组中最后出现的那个元素
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

	//5.魔力手环,n为长度，k为变化的次数
	//算法复杂度过大
	public static String moli(int n,long k,int[] num){
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
		
	//5.魔力手环,内存超限
	public static String molishouhuan(int[] num,int n,int k){
		StringBuffer sb = new StringBuffer();
		//构造A矩阵，第一行就是为num中的数，其余都为0
		int[][] arr = new int[n][n];
		for(int i=0;i<n;i++)
			arr[0][i] = num[i];
		//构造转换矩阵
		int[][] mul = new int[n][n];
		for(int i=0;i<n;i++){
			//最后一行
			if(i == n-1){
				mul[i][i]=1;
				mul[0][i]=1;
			}
			else {
				mul[i][i]=1;
				mul[i+1][i]=1;
			}
		}
		for(int i=0;i<k-1;i++){
			arr = matrixMul(arr, mul, n);
		}
		for(int i=0;i<n;i++){
			if(i == n-1)
				sb.append(arr[0][i]);
			else
				sb.append(arr[0][i]+" ");
		}
		return sb.toString();
	}
		
	//两个矩阵的乘法
	private static int[][] matrixMul(int[][] a,int[][] b,int n){
		int[][] c = new int[n][n];
		//对a的每一行乘以b的每一列
		//a行
		for(int i=0;i<n;i++){
			//b列
			for(int j=0;j<n;j++){
				for(int k=0;k<n;k++){
					if(a[i][k]==0||b[k][j]==0)
						continue;
					c[i][j] += a[i][k]*b[k][j];
					c[i][j] = c[i][j]%100;
				}
			}
		}
		return c;
	}
	
	//6.分配工作，回溯
	//work包含了当前已经选中的工作，n是工程师的数量
	//1、一个人只能做一项工程，而不能分饰两角；
	//2、有几个工程师，每个工程师有一个工作即满足题意，不用6项工作全部都要有人做。
	public static void zhaogongzuo(ArrayList<Integer> work,String[] strs,int index,ArrayList<Integer> cases){
		if(index >= strs.length){
			int count = cases.get(0);
			cases.set(0, ++count);
			return;
		}
		//workId是第index工程师可以完成的工作
		String str = strs[index];
		for(int i=0;i<str.length();i++){
			int workId = str.charAt(i)-'0';
			//如果该工作没有被做，则该工程师可以完成该任务
			if(!work.contains(workId)){
				//保护现场
				work.add(workId);
				zhaogongzuo(work, strs, index+1, cases);
				work.remove(work.size()-1);
			}
		}
	}
	
	//7.集合，S = { p/q | w ≤ p ≤ x, y ≤ q ≤ z }
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
		
	//8.奇怪的表达式
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
	
	//9.涂格子,某一列中拥有相同颜色的最大的区域去涂画
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
	
	//10.小易记单词,s1是原始m个数据，s2是经过set处理的n个数据
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
	
	//11.堆砖头,二维数组内存超限
	//解决方式，用滚动数组形式解决，这道题技巧性太强了
	//dp[i][j]表示前i块砖头，高度差j(B-A),B的最大高度值
	//因为B-A可能是负数，因此整体向右偏移sum个元素
	//1⃣第i块砖头没有用，dp[i][j]=dp[i-1][j]
	//2⃣第i块砖头放在A区,dp[i][j]=dp[i-1][j-h[i]]
	//3⃣第i块砖头放在B区,dp[i][j]=dp[i-1][j+h[i]]+h[i]
	//最终所得结果dp[n][0]
	public static int duizhuantou(int n,int[] num){
		int sum = 0;
		for(int i=1;i<=n;i++)
			sum += num[i];
		int[][] dp = new int[n+1][2*sum+1];
		//初始化为-1,表示没有这种高度的堆法,用0块砖头，除了可以堆成高度差为0是没有堆法的
		Arrays.fill(dp[0], -1);
		//两塔的高度差为0，B塔的高度也为0
		dp[0][sum] = 0;
		for(int i=1;i<=n;i++){
			for(int j=0;j<=2*sum;j++){
				dp[i][j] = dp[i-1][j];
				//把砖头放在A区,与不放的比较
				if(j>=num[i]&&dp[i-1][j-num[i]]!=-1){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-num[i]]);
				}
				//把砖头放在B区，与之前的比较
				if(j+num[i]<=2*sum&&dp[i-1][j+num[i]]!=-1){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j+num[i]]+num[i]);
				}
			}
			for(int k=0;k<=n;k++){
				for(int m=0;m<=2*sum;m++)
					System.out.print(dp[k][m]+" ");
				System.out.println("");
			}
			System.out.println("");
		}
		if(dp[n][sum] == 0)
			return -1;
		return dp[n][sum];
	}
	
	//12.分饼干给学生
	//dp[i][j]为用前i个数字，分给学生余数为j的个数
	//假如第i个数字为确定的数字，newJ = (10*j+num[i])%n  dp[i][newJ] += dp[i-1][j]
	//假如第i个数字为X,那么newJ = (10*j+k)%n(k为0-9)   dp[i][newJ] += dp[i-1][j]
	//余数只可能为0...n-1
	public static long fenbingan(String s,int n){
		int len = s.length();
		long[][] dp = new long[len+1][n];
		dp[0][0] = 1;
		for(int i=1;i<=len;i++){
			for(int j=0;j<n;j++){
				//不确定X
				if(s.charAt(i-1) == 'X'){
					for(int k=0;k<=9;k++){
						int newJ = (10*j+k)%n;
						dp[i][newJ] += dp[i-1][j];
					}
				}
				//确定数字
				else {
					int newJ = (10*j+s.charAt(i-1)-'0')%n;
					dp[i][newJ] += dp[i-1][j];
				}
			}
			for(int k=0;k<=len;k++){
				for(int m=0;m<n;m++)
					System.out.print(dp[k][m]+" ");
				System.out.println("");
			}
			System.out.println("");
		}
		return dp[len][0];
	}
	
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[] num = new int[n];
		for(int i=0;i<n;i++)
			num[i] = scanner.nextInt();
		System.out.println(molishouhuan(num, n, k));
 	}
}
