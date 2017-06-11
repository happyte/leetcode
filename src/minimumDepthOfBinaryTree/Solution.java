package minimumDepthOfBinaryTree;

import java.util.*;


public class Solution {
	 public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	 }
	
	public int run(TreeNode root) {
	       if (root == null)
	           return 0;
	       LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
	       int curNum = 0;    //记录当前层添加的节点数
	       int lastNum = 1;   //与curNum对应
	       int depth = 1;     //深度
	       queue.add(root);
	       while(!queue.isEmpty()){
	       		TreeNode cur = (TreeNode)queue.poll();    //找到当前queue的首元素并移除
	            if(cur.left == null && cur.right == null)   //找到的第一个叶子节点
	            	return depth;
	            lastNum --;
	            if (cur.left != null){       //左子节点存在
	            	queue.add(cur.left);
	                curNum ++;
	            }
	            if (cur.right != null){      //右子节点存在
	            	queue.add(cur.right);
	                curNum ++;
	            }
	            if(lastNum == 0){
	            	lastNum = curNum;
	                curNum = 0;
	                depth ++;
	            }
	       }
	       return 0;
	    }
}
