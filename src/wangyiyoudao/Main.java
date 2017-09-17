package wangyiyoudao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
	//翻盘游戏
	public static String fanpan(int[] num,int n,int k){ 
		StringBuffer sb = new StringBuffer();
		for(int j=0;j<k;j++){
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=n-1;i>=0;i--){
				list.add(num[i+n]);
				list.add(num[i]);
			}
			Collections.reverse(list);
			for(int i=0;i<list.size();i++)
				num[i] = list.get(i);
		}
		for(int i=0;i<num.length;i++){
			if(i == num.length-1)
				sb.append(num[i]);
			else
				sb.append(num[i]+" ");
		}
		return sb.toString();
	}
	
	//小易打怪
	public static int daguai(int[] num,int n,int ori){
		int sum = ori;
		for(int i=0;i<n;i++){
			if(sum>=num[i])
				sum += num[i];
			else
				sum += gongyue(sum, num[i]);
		}
		return sum;
	}
	
	//求两个数的最大公约数
	public static int gongyue(int a,int b){
		if(b == 0)
			return a;
		return gongyue(b, a%b);
	}
	
	//炮台给敌人造成的最大伤害
	public static String paotai(int[] x,int[] y,int n,int max,int emyX,int emyY){
		int sum = 0;
		for(int i=0;i<n;i++){
			double dis = Math.sqrt(Math.abs(x[i]-emyX)*Math.abs(x[i]-emyX)+Math.abs(y[i]-emyY)*Math.abs(y[i]-emyY));
			System.out.println("dis:"+dis);
			if(dis<=max)
				sum ++;
		}
		return sum+"x";
	}
	
	//最多可以扫描的爆炸蘑菇
	//3*3扫描镜只能用两次
	//N行,M列
	public static int mogu(int N,int M,int K,int[][] mushroom){
		//在外面再构建一层
		int[][] graph = new int[N+2][M+2];
		//有k个蘑菇,填到矩阵中去，1≤x≤N,1≤y≤M)，最外层的padding是没有蘑菇的
		for(int i=0;i<K;i++)
			graph[mushroom[i][0]][mushroom[i][1]]++;
		int max = 0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=M;j++){
				//开始扫描第一个九宫格,有蘑菇的话则用一个二位数组保存去掉的蘑菇数
				int[][] matrix = new int[N+2][M+2];
				int max1 = 0;
				for(int dx=-1;dx<2;dx++){
					for(int dy=-1;dy<2;dy++){
						if(graph[i+dx][j+dy]>0){
							max1++;
							matrix[i+dx][j+dy]--;
						}
					}
				}
				for(int k=1;k<=N;k++){
					for(int l=1;l<=M;l++){
						int max2 = 0;
						for(int dx=-1;dx<2;dx++){
							for(int dy=-1;dy<2;dy++){
								if(graph[k+dx][l+dy]+matrix[k+dx][l+dy]>0)
									max2++;
							}
						}
						if(max1+max2>max)
							max = max1+max2;
						if(max == 18)
							return 18;
					}
				}
			}
		}
		return max;
	}
	
	/**
	 * 2.幸运的袋子，从一个袋子中可以拿出一些数，使得剩下的数的累加大于累乘
	 */
	public static int dfs(int[] num,int index,long sum,long multi){
		int count = 0;
		for(int i=index;i<num.length;i++){
			sum += num[i];
			multi *= num[i];
			//考虑前0...i-1的累加和累乘
			if(sum>multi)
				count = count+1+dfs(num, i+1, sum, multi);
			else if (num[i] == 1)
				count = count+dfs(num, i+1, sum, multi);
			else
				break;
			sum -= num[i];
			multi /= num[i];
			//去除重复
			while(i<num.length-1&&num[i]==num[i+1])
				i++;
		}
		return count;
	}
	
	/**
		3.不要二
		W*H的网格最多能放几块蛋糕
		网格行编号0-H-1,列编号0-W-1
		H是行，W是列
	**/
	public static int dangao(int W,int H){
		System.out.println("W:"+W+" H:"+H);
		//求偶数行可以出现的4*4的方块数,剩余1列的话只有剩余的一列,剩余2列或3列的话都有2列
		int evenCount = H/4*2+(H%4<2? H%4 : 2);
		System.out.println("evenCount:"+evenCount);
		//求奇数行可以出现的4*4的方块数
		int oddCount = (H-2)/4*2+((H-2)%4<2? (H-2)%4 : 2);
		System.out.println("oddCount:"+oddCount);
		int res = W/4*2*(evenCount+oddCount);
		if(W%4>0)
			res += evenCount;
		if(W%4>1)
			res += evenCount;
		if(W%4>2)
			res += oddCount;
		return res;
	}
	
	/**
	 * 4.解救小易
	 */
	public static int xianjin(int[] x,int[] y,int n){
		int min = 65536;
		for(int i=0;i<n;i++){
			int dis = Math.abs(x[i]-1)+Math.abs(y[i]-1);
			min = Math.min(min, dis);
		}
		return min;
	}
	
	/**
	 * 5.统计回文串
	 * B字符串插入A中，问有多少种插入方法使得插入后是个回文串
	 */
	public static int huiwen(String s1,String s2){
		int count = 0;
		int n = s1.length();
		for(int i=0;i<=n;i++){
			String newStr = new String(s1.substring(0, i)+s2+s1.substring(i, n));
			if (isHuiwen(newStr)) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean isHuiwen(String s){
		for(int i=0;i<s.length()/2;i++){
			if(s.charAt(i) != s.charAt(s.length()-i-1))
				return false;
		}
		return true;
	}
	
	/**
	 * 7.两种排序方法
	 */
	//输入的字符串是否为两种排序中的一种
	public static String paixu(String[] strs){
		String str= "none";
		if(isDictonary(strs)&&isLength(strs))
			return "both";
		else if (isDictonary(strs))
			return "lexicographically";
		else if (isLength(strs))
			return "lengths";
		return "none";
	}
	
	public static boolean isDictonary(String[] strs){
		String[] strC = strs.clone();
		//本身这个API就是按照字典排序
		Arrays.sort(strC);
		for(int i=0;i<strs.length;i++){
			if(strC[i] != strs[i])
				return false;
		}
		return true;
	}
	
	public static boolean isLength(String[] strs){
		for(int i=1;i<strs.length;i++){
			if(strs[i].length()<strs[i-1].length())
				return false;
		}
		return true;
	}
	
	/**
	 * 8.小易喜欢的单词
	 */
	public static String danci(String s){
		Map<Character, Integer> map = new HashMap<>();
		if(s.charAt(0)<'A'||s.charAt(0)>'Z')
			return "Dislikes";
		map.put(s.charAt(0), 1);
		//如果单词有小写的单词或者前后两个连续，则直接返回DisLike
		for(int i=1;i<s.length();i++){
			//前后不相同，或不是大写字母
			if(s.charAt(i) == s.charAt(i-1)||s.charAt(i)<'A'||s.charAt(i)>'Z'){
				return "Dislikes";
			}
			else {
				if(map.containsKey(s.charAt(i))){
					int count = map.get(s.charAt(i));
					map.put(s.charAt(i), ++count);
				}
				else {
					map.put(s.charAt(i), 1);
				}
			}
		}
		String sb = new String();
		for(int i=0;i<s.length();i++){
			//如果大于一个字符
			if(map.get(s.charAt(i))>1){
				sb+=s.charAt(i);
			}
		}
		int j = 0;
		for(int i=1;i<sb.length();i++){
			if(sb.charAt(i)==sb.charAt(0)){
				j = i;
				break;
			}
		}
		for(int i=0;i<=j;i++){
			if(i+j>=sb.length()||sb.charAt(i)!=sb.charAt(i+j))
				return "Likes";
		}
		return "Dislikes";
	}
	
	/**
	 * 9.Fibonacci数列
	 */
	public static int feibonaqi(int n){
		int a = 1;
		int b = 1;
		//b大，a小
		while(!(n>=a&&n<=b)){
			int temp = b;
			b = b+a;
			a = temp;
		}
		int min = Math.min(n-a, b-n);
		System.out.println("a:"+a);
		System.out.println("b:"+b);
		return min;
	}
	
	/**
	 * 10.数字游戏
	 * 找出最小不能由n个数选取求和组成的数
	 */
	public static int mincost(int[] num){
		//sum表示当前能够组合出的最大数
		//如果num[i]-sum>1,表示sum+1这个数字肯定组合不出来
		Arrays.sort(num);
		if(num[0] != 1)
			return 1;
		int sum = num[0];
		for(int i=1;i<num.length;i++){
			if(num[i]-sum>1)
				break;
			else
				sum += num[i];
		}
		return sum+1;
	}
	
	//饥饿的小易
	//1,000,000,007(贝壳总在能被1,000,000,007整除的位置)
	//关键在于不知道移动是4 * x + 3还是8 * x + 7
	public static int jie(int x){
		int mod = 1000000007;
		
		return 1;
	}
	
	/**
	 * 每个输入包含 1 个测试用例。每个测试用例的第 i 行，表示完成第 i 件料理需要哪些材料，
	 * 各个材料用空格隔开，输入只包含大写英文字母和空格，输入文件不超过 50 行，每一行不超过 50 个字符。
	 * BUTTER FLOUR
	   HONEY FLOUR EGG
	   4
	 */
	
	/**
	 * 分苹果，每次只能从一个奶牛上拿2个苹果给另外一头奶牛
	 */
	public static int fenpingguo(int n,int[] num){
		int sum = 0;
		for(int i=0;i<n;i++)
			sum += num[i];
		//如果苹果不能够被均分
		if(sum%n != 0)
			return -1;
		Arrays.sort(num);
		int count = 0;
		int avg = sum/n;
		for(int i=0;i<n;i++){
			if(Math.abs(avg-num[i])%2==1){
				return -1;
			}
			else{
				count += Math.abs(avg-num[i])/2;
			}
		}
		return count/2;
	}
	
	public static long naijiudu(long h){
		long x = (long) Math.sqrt(h);
		for(long i=x;i>=0;i--){
			if(i*(1+i)<=h)
				return i;
		}
		return 0;
	}
	
	/**
	 * 藏宝图，字符串1中是否包含字符串2，不要求连续
	 */
	//求str1,str2的最长公共子串，不要求连续
	public static String isContain(String str1,String str2){  
		int M = str1.length();
		int N = str2.length();
		//dp[i][j]代表str1[0...i]与str2[0...j]的最长公共子串
		int[][] dp = new int[M][N];
		//若dp[i][0]=1,则i以后的都为1
		int i = 0;
		for(;i<M;i++){
			if(str1.charAt(i)==str2.charAt(0)){
				dp[i][0] = 1;
				break;
			}
		}
		for(int k=i+1;k<M;k++)
			dp[k][0] = 1;
		//若dp[0][j]=1,则j以后的都为1
		int j = 0;
		for(;j<N;j++){
			if(str1.charAt(0)==str2.charAt(j)){
				dp[0][j] = 1;
				break;
 			}
		}
		for(int k=j+1;k<N;k++)
			dp[0][k] = 1;
		for(i=1;i<M;i++){
			for(j=1;j<N;j++){
				if(str1.charAt(i)==str2.charAt(j))
					dp[i][j] = Math.max(dp[i-1][j-1]+1, Math.max(dp[i-1][j], dp[i][j-1]));
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		System.out.println(dp[M-1][N-1]);
		if(dp[M-1][N-1] == str2.length())
			return "Yes";
        return "No";
    }  
	
	/**
	 * 有 n 个学生站成一排，每个学生有一个能力值，牛牛想从这 n 个学生中按照顺序选取 k 名学生，
	 * 要求相邻两个学生的位置编号的差不超过 d，使得这 k 个学生的能力值的乘积最大，你能返回最大的乘积吗？
	 * 3
	   7 4 7
	   2 50
	 */
	public static long hechangtuan(int[] num,int k,int d){
		int n = num.length-1;
		//dp0[i][j]代表选中第i个数，一共选了j个数字的最大成绩
		//dp1[i][j]代表选中第i个数，一共选了j个数字的最小成绩
		//num下标从1开始
		long[][] dp0 = new long[n+1][k+1];
		long[][] dp1 = new long[n+1][k+1];
		long max = 0;
		for(int i=1;i<=n;i++){
			dp0[i][1] = num[i];
			dp1[i][1] = num[i];
			for(int j=2;j<=k;j++){
				for(int m=Math.max(1, i-d);m<i;m++){
					dp0[i][j] = Math.max(dp0[i][j], Math.max(num[i]*dp0[m][j-1], num[i]*dp1[m][j-1]));
					dp1[i][j] = Math.min(dp1[i][j], Math.min(num[i]*dp1[m][j-1], num[i]*dp0[m][j-1]));
				}
			}
			max = Math.max(max, dp0[i][k]);
			System.out.println("max:"+max);
		}
		return max;
	}
	
	//oxoxoxox
	//ooxxoo
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int[] num = new int[n+1];
			for(int i=1;i<=n;i++)
				num[i] = scanner.nextInt();
			int k = scanner.nextInt();
			int d = scanner.nextInt();
			System.out.println(hechangtuan(num, k, d));
		}
	}
}
