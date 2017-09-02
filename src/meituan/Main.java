package meituan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	
	//leetcode的最大矩阵问题
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
	
	//经典动态规划找硬币问题
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
	
	//丢弃奇数位的数字
	public static int qishuwei(int n){
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0;i<=n;i++)
			list.add(i);
		while(list.size()!=1){
			for(int i=0;i<list.size();i+=1)
				list.remove(i);
		}
		return list.get(0);
	}
	
	//从右上角到左下角的对角线打印
	public static int[] arrayPrint(int[][] arr, int n) {
	     int[] res = new int[n*n];
	     ArrayList<Integer> list = new ArrayList<>();
	     //上三角
	     //i变化为3,2,1,0
	     for(int i=n-1;i>=0;i--){
	    	 //i为3时，j也为3
	    	int k = 0;
	    	for(int j=i;j<=n-1;j++){
	    		System.out.println("k:"+k+" j:"+j);
	    		list.add(arr[k++][j]);
	    	}
	     }
	     //下三角
	     for(int i=1;i<=n-1;i++){
	    	 int k = 0;
	    	 for(int j=i;j<=n-1;j++){
		    	 System.out.println("j:"+j+" k:"+k);
		    	 list.add(arr[j][k++]);
		     }
	     }
	     for(int i=0;i<list.size();i++){
	    	 System.out.print(list.get(i)+" ");
	    	 res[i] = list.get(i);
	     }
	     return res;
	 }
	 
	 //股票只经过一次买卖  7,1,5,3,6,4
	 public static int maxProfitI(int[] prices, int n) {
		 int local = 0;
		 int global = 0;
		 for(int i=0;i<n-1;i++){
			 local = Math.max(local+prices[i+1]-prices[i], 0);
			 global = Math.max(local, global);
		 }
		 return global;
	 }
	      
	 //股票允许经过两次买卖 
	 public static int maxProfitII(int[] prices, int n) {
		 int[] global = new int[3];
		 int[] local = new int[3];
		 for(int i=0;i<n-1;i++){
			 int diff = prices[i+1]-prices[i];
			 for(int j=2;j>=1;j--){
				 local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
				 global[j] = Math.max(local[j], global[j]);
			 }
		 }
		 return global[2];
	 }
	 
	 //可以进行无限多次交易,只要某两天是赚钱的，就进行交易
	 public static int maxProfitIII(int[] prices, int n) {
		 int res = 0;
		 for(int i=0;i<n-1;i++){
			 int diff = prices[i+1]-prices[i];
			 if(diff>0)
				 res += diff;
		 }
		 return res;
	 }
	 
	 //哈夫曼树编码问题
	 static class TreeNode{
		 int weight;
		 Character character;
		 TreeNode left;
		 TreeNode right;
		 public TreeNode(int weight,Character character){
			 this.weight = weight;
			 this.character = character;
		 }
		 public TreeNode(int weight){
			 this.weight = weight;
		 }
	 }
	 
	 //哈夫曼编码题
	public static int hufuman(String s){
		char[] strs = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
		for(int i=0;i<strs.length;i++){
			if(map.containsKey(strs[i])){
				map.put(strs[i], map.get(strs[i])+1);
			}
			else {
				map.put(strs[i], 1);
			}
		}
		//优先队列,按照字符数量从小到大排列
		PriorityQueue<TreeNode> pq = new PriorityQueue<>(map.size(), new Comparator<TreeNode>() {
			@Override
			public int compare(TreeNode o1, TreeNode o2) {
				return o1.weight-o2.weight;
			}
		});
		for(Map.Entry<Character, Integer> entry:map.entrySet()){
			pq.offer(new TreeNode(entry.getValue(), entry.getKey()));
		}
		System.out.println();
		while(pq.size()>1){
			System.out.println();
			TreeNode left = pq.poll();
			TreeNode right = pq.poll();
			System.out.println("left:"+left.character+" right:"+right.character);
			TreeNode father = new TreeNode(left.weight+right.weight);
			father.left = left;
			father.right = right;
			pq.offer(father);
		}
		return getLength(pq.poll(), 0);
	}
	
	private static int getLength(TreeNode root,int depth){
		if(root != null)
			System.out.println("ch:"+root.character+" depth:"+depth);
		if(root == null)
			return 0;
		return (root.character == null?0:root.weight)*depth+getLength(root.left, depth+1)+getLength(root.right, depth+1);
	}
	
	//[5386,5384,11054,6345,5816,11757],6  6373
	public static int longestDistance(int[] num,int n){
		int local = 0;
		int global = 0;
		for(int i=0;i<n-1;i++){
			local = Math.max(local+num[i+1]-num[i], 0);
			global = Math.max(local, global);
			System.out.println("local:"+local+" global:"+global);
		}
		return global;
	}
	
	//n为行数，m为列数,1为经理位置，2为商家位置，0可以经过的，－1不能经过的
	public static int countPath(int[][] map, int n, int m) {
		 int startX = 0;
	        int startY = 0;
	        int endX = 0;
	        int endY = 0;
	        for(int i=0;i<map.length;i++) {
	            for(int j=0;j<map[0].length;j++) {
	                if(map[i][j]==1) {
	                    startX = i;
	                    startY = j;
	                }
	                else if(map[i][j]==2) {
	                    endX = i;
	                    endY = j;
	                }
	            }
	        }
	        int dirX = startX>endX? -1:1;
	        int dirY = startY>endY? -1:1;
	        boolean[][] visited = new boolean[n][m];
	        return helper(dirX,dirY,n,m,startX,startY,map,visited);
    }
	
	//x为横坐标，y为纵坐标
	private static int helper(int dirX,int dirY,int n,int m,int x,int y,int[][] map,boolean[][] visited) {
        int count = 0;
        if(x>=0&&x<n&&y>=0&&y<m&&map[x][y]==2) {
            count++;
            return count;
        }
        if(x>=0&&x<n&&y>=0&&y<m&&!visited[x][y]&&map[x][y]!=-1) {
            visited[x][y] = true;
            count += helper(dirX,dirY,n,m,x+dirX,y,map,visited);
            count += helper(dirX,dirY,n,m,x,y+dirY,map,visited);
            visited[x][y] = false;
        }
        return count;
    }
	
	public static int countArea(int[] A, int n) {
		int maxArea = 0;
		int[] height = new int[n+1];
		for(int i=0;i<n;i++)
			height[i] = A[i];
		height[n] = 0;
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		//压入的是索引值
		while(i<=n){
			if(stack.isEmpty()||height[i]>height[stack.peek()]){
				stack.push(i++);
			}
			else {
				int t = stack.pop();
				maxArea = Math.max(maxArea, height[t]*(stack.isEmpty()?i:i-stack.peek()-1));
				System.out.println("maxArea:"+maxArea);
			}
		}
        return maxArea;
    }
	
	//s1,s2之间按照字典排序，长度在len1 到 len2之间
	public static int countDict(String s1,String s2,int len1,int len2){
		
		return 0;
	}
	
	//计算N年后员工的平均年龄 5 5 0.2 3
	public static int countAge(int W,double Y,double x,int N){
		for(int i=0;i<N;i++){
			Y = ((Y+1)*(W-W*x) + 21*(W*x))/W;
		}
		return (int)Math.ceil(Y);
	}
	
	public static void main(String[] args) {
		int[][] map = new int[][]{{0,1,0},{2,0,0}};
		System.out.println(countPath(map, 2, 3));
	}
	
}
