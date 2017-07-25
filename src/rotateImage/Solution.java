package rotateImage;

import java.util.ArrayList;

public class Solution {
	public static void rotate(int[][] matrix) {
        ArrayList<ArrayList<Integer>> origin = new ArrayList<>();
        for(int i=0;i<matrix.length;i++){
        	ArrayList<Integer> item = new ArrayList<>();
        	for(int j=0;j<matrix[0].length;j++){
        		item.add(matrix[i][j]);
        	}
        	origin.add(item);
        }
        for(int i=matrix[0].length-1;i>=0;i--){
        	for(int j=0;j<matrix.length;j++){
        		matrix[i][j] = origin.get(matrix[0].length-1-j).get(i);
        	}
        }
    }
	
	public static void rotateII(int[][] matrix){
		//需要旋转多少层
		int layerNum = matrix.length/2;
		for(int layer=0;layer<layerNum;layer++){
			for(int i=layer;i<matrix.length-1-layer;i++){
				int temp = matrix[layer][i];
				//左转上
				matrix[layer][i] = matrix[matrix.length-i-1][layer];
				//下转左
				matrix[matrix.length-i-1][layer] = matrix[matrix.length-layer-1][matrix.length-i-1];
				//右转下，列相同
				matrix[matrix.length-layer-1][matrix.length-i-1] = matrix[i][matrix.length-layer-1];
				//上转右
				 matrix[i][matrix.length-layer-1] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = new int[][]{{1,2},{3,4}};
		rotate(matrix);
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
