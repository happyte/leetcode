package removeDuplicatesFromSortedArray;


public class Solution {
	public static int removeDuplicates(int[] A) {
		if(A==null || A.length==0)  
	        return 0;
		//不仅需要返回长度，还需要操作A数组
		int index = 0;
		for(int i=0; i<A.length; i++){
			if(i > 0 && A[i] == A[i-1])
				continue;
			A[index++] = A[i];
		}
	    return index;
    }
	
	public static void main(String[] args) {
		int[] test = {1,1,1,2,2,3,3};
		int len = removeDuplicates(test);
		System.out.println("=====================");
		for(int i=0;i<len;i++)
			System.out.println(test[i]);
	}
}
