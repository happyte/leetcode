package findMinimumInRotatedArray;

public class Solution {
	public static int findMin(int[] num){
		if(num == null || num.length ==0)
			return -1;
		int min = num[0];
		int l = 0;
		int r = num.length-1;
		while(l<=r){
			int m = (l+r)/2;
			//m-r为递增,取min和m的较小值,且r=m-1
			if(num[m] < num[r]){
				min = Math.min(num[m], min);
				r = m-1;
			}
			//l-m为递增,取min和l的较小值,且l=m+1
			else {
				min = Math.min(num[l], min);
				l = m+1;
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		int[] A = new int[]{4,5,6,7,8,1,2};
		System.out.println(findMin(A));
	}
}
