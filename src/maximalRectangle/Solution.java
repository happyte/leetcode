package maximalRectangle;

import java.util.Stack;

public class Solution {
	public static int maximalRectangle(char[][] matrix) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return 0;
		//维护这个数组,初始都为0
		int[] h = new int[matrix[0].length];
		int maxArea = 0;
		for(int i=0;i<matrix.length;i++){
			for(int j=0;j<matrix[0].length;j++){
				h[j] = (matrix[i][j] == '0'? 0:h[j]+1);
			}
			for(int j=0;j<matrix[0].length;j++){
				System.out.print(h[j]);
			}
			System.out.println();
			maxArea = Math.max(maxArea, largestRectangle(h));
		}
        return maxArea;
    }
	
	private static int largestRectangle(int[] height){
		int[] h = new int[height.length+1];
		for(int i=0;i<height.length;i++)
			h[i] = height[i];
		h[height.length] = 0;
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		int maxArea = 0;
		while(i<h.length){
			if(stack.isEmpty() || h[i] >= h[stack.peek()]){
				stack.push(i++);
			}
			else{
				int t = stack.peek();
				stack.pop();
				maxArea = Math.max(maxArea, h[t]*(stack.isEmpty() ? i:i-stack.peek()-1));
			}
		}
		return maxArea;
	}
	
	public static void main(String[] args) {
		char[][] A = new char[][]{{'1','0','1','0','0'},
							      {'1','0','1','1','1'},
							      {'1','1','1','1','1'},
							      {'1','0','0','1','0'}};
		System.out.println(maximalRectangle(A));
	}
}
