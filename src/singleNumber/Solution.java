package singleNumber;

public class Solution {
	//其余都出现两次，找出数组中只出现一次的数字
	public static int singleNumber(int[] A) {  
	    if(A==null || A.length==0)  
	        return 0;  
	    int res = A[0];  
	    for(int i=1;i<A.length;i++)  
	    {  
	        res ^= A[i];  
	    }  
	    return res;  
	}  
	
	//除了一个出现了1次，其余都出现了3次
	public static int singleNumberII(int[] A) {  
	    int[] digits = new int[32];  
	    for(int i=0;i<32;i++)  
	    {  
	        for(int j=0;j<A.length;j++)  
	        {  
	            digits[i] += (A[j]>>i)&1;  
	        }  
	    }  
	    int res = 0;  
	    for(int i=0;i<32;i++)  
	    {  
	        res += (digits[i]%3)<<i;  
	    }  
	    return res;  
	}  
	
	public static void main(String[] args) {
		int[] A = new int[]{1,2,2,1,3,5,4,4,5};
		System.out.println(singleNumber(A));
	}
}
