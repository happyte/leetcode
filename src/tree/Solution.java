package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import dataStructure.ListNode;
import dataStructure.TreeNode;

//二叉树总结
public class Solution {
	
	//树的遍历篇
	//1.递归方式前序遍历
	public static ArrayList<Integer> preSerach(TreeNode root){
		//中左右
		ArrayList<Integer> res = new ArrayList<>();
		preHelper(root, res);
		return res;
	}
	
	private static void preHelper(TreeNode root,ArrayList<Integer> res){
		if(root == null)
			return;
		res.add(root.val);
		preHelper(root.left,res);
		preHelper(root.right,res);
	}
	
	//1.前序遍历非递归方式.与递归一样，时间复杂度为O(n),空间复杂度为O(logN)
	public static ArrayList<Integer> preSerachII(TreeNode root){
		//用一个栈来模拟
		ArrayList<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode cur = stack.pop();
			res.add(cur.val);
			if(cur.right != null)
				stack.push(cur.right);
			if(cur.left != null)
				stack.push(cur.left);
		}
		return res;
	}
	
	//1.以镜像树的方式遍历，空间复杂度为O(1)
	//步骤
	//1⃣当前节点的左孩子为空时，输出当前节点，并把当前节点设为当前节点的右孩子，因为已经构成线索树了，可以向上爬
	//2⃣当前节点的左孩子不为空时候，一直寻找中序遍历的前驱节点(即当前左子树的最右节点)，分两种情况
	//2⃣.1⃣该前驱节点的右孩子为空，把前驱节点的右孩子设为当前节点(构成线索树)，并把当前节点设为当前节点的左孩子
	//2⃣.2⃣该前驱节点的右孩子不为空，清除该线索，并把当前节点设为当前节点的右孩子
	public static ArrayList<Integer> preSerachIII(TreeNode root){
		ArrayList<Integer> res = new ArrayList<>();
		TreeNode cur = root;
		TreeNode pre = null;
		while(cur != null){
			pre = cur.left;
			if(pre == null){
				res.add(cur.val);
				cur = cur.right;
			}
			else {
				while(pre.right != null&&pre.right!=cur){
					pre = pre.right;
				}
				if(pre.right == null){
					res.add(cur.val);
					pre.right = cur;
					cur = cur.left;
				}
				else {
					pre.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}
	
	//2.中序遍历的递归方式
	public static ArrayList<Integer> inSerach(TreeNode root){
		//左中右
		ArrayList<Integer> res = new ArrayList<>();
		inHelper(root, res);
		return res;
	}
	
	private static void inHelper(TreeNode root,ArrayList<Integer> res){
		if(root == null)
			return;
		inHelper(root.left, res);
		res.add(root.val);
		inHelper(root.right, res);
	}
	
	//2.中序遍历的非递归方式
	public static ArrayList<Integer> inSerachII(TreeNode root){
		ArrayList<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode cur = stack.peek();
		while(!stack.isEmpty()){
			//把cur为根节点的左子树的左子节点全部压入
			while(cur != null&&cur.left != null){
				stack.push(cur.left);
				cur = cur.left;
			}
			TreeNode node = stack.pop();
			res.add(node.val);
			cur = node.right;
			if(cur != null)
				stack.push(cur);
		}
		return res;
	}
	
	//2.以镜像树的方式遍历，空间复杂度为O(1)
	public static ArrayList<Integer> inSerachIII(TreeNode root){
		ArrayList<Integer> res = new ArrayList<>();
		TreeNode cur = root;
		TreeNode pre = null;
		while(cur != null){
			pre = cur.left;
			if(pre == null){
				res.add(cur.val);
				cur = cur.right;
			}
			else {
				while(pre.right != null&&pre.right!=cur){
					pre = pre.right;
				}
				if(pre.right == null){
					pre.right = cur;
					cur = cur.left;
				}
				else {
					//与前序遍历相比，添加cur元素的位置换了
					res.add(cur.val);
					pre.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}
	
	//3.后序遍历的递归方式
	public static ArrayList<Integer> postSerach(TreeNode root){
		//左右中
		ArrayList<Integer> res = new ArrayList<>();
		postHelper(root, res);
		return res;
	}
	
	private static void postHelper(TreeNode root,ArrayList<Integer> res){
		if(root == null)
			return;
		postHelper(root.left, res);
		postHelper(root.right, res);
		res.add(root.val);
	}
	
	//3.后序遍历的非递归方式
	public static ArrayList<Integer> postSerachII(TreeNode root){
		//用两个栈来模拟
		ArrayList<Integer> res = new ArrayList<>();
		Stack<TreeNode> s1 = new Stack<>();
		Stack<TreeNode> s2 = new Stack<>();
		s1.push(root);
		while(!s1.isEmpty()){
			//从s1中弹出栈，并把cur的左右孩子放入s1栈中，从s1中弹出栈的要放到s2栈中
			TreeNode cur = s1.pop();
			s2.push(cur);
			if(cur.left != null)
				s1.push(cur.left);
			if(cur.right != null)
				s1.push(cur.right);
		}
		while(!s2.isEmpty())
			res.add(s2.pop().val);
		return res;
	}
	
	//一层一层打印二叉树
	//算法的复杂度是就结点的数量，O(n)，空间复杂度是一层的结点数，也是O(n)
	public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root){
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> item = new ArrayList<>();
		//用队列来完成
		LinkedList<TreeNode> queue = new LinkedList<>();
		queue.push(root);
		int last = 1;  //用来记录当前行添加元素的个数
		int nlast = 0; //用来记录下一行添加元素的个数
		while(!queue.isEmpty()){
			//poll方法是从队列头拿出，add方法是从队列尾拿进
			TreeNode cur = queue.poll();
			last--;
			item.add(cur.val);
			if(cur.left != null){
				nlast++;
				queue.add(cur.left);
			}
			if(cur.right != null){
				nlast++;
				queue.add(cur.right);
			}
			if(last == 0){
				last = nlast;
				nlast = 0;
				res.add(item);
				item = new ArrayList<>();
			}
		}
		return res;
	}
	
	//奇数行顺序，偶数行逆序
	public static ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root){
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> item = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		Stack<TreeNode> newStack = new Stack<>();
		int level = 1;
		int last = 1;
		int nlast = 0;
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode cur = stack.pop();
			item.add(cur.val);
			last--;
			if(level%2==0){
				//先加右节点，再加左节点
				if(cur.right!=null){
					newStack.push(cur.right);
					nlast++;
				}
				if(cur.left!=null){
					newStack.push(cur.left);
					nlast++;
				}
			}
			if(level%2==1){
				//先加左节点，再加右节点
				if(cur.left!=null){
					newStack.push(cur.left);
					nlast++;
				}
				if(cur.right!=null){
					newStack.push(cur.right);
					nlast++;
				}
			}
			if(last==0){
				last = nlast;
				nlast = 0;
				stack = newStack;
				newStack = new Stack<>();
				res.add(item);
				item = new ArrayList<>();
				level++;
			}
		}
		return res;
	}
	
	//树的构造篇
	//把有序的数组转换成二叉搜索树
	public static TreeNode sortedArrayToBST(int[] num){
		int l = 0;
		int r = num.length-1;
		return arrayhelper(l, r,num);
	}
	
	private static TreeNode arrayhelper(int left,int right,int[] num){
		if(left > right)
			return null;
		int m = (left+right+1)/2;
		TreeNode root = new TreeNode(num[m]);
		root.left = arrayhelper(left, m-1, num);
		root.right = arrayhelper(m+1, right, num);
		return root;
	}
	
	//把有序的链表转换成二叉搜索树,模拟中序遍历的过程
	public TreeNode sortedListToBST(ListNode head){
		if(head == null)
			return null;
		int count = 0;
		ArrayList<ListNode> list = new ArrayList<>();
		list.add(head);
		while(head != null){
			head = head.next;
			count++;
		}
		return listhelper(0, count-1, list);
	}
	
	private static TreeNode listhelper(int l,int r, ArrayList<ListNode> list){
		if(l > r)
			return null;
		int m = (l+r+1)/2;
		TreeNode left = listhelper(l, m-1, list);
		TreeNode root = new TreeNode(list.get(0).val);
		root.left = left;
		//需要更新为链表的下一位,左中右
		list.set(0, list.get(0).next);
		root.right = listhelper(m+1, r, list);
		return root;
	}
	
	
	public static void main(String[] args) {
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		TreeNode t7 = new TreeNode(7);
		t1.left = t2;
		t1.right = t3;
		t2.left = t4;
		t2.right = t5;
		t3.left = t6;
		t3.right = t7;
		ArrayList<ArrayList<Integer>> res = zigzagLevelOrder(t1);
		for(int i=0;i<res.size();i++){
			for(int j=0;j<res.get(i).size();j++)
				System.out.print(res.get(i).get(j)+" ");
			System.out.println("");
		}
	}
	
}
