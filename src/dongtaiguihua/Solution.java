package dongtaiguihua;

import java.util.ArrayList;
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
		int[] coins = new int[]{3,4,7};
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
		for(int i=0;i<coins.length;i++){
			for(int j=0;j<=n;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
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
	
	//求玩家走到第n步（n<=骰子最大点数且是方法的唯一入参）时，总共有多少种投骰子的方法。
	//大富翁游戏
	public static int dafuwen(int n){
		int[] dp = new int[n+1];
		dp[1] = 1;
		dp[2] = 2;
		for(int i=3;i<=n;i++){
			for(int j=i-1;j>=1;j--)
				dp[i] += dp[j];
		}
		return dp[n];
	}
	
	//对于小易当前所在的编号为K的 石板，小易单次只能往前跳K的一个约数(不含1和K)步，即跳到K+X(X为K的一个非1和本身的约数)的位置。
	//小易当前处在编号为N的石板，他想跳到编号恰好为M的石板去，小易想知道最少需要跳跃几次可以到达
	//N = 4，M = 24：4->6->8->12->18->24
	public static int buhao(int start,int end){
		if(start == end)
			return 0;
		//dp[i]代表能走到i所需要的最小步数，初始化从dp[5]到dp[24]都为无穷大表示无法走到
		int[] dp = new int[end+1];
		for(int i=start+1;i<=end;i++)
			dp[i] = 65536;
		//比如从dp[4]开始遍历，dp[4]可以到dp[6],那么dp[6]=1
		//dp[i+j]=min{dp[i]+1,dp[i+j]},i为当前石板的编号，j为i的因数
		for(int j=start;j<=end;j++){
			ArrayList<Integer> res = yinshu(j);
			for(int k=0;k<res.size();k++){
				int m = res.get(k);
				//会出现越界，end以后的无需判断，因为也用不到
				if(j+m>end)
					continue;
				dp[j+m] = Math.min(dp[j]+1, dp[j+m]);
			}
		}
		for(int i=start;i<=end;i++)
			System.out.print(dp[i]+" ");
		System.out.println("");
		if(dp[end] == 65536)
			return -1;
		return dp[end];
	}
	
	//求因数
	public static ArrayList<Integer> yinshu(int num){
		ArrayList<Integer> res = new ArrayList<>();
		for(int i=2;i<=Math.sqrt(num);i++){
			if(num%i==0){
				res.add(i);
				if(num/i != i)
					res.add(num/i);
			}
		}
		return res;
	}
	
	//两个字符串的最长公共子序列
	//dp[i][j]代表str1[0...i-1]与str2[0...j-1]的最长公共子序列
	public static int maxsubstring(String str1,String str2){
		int M = str1.length();
		int N = str2.length();
		int[][] dp = new int[M][N];
		//对第一列进行处理,如果str1[i]=str2[0],则dp[i][0]=1,此后的dp[i+1...M][0]=1
		int i=0;
		for(;i<M;i++){
			if(str1.charAt(i)==str2.charAt(0)){
				dp[i][0] = 1;
				break;
			}
		}
		for(int k=i+1;k<M;k++)
			dp[k][0] = 1;
		//对第一行进行处理，如果str1[0]=str2[j],则dp[0][j]=1,此后的dp[0][j+1..N]=1
		int j = 0;
		for(;j<N;j++){
			if(str1.charAt(0) == str2.charAt(j)){
				dp[0][j] = 1;
				break;
			}
		}
		for(int k=j+1;k<N;k++)
			dp[0][k] = 1;
		//dp[i][j]=max{dp[i-1][j],dp[i][j-1],dp[i-1][j-1]+1}
		//第一种情况 str1=A1BC2  str2=AB34C  dp[3][4]=ABC,dp[4][4]=dp[3][4]
		//第二种情况 str1=ABCD1  str2=ABCD2  dp[4][3]=ABCD,dp[4][4]=dp[4][3]
		//第三种情况 str1[i]=str2[j],dp[i][j]=dp[i-1][j-1]最多只能加1
		for(i=1;i<M;i++){
			for(j=1;j<N;j++){
				if(str1.charAt(i)==str2.charAt(j))
					dp[i][j] = Math.max(dp[i-1][j-1]+1, Math.max(dp[i-1][j], dp[i][j-1]));
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		for(int m=0;m<M;m++){
			for(int n=0;n<N;n++){
				System.out.print(dp[m][n]+" ");
			}
			System.out.println("");
		}
		return dp[M-1][N-1];
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
	
	//分糖问题
	public static int candy(int[] ratings) {
		int n = ratings.length;
		if(n == 0) return 0;
		if(n == 1) return 1;
		int res = 0;
		int[] cans = new int[n];
		Arrays.fill(cans, 1);
		//右边比左边得分高，分到的糖多
		for(int i=1;i<n;i++){
			if(ratings[i]>ratings[i-1])
				cans[i] = cans[i-1]+1;
		}
		//左边比右边大，分到的糖多,例如2,1
		for(int i = n-2;i>=0;i--){
			if(ratings[i]>ratings[i+1]&&cans[i]<=cans[i+1])
				cans[i] = cans[i+1]+1;
		}
		for(int i=0;i<n;i++){
			res += cans[i];
		}
		return res;
	}
	
	//解码方式
	//dp[i]代表s[0...i-1]的编码方式数量
	public static int numDecodings(String s) {
		if(s == null || s.length() == 0)
			return 0;
		//第一位为0的话，是不可能组合出任何结果的，返回0
		if(s.charAt(0) == '0')
			return 0;
		int n = s.length();
		int[] dp = new int[n+1];
		dp[0] = 1;
		dp[1] = 1;
		//如果这道题不考虑限制条件的话,dp[i]=dp[i-1]+dp[i-2]
		for(int i=2;i<=n;i++){
			//s[i-1]=0的话,dp[i]=dp[i-2]
			if(s.charAt(i-1) != '0')
				dp[i] += dp[i-1];
			//s[i-2,i-1]中的前一项不为0，且范围在1～26之间
			int temp = Integer.valueOf(s.substring(i-2, i));
			if(s.charAt(i-2) != '0' && (temp>=1 && temp <= 26))
				dp[i] += dp[i-2];
		}
		return dp[n];
	}
	
	//最小步数把word1转成word2,增删改的代价都是一样的
	public static int minDistance(String word1, String word2) {
		if(word1 == null || word2 == null)
			return 0;
		int M = word1.length();
		int N = word2.length();
		//dp[i][j]代表把word1[0...i-1]变成word2[0...j-1]的最小步数
		int[][] dp = new int[M+1][N+1];
		//空字符串变成空字符串
		dp[0][0] = 0;
		//第一列
		for(int i=0;i<=M;i++)
			dp[i][0] = i;
		//第一行
		for(int j=0;j<=N;j++)
			dp[0][j] = j;
		for(int i=1;i<=M;i++){
			for(int j=1;j<=N;j++){
				//如果word1[i]=word2[j]
				if(word1.charAt(i-1)==word2.charAt(j-1)){
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
				}
				else{
					dp[i][j] = Math.min(dp[i-1][j-1]+1, Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
				}
			}
		}
        return dp[M][N];
    }
	
	public static void main(String[] args) {
		System.out.println(combination(33));
	}
}
