package pascalTriangle;

import java.util.ArrayList;

public class Solution {
	//类似杨辉三角，每排的数字是上一排相邻数字的想加
	public static ArrayList<ArrayList<Integer>> generate(int numRows) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if (numRows <= 0)
			return res;
		ArrayList<Integer> pre = new ArrayList<>();
		pre.add(1);
		res.add(pre);
		for(int i=2;i<=numRows;i++){
			ArrayList<Integer> cur = new ArrayList<>();
			cur.add(1);
			for(int j=0;j<pre.size()-1;j++)
				cur.add(pre.get(j)+pre.get(j+1));
			cur.add(1);
			res.add(cur);
			pre = cur;
		}
		return res;
    }
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> res = generate(5);
		for(int i=0;i<res.size();i++){
			ArrayList<Integer> test = res.get(i);
			for(int j=0;j<test.size();j++)
				System.out.print(test.get(j)+" ");
			System.out.println("");
		}
	}
}
