package combinations;

import java.util.ArrayList;

public class Solution {
	//NP问题，循环递归
	public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(n<=0 || n<k)
        	return res;
        ArrayList<Integer> item = new ArrayList<>();
        helper(1,n, k, res, item);
        return res;
    }
	
	private static void helper(int start,int n,int k,ArrayList<ArrayList<Integer>> res,ArrayList<Integer> item){
		if(item.size() == k){
			res.add(new ArrayList<>(item));
			return;
		}
		for(int i=start;i<=n;i++){
			item.add(i);
			helper(i+1, n, k, res, item);
			item.remove(item.size()-1);
		}
	}
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> res = combine(4, 4);
		for(int i=0;i<res.size();i++){
			for(int j=0;j<res.get(0).size();j++){
				System.out.print(res.get(i).get(j));
			}
			System.out.println();
		}
		String str1="hello";
		String str2="he"+ new String("llo");
		System.out.println(str1==str2);
	}
}
