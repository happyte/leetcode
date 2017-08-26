package subSets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//列出字符串数组的所有组合情况的可能
public class Solution {
	public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(S == null || S.length == 0)
        	return res;
        ArrayList<Integer> item = new ArrayList<>();
        res.add(item);
        Arrays.sort(S); //从小到大排序
        for(int i=0;i<S.length;i++){
        	int size = res.size();
        	for(int j=0;j<size;j++){
        		//非递归方式，添加顺序为[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]
        		item = new ArrayList<>(res.get(j));
        		item.add(S[i]);
        		res.add(item);
        	}
        }
        return res;
    }
	
	public static ArrayList<ArrayList<Integer>> subsets2(int[] S){
        if(S == null)
        	return null;
        Arrays.sort(S);
        return helper(S, S.length-1);
	}
	
	private static ArrayList<ArrayList<Integer>> helper(int[] S,int len){
		if(len == -1){
			ArrayList<ArrayList<Integer>> res = new ArrayList<>();
			ArrayList<Integer> item = new ArrayList<>();
			res.add(item);
			return res;
		}
		//先递归找到第一个[]
		ArrayList<ArrayList<Integer>> res = helper(S, len-1);
		int size = res.size();
		for(int i=0;i<size;i++){
			ArrayList<Integer> item = new ArrayList<>(res.get(i));
			item.add(S[len]);
			res.add(item);
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		int[] A = new int[]{1,2,3};
		ArrayList<ArrayList<Integer>> res = subsets(A);
		System.out.println(res.size());
		for(int i=0;i<res.size();i++){
			for(int j=0;j<res.get(i).size();j++){
				System.out.print(res.get(i).get(j));
			}
			System.out.println("");
		}
	}
}
