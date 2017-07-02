package removeDuplicatesFromSortedArrayII;

public class Solution {
	public static int removeDuplicates(int[] A) {
		if(A==null || A.length==0)  
	        return 0;
		int index = 0;
		int count = 1;   //用来跟踪重复元素出现的个数
		for(int i=0; i<A.length; i++){
			if(i > 0 && A[i] == A[i-1]){
				count ++;
				if(count >= 3)
					continue;
			}
			else{
				count = 1;
			}
			A[index++] = A[i];
		}
		return index;
    }
	
	public static void main(String[] args) {
		int[] test = {1,1,1,2,2,2,2,3,3};
		int len = removeDuplicates(test);
		System.out.println("=====================");
		for(int i=0;i<len;i++)
			System.out.println(test[i]);
	}
}
