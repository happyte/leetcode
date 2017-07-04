package searchA2DMatrix;

import java.awt.Event;

public class Solution {
	public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0)
        	return false;
        int i=0;
        for(; i<matrix.length-1; i++){
        	if(target == matrix[i][0] || target == matrix[i+1][0])
        		return true;
        	if(target > matrix[i][0] && target < matrix[i+1][0]){
        		for(int j=0; j<matrix[i].length; j++){
        			if(matrix[i][j] == target)
        				return true;
        		}
        	}
        }
        //最后一行单独判断
        for(int k=0; k<matrix[i].length; k++){
        	if(matrix[i][k] == target)
				return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		int[][] A = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		System.out.println(searchMatrix(A, 9));
	}
}
