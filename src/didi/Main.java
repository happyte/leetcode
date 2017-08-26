package didi;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	//数组最大长度
	public static int max(int length,int[] num){
		if(num == null||length == 0)
			return 0;  
		int local = num[0];
		int global = num[0];
		for(int i=1;i<length;i++){
			local = Math.max(num[i]+local, num[i]);
			global = Math.max(local, global);
		}
		return global;
	}
	
	//dp[i]表示前i步的最长子串,只是保存局部最优的状态
	public static int maxII(int length,int[] num){
		if(num == null||length == 0)
			return 0;
		int[] dp = new int[num.length+1];
		//第一步的最长子串肯定是本身
		dp[1] = num[0];
		int max = num[0];
		for(int i=2;i<=num.length;i++){
			dp[i] = Math.max(dp[i-1]+num[i-1], num[i-1]);
			max = Math.max(max, dp[i]);
			System.out.println("i:"+i+" "+"dp:"+dp[i]);
		}
		return max;
	}
	
	//判断n!最后有几个0
	public static int jiechen(int n){
		int count = 0;
		for(int i=5;i<=n;i++){
			int j = i;
			while(j%5==0){
				count++;
				j = j/5;
			}
		}
		return count;
	}
	
	//n转换成m进制
	public static String jinzhi(int n,int m){
		int temp = n;
		if (n<0) {
			temp = -temp;
		}
		StringBuffer sb = new StringBuffer();
		int count = 0;
		while(temp>0){
			//从最后一位开始
			int k = temp%m;
			System.out.println("k:"+k);
			if(k>=10){
				sb.append((char)('A'+(k-10)));
			}
			else {
				sb.append(k);
			}
			temp = temp/m;
		}
		if(n<0){
			sb.append("-");
			return sb.reverse().toString();
		}
		return sb.reverse().toString();
	}
	
	//dp[i][j]表示选用前i件商品达到j块钱
	public static long shangpin(int[] num,int target){
		long[][] dp = new long[num.length+1][target+1];
		//第一行除了第一个数，其余表示不选商品，达到j元，这是不可能的情况
		//第一列选中i件商品，达到0元,都有一种组合，就是什么都不选
		for(int i=0;i<=num.length;i++)
			dp[i][0] = 1;
		//dp[i][j],分为两种情况，选了第i件商品，那么，dp[i][j]=dp[i-1][j-num[i-1]]+dp[i-1][j]
		//不选第i件商品,dp[i][j]=dp[i-1][j]
		for(int i=1;i<=num.length;i++){
			for(int j=1;j<=target;j++){
				if(j>=num[i-1])
					dp[i][j] = dp[i-1][j-num[i-1]]+dp[i-1][j];
				else
					dp[i][j] = dp[i-1][j];
			}
		}
		for(int i=0;i<=num.length;i++){
			for(int j=0;j<=target;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println("");
		}
		return dp[num.length][target];
	}
	
	static class Point{
		int x;
		int y;
		public Point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	//行数、列数、数组值
	static int rows,cols;
	static int[][] array;
	//res的key是当前的体力值，value是存储的路径，key是从小到大排序的,key越大当前留下的体力值越多
	static TreeMap<Integer, ArrayList<Point>> res = new TreeMap<>();
	//dfs找到从(0,0)到(0,m-1)的唯一路径
	public static void dfs(int x,int y,int p,ArrayList<Point>list){
		//当前坐标超过行数和列数
		if(x<0||x>=rows||y<0||y>=cols) 
			return;
		//体力不足
		if(p<0)
			return;
		//当前点是不允许走过的
		if(array[x][y] == 0)
			return;
		//从(0,0)爬到了(0,m-1)
		if(x==0&&y==cols-1){
			Point point = new Point(x, y);
			list.add(point);
			ArrayList<Point> temp = new ArrayList<>(list);
			res.put(p, temp);
			list.remove(point);
			return;
		}
		//还没到达终点，则当前点已经做过，要设置成0，下次不能走过了
		array[x][y] = 0;
		Point point = new Point(x, y);
		list.add(point);
		//向四处走
		dfs(x, y-1, p-1, list);
		dfs(x, y+1, p-1, list);
		dfs(x+1, y, p, list);
		dfs(x-1, y, p-3, list);
		list.remove(point);
		array[x][y] = 1;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] strs = scanner.nextLine().split(" ");
		int length = strs.length;
		int[] num = new int[length];
		for(int i=0;i<length;i++)
			num[i] = Integer.valueOf(strs[i]);
		System.out.println(maxII(length, num));
//		Scanner scanner = new Scanner(System.in);
//		rows = scanner.nextInt();
//		cols = scanner.nextInt();
//		int p = scanner.nextInt();
//		array = new int[rows][cols];
//		for(int i=0;i<rows;i++){
//			for(int j=0;j<cols;j++)
//				array[i][j] = scanner.nextInt();
//		}
//		ArrayList<Point> list = new ArrayList<>();
//		dfs(0, 0, p, list);
//		if(res.size() == 0){
//			System.out.println("Can not escape!");
//			return;
//		}
//		ArrayList<Point> test = res.lastEntry().getValue();
//		for(int i=0;i<test.size();i++){
//			if(i!=test.size()-1)
//				System.out.print("["+test.get(i).x+","+test.get(i).y+"]"+",");
//			else
//				System.out.print("["+test.get(i).x+","+test.get(i).y+"]");
//		}
	}
}
