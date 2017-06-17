package binaryTreeInorderTraversal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class Solution {
	public class TreeNode {
        int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	//中序遍历的线索树方式
	public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while(cur != null){
            //1.如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点
            if(cur.left == null){
                res.add(cur.val);
                cur = cur.right;
            }
            else{
             	//当前节点的左孩子不为空
                //则在当前节点的左子树中找到当前节点左子树的最右节点（即为前驱节点）
                pre = cur.left;
                while(pre.right != null && pre.right != cur){
                    pre = pre.right;
                }
                //如果前驱节点的右孩子为空，将它的右孩子设为当前节点，当前节点更新为当前节点的左孩子
                if(pre.right == null){
                    pre.right = cur;
                    cur = cur.left;
                }
                //如果前驱节点的右孩子为当前节点，并输出当前节点,将它的右孩子设为空，当前节点更新为当前节点的右孩子
                if(pre.right == cur){
                    res.add(cur.val);
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list1 = new LinkedList<>();
		list1.offer(1);
		list1.offer(2);
		list1.offer(3);
		list1.offer(4);
		list1.offer(5);
		System.out.println("peek:"+list1.peek());
		System.out.println("list:"+list1);
		System.out.println("=======================");
		LinkedList<Integer> list2 = new LinkedList<>();
		list2.push(1);
		list2.push(2);
		list2.push(3);
		list2.push(4);
		list2.push(5);
		System.out.println("peek:"+list2.pop());
		System.out.println("list:"+list2);
		System.out.println("=======================");
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		int min = stack.peek();
		System.out.println("min:"+min);
		System.out.println("stack:"+stack);
		Iterator<Integer> iterator = stack.iterator();
		while(iterator.hasNext()){
			System.out.println("iterator:"+iterator.next());
		}
	
		
	}
}
