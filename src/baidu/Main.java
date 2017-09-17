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
	
	/**
	 * C市现在要转移一批罪犯到D市，C市有n名罪犯，按照入狱时间有顺序，另外每个罪犯有一个罪行值，值越大罪越重。
	 * 现在为了方便管理，市长决定转移入狱时间连续的c名犯人，同时要求转移犯人的罪行值之和不超过t，问有多少种选择的方式？
	 * n，t，c(1≤n≤2e5,0≤t≤1e9,1≤c≤n)，第二行按入狱时间给出每个犯人的罪行值ai(0≤ai≤1e9)
	 * 3 100 2
	 * 1 2 3
	 * 2
	 */
	public static int yisong(int n,long t,int c,long[] a){
		int count = 0;
		for(int i=0;i<=n-c;i++){
			long sum = 0;
			for(int j=i;j<i+c;j++){
				sum += a[j];
			}
			System.out.println("sum:"+sum);
			if(sum<=t)
				count++;
		}
		return count;
	}
	
	/**
	 * 度度熊有一张网格纸，但是纸上有一些点过的点，每个点都在网格点上，若把网格看成一个坐标轴平行于网格线的坐标系的话，每个点可以用一对整数x，y来表示。
	 * 度度熊必须沿着网格线画一个正方形，使所有点在正方形的内部或者边界。然后把这个正方形剪下来。问剪掉正方形的最小面积是多少。 
	 * 第一行一个数n(2≤n≤1000)表示点数，接下来每行一对整数xi,yi(－1e9<=xi,yi<=1e9)表示网格上的点
	 * 2
	 * 0 0
	 * 0 3
	 * 9
	 */
	public static long wangge(int n,long[] x,long[] y){
		long mianji = 0;
		//找出x,y坐标的边界
		long minX = Long.MAX_VALUE;
		long minY = Long.MAX_VALUE;
		long maxX = 0;
		long maxY = 0;
		for(int i=0;i<n;i++){
			minX = Math.min(minX, x[i]);
			minY = Math.min(minY, y[i]);
			maxX = Math.max(maxX, x[i]);
			maxY = Math.max(maxY, y[i]);
		}
		long bian = Math.max(maxX-minX, maxY-minY);
		System.out.println("minX:"+minX+" maxX:"+maxX+" minY:"+minY+" maxY:"+maxY);
		return bian*bian;
	}
	
	
	/**
	 * ss请cc来家里钓鱼，鱼塘可划分为n＊m的格子，每个格子有不同的概率钓上鱼，cc一直在坐标(x,y)的格子钓鱼，
	 * 而ss每分钟随机钓一个格子。问t分钟后他们谁至少钓到一条鱼的概率大？为多少？
	 * 第一行五个整数n,m,x,y,t(1≤n,m,t≤1000,1≤x≤n,1≤y≤m);
	 * 接下来为一个n＊m的矩阵，每行m个一位小数，共n行，第i行第j个数代表坐标为(i,j)的格子钓到鱼的概率为p(0≤p≤1)
	 * 输出两行。第一行为概率大的人的名字(cc/ss/equal),第二行为这个概率(保留2位小数)
	 * 2 2 1 1 1
	 * 0.2 0.1
	 * 0.1 0.4
	 * equal
	 * 0.2
	 */
	public static void diaoyu(int n,int m,int x,int y,int t,double[][] p){
		double sumP = 0;
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++)
				sumP += p[i][j];
		}
		//System.out.println("sumP:"+sumP);
		double ssP = Double.valueOf(String.format("%.2f", sumP/(n*m)));
		//System.out.println("ssP:"+ssP);
		double ccP = p[x-1][y-1];
		//System.out.println("ccP:"+ccP);
		if(ssP == ccP){
			System.out.println("equal");
			System.out.println(String.format("%.2f", 1-Math.pow(1-ccP, t)));
		}
		else if(ssP < ccP){
			System.out.println("cc");
			System.out.println(String.format("%.2f", 1-Math.pow(1-ccP, t)));
		}
		else {
			System.out.println("ss");
			System.out.println(String.format("%.2f", 1-Math.pow(1-ssP, t)));
		}
	}
	
	/**
	 * 现在有两个好友A和B，住在一片长有蘑菇的由n＊m个方格组成的草地，A在(1,1),B在(n,m)。现在A想要拜访B，
	 * 由于她只想去B的家，所以每次她只会走(i,j+1)或(i+1,j)这样的路线，在草地上有k个蘑菇种在格子里(多个蘑菇可能在同一方格),
	 * 问：A如果每一步随机选择的话(若她在边界上，则只有一种选择)，那么她不碰到蘑菇走到B的家的概率是多少？
	 * 第一行N，M，K(1 ≤ N,M ≤ 20, k ≤ 100),N,M为草地大小，接下来K行，每行两个整数x，y，代表(x,y)处有一个蘑菇
	 * 输出一行，代表所求概率(保留到2位小数)
	 * 2 2 1
	 * 2 1
	 * 0.50
	 */
	public static void zoumogu(int n,int m,int[][] mogu){
		//每次只能往右和往下走
		boolean[][] visited = new boolean[n+1][m+1];
		int total = helperI(1, 1, n, m);
		System.out.println("total:"+total);
		int count = helperII(1, 1, n, m, mogu);
		System.out.println("count:"+count);
		System.out.println(String.format("%.2f", (float)count/total));
	}
	
	//不考虑蘑菇的位置，一共有多少种走法
	public static int helperI(int startX, int startY,int endX,int endY){
		int[][] dp = new int[endX+1][endY+1];
		for(int i=startX;i<=endX;i++)
			dp[i][startY] = 1;
		for(int j=startY;j<=endY;j++)
			dp[startX][j] = 1;
		for(int i=startX+1;i<=endX;i++){
			for(int j=startY+1;j<=endY;j++)
				dp[i][j] = dp[i-1][j]+dp[i][j-1];
		}
		for(int i=0;i<=endX;i++){
			for(int j=0;j<=endY;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
		}
		return dp[endX][endY];
	}
	//0表示没有蘑菇，1表示有蘑菇
	public static int helperII(int startX, int startY,int endX,int endY,int[][] mogu){
		int[][] dp = new int[endX+1][endY+1];
		for(int i=startX;i<=endX;i++){
			if(mogu[i][startY]!=1) 
				dp[i][startY] = 1;
		}
		for(int j=startY;j<=endY;j++){
			if(dp[startX][j]!=1)
				dp[startX][j] = 1;
		}
		for(int i=startX+1;i<=endX;i++){
			for(int j=startY+1;j<=endY;j++){
				if(mogu[i][j] == 1)
					dp[i][j] = 0;
				else
					dp[i][j] = dp[i-1][j]+dp[i][j-1];
			}
		}
		for(int i=0;i<=endX;i++){
			for(int j=0;j<=endY;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
		}
		return dp[endX][endY];
	}
	
	public static void main(String[] args) {
		//走蘑菇
//		Scanner scanner = new Scanner(System.in);
//		while(scanner.hasNext()){
//			int n = scanner.nextInt();
//			int m = scanner.nextInt();
//			int k = scanner.nextInt();
//			int[][] mogu = new int[n+1][m+1];
//			for(int i=0;i<k;i++){
//				mogu[scanner.nextInt()][scanner.nextInt()] = 1;
//			}
//			zoumogu(n, m, mogu);
//		}
		//钓鱼
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int t = scanner.nextInt();
			double[][] p = new double[n][m];
			for(int i=0;i<n;i++){
				for(int j=0;j<m;j++)
					p[i][j] = scanner.nextDouble();
			}
			diaoyu(n, m, x, y, t, p);
		}
		//网格
//		Scanner scanner = new Scanner(System.in);
//		while(scanner.hasNext()){
//			int n = scanner.nextInt();
//			long[] x = new long[n];
//			long[] y = new long[n];
//			for(int i=0;i<n;i++){
//				x[i] = scanner.nextLong();
//				y[i] = scanner.nextLong();
//			}
//			System.out.println(wangge(n, x, y));
//		}
		//移送罪犯
//		Scanner scanner = new Scanner(System.in);
//		while(scanner.hasNext()){
//			int n = scanner.nextInt();
//			long[] a = new long[n];
//			long t = scanner.nextLong();
//			int c = scanner.nextInt();
//			for(int i=0;i<n;i++)
//				a[i] = scanner.nextLong();
//			System.out.println(yisong(n, t, c, a));
//		}
	}
}
