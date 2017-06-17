package binaryTreeZigZag;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.Stack;

import dataStructure.TreeNode;

public class Solution {
	public static ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(pRoot == null)
            return res;
        ArrayList<Integer> item = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();   //通过栈的特点控制行的左右打印
        Stack<TreeNode> newStack = new Stack<>();
        stack.push(pRoot);
        int curNum = 1;
        int nextNum = 0;
        int level = 0;
        while(!stack.isEmpty()){
        	newStack = new Stack<>();
        	item = new ArrayList<>();
        	//stack只用于当前层的保存，而newStack负责下一层的保存
            while(!stack.isEmpty()){
            	TreeNode cur = stack.pop();
            	item.add(cur.val);
            	//从右往左保存
            	if(level%2 == 1){
            		if(cur.right != null){
            			newStack.push(cur.right);
            		}
            		if(cur.left != null){
            			newStack.push(cur.left);
            		}
            	}
            	if(level%2 == 0){
            		if(cur.left != null){
            			newStack.push(cur.left);
            		}
            		if(cur.right != null){
            			newStack.push(cur.right);
            		}
            	}
            }
            res.add(item);
            level++;
            stack = newStack;
        }
        return res;
	}
	
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(8);
		TreeNode t2 = new TreeNode(6);
		TreeNode t3 = new TreeNode(10);
		TreeNode t4 = new TreeNode(5);
		TreeNode t5 = new TreeNode(7);
		TreeNode t6 = new TreeNode(9);
		TreeNode t7 = new TreeNode(11);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.left = t6;
		t3.right = t7;
		ArrayList<ArrayList<Integer>> res = Print(t1);
		System.out.println("res:"+res);
		
	}
}
