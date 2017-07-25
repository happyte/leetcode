package jumpGame;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//跳跃游戏，看能不能到最后一个
	public static boolean jump(int[] A) {
		if(A == null || A.length==0)
			return false;
		int reach = 0;
		for(int i=0;i<=reach&&i<A.length;i++){
			reach = Math.max(A[i]+i, reach);
		}
		if(reach < A.length-1)
			return false;
		return true;
	}
	
	//求出跳到最后一个的，跳跃的最短次数
	public static int jumpII(int[] A) {
		/*
		if(A == null||A.length == 0)
			return 0;
		int reach = 0;
		int lastReach = 0;
		int step = 0;
		for(int i=0;i<=reach&&i<A.length;i++){
			if(i > lastReach){
				lastReach = reach;
				step++;
			}
			reach = Math.max(A[i]+i, reach);
		}
		if(reach < A.length-1)
			return 0;
        return step;*/
		int last = 0, reach = 0, best = 0;  
        List<Integer> steps = new ArrayList<Integer>();  
          
        for (int i = 0; i < A.length; i++) {  
            if (i > last) {  
                last = reach;  
                steps.add(best);  
            }  
            if(i+A[i] > reach) {  
                reach = i+A[i];  
                best = i;  
            }  
              
        }  
        for(int i=0;i<steps.size();i++){
        	System.out.print(A[steps.get(i)]+" ");
        }
        System.out.println("");
        return steps.size(); 
    }
	
	public static void main(String[] args) {
		int[] A = new int[]{2,3,1,1,4};
		System.out.println(jumpII(A));
	}
}
