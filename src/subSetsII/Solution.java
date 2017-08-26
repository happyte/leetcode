package subSetsII;

import java.util.ArrayList;
import java.util.Arrays;

//去除重复的情况
public class Solution {
	public static ArrayList<ArrayList<Integer>> subsets(int[] S) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(S == null || S.length == 0)
        	return res;
        ArrayList<Integer> item = new ArrayList<>();
        res.add(item);
        Arrays.sort(S); //从小到大排序
        int start = 0;
        for(int i=0;i<S.length;i++){
        	int size = res.size();
        	for(int j=start;j<size;j++){
        		//非递归方式，添加顺序为[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]
        		item = new ArrayList<>(res.get(j));
        		item.add(S[i]);
        		res.add(item);
        	}
        	if(i<S.length-1 && S[i] == S[i+1]){
        		start = size; //上一次的大小
        	}
        	else{
        		start = 0;
        	}
        }
        return res;
    }
	
	public static ArrayList<ArrayList<Integer>> subsets2(int[] S){
        if(S == null)
        	return null;
        Arrays.sort(S);
        ArrayList<Integer> lastIndex = new ArrayList<>();
        lastIndex.add(0);
        return helper(S, S.length-1,lastIndex);
	}
	
	private static ArrayList<ArrayList<Integer>> helper(int[] S,int len,ArrayList<Integer> lastIndex){
		if(len == -1){
			ArrayList<ArrayList<Integer>> res = new ArrayList<>();
			ArrayList<Integer> item = new ArrayList<>();
			res.add(item);
			return res;
		}
		//先递归找到第一个[]
		ArrayList<ArrayList<Integer>> res = helper(S, len-1,lastIndex);
		int size = res.size();
		int start = 0;
		if(len > 0 && S[len] == S[len-1])
			start = lastIndex.get(0);
		for(int i=start;i<size;i++){
			ArrayList<Integer> item = new ArrayList<>(res.get(i));
			item.add(S[len]);
			res.add(item);
		}
		lastIndex.set(0,size);
		return res;
	}
	
	
	public static void main(String[] args) {
		int[] A = new int[]{1,2,2};
		ArrayList<ArrayList<Integer>> res = subsets2(A);
		System.out.println(res.size());
		for(int i=0;i<res.size();i++){
			for(int j=0;j<res.get(i).size();j++){
				System.out.print(res.get(i).get(j));
			}
			System.out.println("");
		}
	}
}
