package subTree;

import dataStructure.TreeNode;

//判断B是否为A的子树
public class Solution {
	public static boolean HasSubtree(TreeNode root1,TreeNode root2) {
		if(root1 == null || root2 == null)
			return false;
		boolean result = false;
		if(root1 != null && root2 != null){
			//只有根节点相同了，才可以判断是否为子树
			if(root1.val == root2.val)
				result = isSubTree(root1, root2);
			if(!result)
				result = HasSubtree(root1.left, root2);
			if(!result)
				result = HasSubtree(root1.right, root2);
		}
        return result;
    }
	
	//以为p1为根节点，判断p2是否为p1的子树
	public static boolean isSubTree(TreeNode p1,TreeNode p2){
		//如果此时p2树已经遍历到叶子节点的下一层,返回true
		if(p2 == null)
			return true;
		if(p1 == null)
			return false;
		if(p1.val != p2.val)
			return false;
		return isSubTree(p1.left, p2.left)&&isSubTree(p1.right, p2.right);
	}
	
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(8);
		TreeNode t2 = new TreeNode(8);
		TreeNode t3 = new TreeNode(7);
		TreeNode t4 = new TreeNode(9);
		TreeNode t5 = new TreeNode(2);
		TreeNode t6 = new TreeNode(4);
		TreeNode t7 = new TreeNode(7);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t5.left = t6;
		t5.right = t7;
		
		TreeNode p1 = new TreeNode(8);
		TreeNode p2 = new TreeNode(9);
		TreeNode p3 = new TreeNode(2);
		p1.left = p2;
		p1.right = p3;
		
		System.out.println(HasSubtree(t1, p1));
	}
}
