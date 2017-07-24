package nQuenes;

import java.util.ArrayList;

//N皇后问题，两个皇后不能同行同列，且不能在同一对角线上
public class Solution {
	public static ArrayList<String[]> solveNQueens(int n) {
		 ArrayList<String[]> res = new ArrayList<String[]>();  
		 helper(n,0,new int[n], res);  
		 return res;  
    }	
	
	private static void helper(int n, int row, int[] columnForRow, ArrayList<String[]> res) {  
		if(row == n){
			String[] item = new String[n];
			for(int i=0;i<n;i++){
				StringBuffer sb = new StringBuffer();
				for(int j=0;j<n;j++){
					//每行插入的位置为columnForRow[i]
					if(columnForRow[i] == j){
						sb.append('Q');
					}
					else {
						sb.append('.');
					}
				}
				item[i] = sb.toString();
			}
			res.add(item);
			return;
		}
		for(int i=0;i<n;i++){
			columnForRow[row] = i;
			if(check(row, columnForRow)){
				helper(n, row+1, columnForRow, res);
			}
		}
	}  
	
	private static boolean check(int row, int[] columnForRow)  
	{  
		for(int i=0;i<row;i++){
			//判断是否同一列，是否在对角线上
			if(columnForRow[i] == columnForRow[row] 
					|| Math.abs(row-i) == Math.abs(columnForRow[row]-columnForRow[i]))
				return false;
		}
	   return true;
	}  
	
	public static void main(String[] args) {
		ArrayList<String[]> res = solveNQueens(5);
		for(int i=0;i<res.size();i++){
			for(int j=0;j<res.get(i).length;j++){
				System.out.print(res.get(i)[j]+" ");
			}
			System.out.println("");
		}
	}
}
