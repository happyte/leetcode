package setMatrixZeros;

public class Solution {
	public static void setZeroes(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        	return;
        boolean colFlag = false;  //记录第一列是否有0
        boolean rowFlag = false; //记录第一行是否有0
        for(int i=0;i<matrix.length;i++){
        	if(matrix[i][0] == 0){
        		colFlag = true;
        		break;
        	}
        }
        for(int j=0;j<matrix[0].length;j++){
        	if(matrix[0][j] == 0){
        		rowFlag = true;
        		break;
        	}
        }
        //从i-1,j-1开始遍历数组，发现matrix[i][j]=0,把matrix[i][0]和matrix[0][j]赋为0，维护第一行和第一列
        for(int i=1;i<matrix.length;i++){
        	for(int j=1;j<matrix[i].length;j++){
        		if(matrix[i][j] == 0){
        			matrix[i][0] = 0;
        			matrix[0][j] = 0;
        		}
        	}
        }
        //根据维护的第一行和第一列，从i-1，j-1开始对整行整列赋0
        for(int i=1;i<matrix.length;i++){
        	for(int j=1;j<matrix[i].length;j++){
        		if(matrix[i][0] == 0 || matrix[0][j] == 0){
        			matrix[i][j] = 0;
        		}
        	}
        }
        //处理第一行
        if(rowFlag){
        	for(int j=0;j<matrix[0].length;j++)
        		matrix[0][j]=0;
        }
        if(colFlag){
        	for(int i=0;i<matrix.length;i++)
        		matrix[i][0] = 0;
        }
    }
	
	public static void main(String[] args) {
		int[][] A = new int[][]{{1,3,5,7},{2,0,4,8},{13,15,17,0}};
		setZeroes(A);
		System.out.println(A[0][0]+","+A[0][1]+","+A[0][2]+","+A[0][3]);
		System.out.println(A[1][0]+","+A[1][1]+","+A[1][2]+","+A[1][3]);
		System.out.println(A[2][0]+","+A[2][1]+","+A[2][2]+","+A[2][3]);
	}
}
