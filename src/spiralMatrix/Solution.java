package spiralMatrix;

import java.util.ArrayList;

public class Solution {
	public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        	return res;
        int min = Math.min(matrix.length, matrix[0].length);
        //需要遍历的层数
        int levelNum = min/2;
        for(int level=0; level<levelNum; level++){
        	//上行,与下行对应
        	for(int i=level;i<matrix[0].length-level-1;i++){
        		res.add(matrix[level][i]);
        	}
        	//右行,与左行对应
        	for(int i=level;i<matrix.length-level-1;i++){
        		res.add(matrix[i][matrix[0].length-level-1]);
        	}
        	//下行
        	for(int i=matrix[0].length-1-level;i>level;i--){
        		res.add(matrix[matrix.length-1-level][i]);
        	}
        	//左行
        	for(int i=matrix.length-1-level;i>level;i--){
        		res.add(matrix[i][level]);
        	}
        }
        //奇数行需要单独处理
        if(min%2 == 1){
        	//如果列数大于行数，最后需要添加的是行
        	if(matrix[0].length >= matrix.length){
        		for(int i=levelNum;i<=matrix[0].length-levelNum-1;i++){
        			res.add(matrix[levelNum][i]);
        		}
        	}
        	//行数大于列数，最后添加的是列
        	else{
        		for(int i=levelNum;i<=matrix.length-levelNum-1;i++){
        			res.add(matrix[i][levelNum]);
        		}
        	}
        }
        return res;
    }
	
	public static void main(String[] args) {
		int[][] A = new int[][]{{1,2,3,4},
								{5,6,7,8},
								{9,10,11,12},
								};
		ArrayList<Integer> res = spiralOrder(A);
		for(int i=0;i<res.size();i++){
			System.out.print(res.get(i)+" ");
		}
	}
}
