package searchInRotatedArrayII;

public class Solution {
	//会有重复的元素出现
	public static boolean search(int[] A, int target) {
        if(A == null || A.length == 0)
        	return false;
        int l = 0;
        int r = A.length-1;
        while(l<=r){
        	int m = (l+r)/2;
        	if(A[m] == target)
        		return true;
        	if(A[m] < A[r]){
        		if(target > A[m] && target <= A[r]){
        			l = m+1;
        		}
        		else{
        			r = m-1;
        		}
        	}
        	else if(A[m] > A[l]){
        		if(target >= A[l] && target < A[m]){
        			r = m-1;
        		}
        		else{
        			l = m+1;
        		}
        		
        	}
        	//A[m]==A[l],A[m]==A[r]
        	else if(A[m] == A[l]){
        		l++;
        	}
        	else{
        		r--;
        	}
        }
        return false;
    }
	
	public static void main(String[] args) {
		int[] A = new int[]{3,1,1};
		System.out.println(search(A, 3));
	}
}
