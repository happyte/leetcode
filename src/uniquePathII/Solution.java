package uniquePathII;

public class Solution {
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 ||obstacleGrid[0].length ==0)
        	return 0;
        int[] res = new int[obstacleGrid[0].length];
        res[0] = 1;
        for(int i=0;i<obstacleGrid.length;i++)
        {
        	for(int j=1;j<obstacleGrid[0].length;j++){
        		if(obstacleGrid[i][j]==1){
        			res[j] = 0;
        		}
        		else{
        			res[j] += res[j-1];
        		}
        	}
        }
        return res[obstacleGrid[0].length-1];
    }
	
	public static void main(String[] args) {
		int[][] A = new int[][]{{0},
								{1}
								};
		System.out.println(uniquePathsWithObstacles(A));
	}
}
