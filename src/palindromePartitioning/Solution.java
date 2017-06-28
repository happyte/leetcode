package palindromePartitioning;

import java.util.ArrayList;

public class Solution {
	public static ArrayList<ArrayList<String>> partition(String s){
		ArrayList<ArrayList<String>> res = new ArrayList<>();
		if(s == null || s.length() == 0)
			return res;
		ArrayList<String> item = new ArrayList<>();
		helper(s, getDict(s), 0, item, res);
		return res;
	}
	//暴力循环递归
	private static void helper(String s,boolean[][] dict,int start,ArrayList<String> item,ArrayList<ArrayList<String>> res){
		if(start >= s.length()){
			res.add(new ArrayList<String>(item));
			for(String a:new ArrayList<>(item)){
				System.out.println(a);
			}
			return;
		}
		for(int i=start; i<s.length(); i++){
			if(dict[start][i]){
				//全局变化的量️需要保护现场
				item.add(s.substring(start, i+1));
				helper(s, dict, i+1, item, res);
				item.remove(item.size()-1);
			}
		}
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
	
	public static void main(String[] args)  {
		System.out.println(partition("a"));
		System.out.println('A'+10+3.1 );
		Integer n1 = new Integer(47);
        Integer n2 = new Integer(47);
        System.out.print(n1 == n2);//false
        System.out.print(",");
        System.out.println(n1 != n2);//true
	}
	
}
