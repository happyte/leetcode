package searchInRotatedArray;

public class Solution {
	
	public static int search(int[] A, int target) {
        if(A == null || A.length == 0)
        	return -1;
        int l = 0;
        int r = A.length-1;
        while(l<=r){
        	int m = (l+r)/2; 
        	//找到目标点
        	if(target == A[m])
        		return m;
        	//说明m-r是递增的，不受rotated的影响
        	if(A[m] < A[r]){
        		//target就位于m-r之间
        		if(target > A[m] && target <= A[r]){
        			l = m+1;
        		}
        		//target位于l-m之间
        		else{
        			r = m-1;
        		}
        	}
        	//m-r不是递增的，说明l-m是递增的
        	else{
        		//target位于l-m之间
        		if(target >= A[l] && target < A[m]){
        			r = m-1;
        		}
        		//target位于m-r之间
        		else{
        			l = m+1;
        		}
        	}
        }
        return -1;
    }
	
	public static void main(String[] args) {
		int[] A = new int[]{4,5,6,7,0,1,2};
		System.out.println(search(A, 4));
		System.out.println(search(A, 5));
		System.out.println(search(A, 6));
		System.out.println(search(A, 7));
		System.out.println(search(A, 0));
		System.out.println(search(A, 1));
		System.out.println(search(A, 2));
	}
}
