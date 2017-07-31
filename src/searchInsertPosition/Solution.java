package searchInsertPosition;

public class Solution {
	 public static int searchInsert(int[] A, int target) {
	     if(A == null || A.length == 0)
	    	 return 0;
	     int index = 0;
	     if(target <= A[0])
	    	 return index;
	     if(target > A[A.length-1])
	    	 return A.length;
	     for(int i=1;i<A.length;i++){
	    	 if(target == A[i]){
	    		 index = i;
	    		 break;
	    	 }
	    	 else if (target > A[i-1]&&target < A[i]) {
				index = i;
				break;
			}
	     }
	     return index;
	 }
	 
	 public static void main(String[] args) {
		int[] A = new int[]{1,3,5,6};
		System.out.println(searchInsert(A, 1));
	}
}
