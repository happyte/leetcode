package wordSearch;


public class Solution {
	public static boolean exist(char[][] board, String word) {
		 if(board == null || board.length == 0 || word == null || word.length() == 0)
			 return false;
		 //维护这个二维数组,在dfs函数中保护现场，只是维护在dfs搜索中，防止重复搜索
		 boolean[][] used = new boolean[board.length][board[0].length];
		 for(int i=0;i<board.length;i++){
			 for(int j=0;j<board[i].length;j++)
				 if(dfs(board, word, 0, i, j, used))
					 return true;
		 }
		 return false;
	}
	
	private static boolean dfs(char[][] board,String word,int index,int i,int j,boolean[][] used){
		if(index == word.length())
			return true;
		if(i<0 || j<0 || i>=board.length || j>=board[i].length || used[i][j] || board[i][j]!=word.charAt(index))
			return false;
		used[i][j] = true;
		boolean res = dfs(board, word, index+1, i-1, j, used) || 
					  dfs(board, word, index+1, i+1, j, used) ||
					  dfs(board, word, index+1, i, j-1, used) ||
					  dfs(board, word, index+1, i, j+1, used);
		used[i][j] = false;
		return res;
	}
	
	public static void main(String[] args) {
		char[][] A = new char[][]{{'A','B','C','E'},
			                      {'S','F','C','S'},
			                      {'A','D','E','E'}};
	    String w = "ABCB";
	    //System.out.println(exist(A, w));
	    String a = new String();
	    String b = new String();
	    System.out.println(a == b);
	    System.out.println(a.equals(b));
	}
}
