package minimumPathSum;

public class Solution {
	//与unique的题目是一个思路
	public static int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
        	return 0;
        int[] res = new int[grid[0].length];
        res[0] = grid[0][0];
        for(int i=1;i<grid[0].length;i++){  
            res[i] = res[i-1]+grid[0][i];  
        } 
        for(int i=1;i<grid.length;i++){
        	for(int j=0;j<grid[i].length;j++){
        		//第一行的权重特殊处理
        		if(j == 0)
        			res[j] += grid[i][0];
        		else
        			res[j] = Math.min(res[j], res[j-1])+grid[i][j];
        	}
        }
        return res[grid[0].length-1];
    }
	
	public static void main(String[] args) {
		int[][] A = new int[][]{{1,3,1},
							    {1,5,1},
							    {4,2,1}};
		System.out.println(minPathSum(A));
	}
}
