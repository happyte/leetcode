package uniquePath;

//从起点走到终点的走法
public class Solution {
	//递归思路,依赖前m-1,n-1的结果,如果m和n大到100的话，早就超时了
	public static int uniquePaths(int m, int n) {
        return helper(1, 1, m, n);
    }
	
	private static int helper(int row,int col,int m,int n){
		if(row == m && col ==n)
			return 1;
		if(row > m || col > n)
			return 0;
		return helper(row+1, col, m, n)+helper(row, col+1, m, n);
	}
	
	//动态规划思路
	public static int uniquePaths2(int m, int n){
		int[] res = new int[n];
		res[0] = 1;
		for(int i=0;i<m;i++){
			for(int j=1;j<n;j++){
				res[j] += res[j-1];
			}
		}
		return res[n-1];
	}
	
	public static void main(String[] args) {
		System.out.println(uniquePaths2(2, 2));
	}
}
