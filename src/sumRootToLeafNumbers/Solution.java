package sumRootToLeafNumbers;

//求出根节点到叶子节点的和的累加
public class Solution {
	public class TreeNode {
		int val;
	    TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
	 }
	 public int sumNumbers(TreeNode root) {
	        return helper(root,0);
	 }
	 private int helper(TreeNode root, int sum){
	        if (root == null)
	            return 0;
	        if(root.left == null && root.right == null)
	            return 10*sum + root.val;
	        return helper(root.left,10*sum + root.val) + helper(root.right,10*sum + root.val);
	 }
}
