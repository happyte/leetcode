package maximumSubarray;

public class Solution {
	//最大连续子串
	public static int maxSubArray(int[] A) {  
	    if(A==null || A.length==0)  
	        return 0;  
	    int global = A[0];  
	    int local = A[0];  
	    for(int i=1;i<A.length;i++)  
	    {  
	        local = Math.max(A[i],local+A[i]);  
	        global = Math.max(local,global);  
	    }  
	    return global;  
	}  
	
	//最长累乘子串
	public static int maxProduct(int[] A) {  
	    if(A==null || A.length==0)  
	        return 0;  
	    if(A.length == 1)  
	        return A[0];  
	    int max_local = A[0];  
	    int min_local = A[0];  
	    int global = A[0];  
	    for(int i=1;i<A.length;i++)  
	    {  
	        int max_copy = max_local;  
	        max_local = Math.max(Math.max(A[i]*max_local, A[i]), A[i]*min_local);  
	        min_local = Math.min(Math.min(A[i]*max_copy, A[i]), A[i]*min_local);  
	        global = Math.max(global, max_local);  
	    }  
	    return global;  
	}  
}
