package permutations;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(num == null || num.length==0)
        	return res;
        Arrays.sort(num);
        boolean[] used = new boolean[num.length];
        helperII(num, used, res, new ArrayList<Integer>());
        return res;
    }
	
	private static void helper(int[] num,boolean[] used,ArrayList<ArrayList<Integer>> res,ArrayList<Integer> item){
		if(item.size() == num.length){
			res.add(new ArrayList<>(item));
			return;
		}
		for(int i=0;i<num.length;i++){
			if(!used[i]){
				item.add(num[i]);
				used[i] = true;
				helper(num, used, res, item);
				used[i] = false;
				item.remove(item.size()-1);
			}
		}
	}
	
	private static void helperII(int[] num,boolean[] used,ArrayList<ArrayList<Integer>> res,ArrayList<Integer> item){
		if(item.size() == num.length){
			res.add(new ArrayList<>(item));
			return;
		}
		for(int i=0;i<num.length;i++){
			//只递归第一个相同的元素
			if(i>0&&!used[i-1]&&num[i]==num[i-1])
				continue;
			if(!used[i]){
				item.add(num[i]);
				used[i] = true;
				helper(num, used, res, item);
				used[i] = false;
				item.remove(item.size()-1);
			}
		}
	}
	
	public static void main(String[] args) {
		int[] num = new int[]{1,1,2};
		ArrayList<ArrayList<Integer>> res = permute(num);
		for(int i=0;i<res.size();i++){
			for(int j=0;j<res.get(i).size();j++){
				System.out.print(res.get(i).get(j)+" ");
			}
			System.out.println("");
		}
	}
}
