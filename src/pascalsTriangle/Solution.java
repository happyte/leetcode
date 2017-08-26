package pascalsTriangle;

import java.util.ArrayList;

public class Solution {
	//给出金字塔
	public static ArrayList<ArrayList<Integer>> generate(int numRows) {  
	     ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
	     if(numRows<=0)  
	        return res;  
	     ArrayList<Integer> pre = new ArrayList<Integer>();  
	     pre.add(1);  
	     res.add(pre);  
	     for(int i=2;i<=numRows;i++)  
	     {  
	         ArrayList<Integer> cur = new ArrayList<Integer>();  
	         cur.add(1);  
	         for(int j=0;j<pre.size()-1;j++)  
	         {  
	             cur.add(pre.get(j)+pre.get(j+1));  
	         }  
	         cur.add(1);  
	         res.add(cur);  
	         pre = cur;  
	     }  
	     return res;  
	}  
	
	//返回第k层金字塔的数字
	public static ArrayList<Integer> getRow(int rowIndex) {  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    if(rowIndex<0)  
	        return res;  
	    res.add(1);  
	    for(int i=1;i<=rowIndex;i++)  
	    {  
	        for(int j=res.size()-2;j>=0;j--)  
	        {  
	            res.set(j+1,res.get(j)+res.get(j+1));  
	        }  
	        res.add(1);  
	    }  
	    return res;  
	}  
}
