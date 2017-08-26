package trappingRainWater;

//模拟接水过程
public class Solution {
	 //模拟接雨水过程
	 public static int trap(int[] A) {
		 if(A == null||A.length == 0)
			 return 0;
		 int area = 0;
		 int max = 0;
		 int maxIndex = 0;
		 //第一次遍历，找到最高点和索引值
		 for(int i=0;i<A.length;i++){
			 if(A[i]>max){
				 max = A[i];
				 maxIndex = i;
			 }
		 }
		 //从左往最高点找,root为寻找过程中的最高点
		 int root = A[0];
		 for(int i=0;i<maxIndex;i++){
			 if(root<A[i])
				 root = A[i];
			 else
				 area += (root-A[i]);
		 }
		//从右往最高点找
		 root = A[A.length-1];
		 for(int i=A.length-1;i>maxIndex;i--){
			 if(root<A[i])
				 root = A[i];
			 else
				 area += (root-A[i]);
		 }
		 System.out.println(area);
		 return area;
	 }
	 
	 public static void main(String[] args) {
		int[] A = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(trap(A));
	}
}
