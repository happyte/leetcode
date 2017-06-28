package palindromePartitioningII;


public class Solution {
	//dict数组是反着用的
	public static int minCut(String s){
		boolean[][] dict = getDict(s);
		int res[] = new int[s.length()+1];
		for(int i=0;i<s.length();i++){
			res[i+1] = i+1;
			for(int j=0;j<=i;j++){
				if(dict[j][i]){
					res[i+1] = Math.min(res[i+1], res[j]+1);
				}
			}
		}
		return res[s.length()]-1;
	}
	
	//字符串回文子串的dp下标
	private static boolean[][] getDict(String s){
		boolean[][] dict = new boolean[s.length()][s.length()];
		for(int i=s.length()-1; i>=0; i--){
			for(int j=i; j<s.length(); j++){
				if(s.charAt(i) == s.charAt(j) && (j-i<=1 || dict[i+1][j-1]))
					dict[i][j] = true;
			}
		}
		return dict;
	}
	
	static int x;
	public static void main(String[] args) {
		minCut("abccba");
	}
}
