package maximunSubarray;

public class Solution {
	public static int maxSubArray(int[] A) {
        if(A == null || A.length == 0)
        	return 0;
        int local = A[0];
        int global = A[0];
        for(int i=1;i<A.length;i++){
        	local = Math.max(A[i],local+A[i]);
        	global = Math.max(local, global);
        	System.out.println("local:"+local);
        	System.out.println("global:"+global);
        }
        return global;
    }
	
	public static void main(String[] args) {
		int[] A = new int[]{-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray(A));
	}
}
