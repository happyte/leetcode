package largestRectangleInHistogram;

import java.util.Stack;

//最大矩形面积
public class Solution {
	public static int largestRectangleArea(int[] height) {
		//数组的最后一个数字添加0 
		int[] h = new int[height.length+1];
		for(int i=0;i<height.length;i++)
			h[i] = height[i];
		h[height.length] = 0;
		Stack<Integer> stack = new Stack<>();
		int i = 0;
		int maxArea = 0;
		while(i<h.length){
			//栈为空或者当前的元素大于栈顶的元素
			if(stack.isEmpty() || h[i] >= h[stack.peek()]){
				//压入栈的是当前的索引
				stack.push(i++);
			}
			//压入栈的索引小于栈顶的索引
			else{
				int t = stack.peek();
				stack.pop();
				maxArea = Math.max(maxArea, h[t]*(stack.isEmpty() ? i:i-stack.peek()-1));
			}
		}
		return maxArea;
    }
	
	public static void main(String[] args) {
		int[] A = new int[]{2,1,5,6,2,3};
		System.out.println(largestRectangleArea(A));
	}
}
