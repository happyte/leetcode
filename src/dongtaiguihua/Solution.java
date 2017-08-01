package dongtaiguihua;

import java.util.Arrays;

//动态规划专项训练
public class Solution {
	//一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法
	//dp公式推导 dp[i]=dp[i-1]+dp[i-2],dp[1]=1,dp[2]=2
	public static int JumpFloor(int target) {
		if(target == 1)
			return 1;
		if(target == 2)
			return 2;
		int[] dp = new int[target+1];
		dp[1]=1;
		dp[2]=2;
		for(int i=3;i<=target;i++){
			dp[i] = dp[i-1]+dp[i-2];
		}
        return dp[target];
    }
	
	//一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法
	//dp公式推导 dp[i]=dp[i-1]+dp[i-2]+....+dp[1]
	public static int JumpFloorII(int target) {
		if(target == 1)
			return 1;
		if(target == 2)
			return 2;
		int[] dp = new int[target+1];
		dp[1]=1;
		dp[2]=2;
		for(int i=3;i<=target;i++){
			dp[i]=1;
			for(int j=i-1;j>=1;j--){
				dp[i]+=dp[j];
			}
		}
        return dp[target];
    }
	
	//我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
	//dp推导公式  dp[i]=dp[i-1]+dp[i-2]
	public static int RectCover(int target) {
		if(target <= 2 )
			return target;
		int[] dp = new int[target+1];
		dp[0]=0;
		dp[1]=1;
		dp[2]=2;
		for(int i=3;i<=target;i++){
			dp[i] = dp[i-1]+dp[i-2];
		}
        return dp[target];
    }
	
	//动态规划经典题，找硬币,用{1,5,10,20,50}能有多少种解决方案
	//dp[i][j]代表用0...i范围内的硬币种类能凑到j元钱的方案总数
	//dp[i][j]=dp[i][j]+dp[i-1][j-k*coins[i]]
	//第一列dp[i][0]=1, 代表不花钱也是一种方案
	private static int combination(int n){
		int[] coins = new int[]{1,5,10,20,50};
		int[][] dp = new int[coins.length][n+1];
		//对第一列进行填充
		for(int i=0;i<coins.length;i++)
			dp[i][0] = 1;
		//对第一行进行填充
		for(int j=1;j<=n;j++){
			if(j%coins[0]==0)
				dp[0][j]=1;
		}
		for(int i=1;i<coins.length;i++){
			for(int j=1;j<=n;j++){
				int m = j/coins[i];
				for(int k=0;k<=m;k++){
					dp[i][j] = dp[i][j]+dp[i-1][j-k*coins[i]];
				}
			}
		}
		return dp[coins.length-1][n];
	}
	
	//矩阵的走法，每次只能向右或者向下走，达到最右下脚的最小距离
	//第一行和第一列就是第一行和第一列的累加和
	//其余格子 dp[i][j]=m[i][j]+min{dp[i-1][j],dp[i][j-1]}
	public static int minzoufa(int matrix[][]){
		int rows = matrix.length;
		int cols = matrix[0].length;
		int[][] dp = new int[rows][cols];
		dp[0][0] =  matrix[0][0];
		//第一列
		for(int i=1;i<rows;i++){
			for(int k=i;k>=0;k--)
				dp[i][0] += matrix[k][0];
		}
		//第一行
		for(int j=1;j<cols;j++){
			for(int k=j;k>=0;k--)
				dp[0][j] += matrix[0][k];
		}
		for(int i=1;i<rows;i++){
			for(int j=1;j<cols;j++){
				dp[i][j] = matrix[i][j]+Math.min(dp[i-1][j], dp[i][j-1]);
			}
		}
		return dp[rows-1][cols-1];
	}
	
	//求arr的最长递增子序列，例如arr=[2,1,5,3,6,4,8,9,7]
	//dp[i]=max{dp[j]+1,0=<j<i,arr[j]<arr[i]}
	public static int dizeng(int[] arr){
		int l = arr.length;
		int[] dp = new int[l];
		//无论如何，最长递增最小长度肯定为1，因为就是当前项本身
		Arrays.fill(dp, 1);
		for(int i=1;i<l;i++){
			for(int j=0;j<i;j++){
				if(arr[j]<arr[i]){
					dp[i] = Math.max(dp[j]+1, 1);
				}
			}
		}
		for(int i=0;i<l;i++)
			System.out.print(dp[i]+" ");
		return dp[l-1];
	}
	
	public static void main(String[] args) {
//		int[][] matrix = new int[][]{{1,3,5,9},{8,1,3,4},{5,0,6,1},{8,8,4,0}};
//		System.out.println(minzoufa(matrix));
		int[] arr = new int[]{2,1,5,3,6,4,8,9,7};
		dizeng(arr);
	}
}
