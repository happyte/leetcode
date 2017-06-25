package com.zs.offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import dataStructure.ListNode;
import dataStructure.TreeLinkNode;
import dataStructure.TreeNode;


public class Solution {
	
	/**
	 * 找出1-n中1出现的数量
	 */
	public static int NumberOf1Between1AndN_Solution(int n) {
		   int count = 0;
		   for(int i=0; i<=n; i++){
			   char[] vals = String.valueOf(i).toCharArray();
			   for(int j=0; j<vals.length; j++){
				   if (vals[j] == '1')
					   count += 1;
			   }
		   }
		   return count;
	 }
	
	/**
	 * 排列组合的最小值
	 */
	public static String PrintMinNumber(int [] numbers) {
		 StringBuffer sBuffer = new StringBuffer();
		 ArrayList< Integer> array =  new ArrayList<>();
		 for(int i=0; i<numbers.length; i++)
			 array.add(numbers[i]);
		 Collections.sort(array, new Comparator<Integer>() {
			 @Override
			public int compare(Integer o1, Integer o2) {
				String str1 = o1+""+o2;
				String str2 = o2+""+o1;
				return str1.compareTo(str2);
			}
		 });
		 for(Integer j:array)
			 sBuffer.append(String.valueOf(j));
		 return sBuffer.toString();
	 }
	
	/**
	 * 丑数
	 */
	public static int GetUglyNumber_Solution(int index) {
			if (index <= 0)
				return 0;
			int[] result = new int[index];
			int i2 = 0;
			int i3 = 0;
			int i5 = 0;
			result[0] = 1;
			int count = 0;
			while(count < index-1){
				int temp = min(result[i2]*2, result[i3]*3, result[i5]*5);
				if(temp == result[i2]*2)
					i2 ++;
				if(temp == result[i3]*3)
					i3 ++;
				if(temp == result[i5]*5)
					i5 ++;
				count++;
				result[count] = temp;
			}
	        return result[index-1];
	}
	
	private static int min(int a,int b, int c){
		int min = Math.min(a, b);
		return Math.min(min, c);
	}
	
	/**
	 * 第一个只出现一次的字符,并返回其位置
	 */
	public static int FirstNotRepeatingChar(String str) {
		String str1 = str;
        Map<Character, Integer> map = new HashMap<>();
        char[] strs = str1.toCharArray();
        for(int i=0; i<strs.length; i++){
        	if(map.containsKey(strs[i])){
        		int value = map.get(strs[i]);
        		value += 1;
        		map.put(strs[i], value);
        	}
        	else {
        		map.put(strs[i], 1);
			}
        }
        System.out.println(map);
        for(int i=0; i<str1.length(); i++){
        	char ch = str1.charAt(i);
        	if(map.get(ch) == 1)
        		return i;
        }
        return 0;
    }
	
	/**
	 * 两个链表找到第一个相同的节点
	 */
	 public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
		 if(pHead1 == null || pHead2 == null)
	            return null;
	        ListNode head1 = pHead1;
	        ListNode head2 = pHead2; 
	        Stack<ListNode> s1 = new Stack<>();
	        Stack<ListNode> s2 = new Stack<>();
	        //链表1压入栈中
	        while(head1 != null){
	            s1.push(head1);
	            head1 = head1.next;
	        }
	        //链表2压入栈中
	        while(head2 != null){
	            s2.push(head2);
	            head2 = head2.next;
	        }
	        //同时出栈，直到公共节点前一个
	        while(!s1.isEmpty() && !s2.isEmpty()){
	            ListNode l1 = s1.pop();
	            ListNode l2 = s2.pop();
	            if(l1.val != l2.val){
	                if(l1.next != null)
	                    return l1.next;
	                else
	                    return null;
	            }
	        }
	        if(s1.isEmpty())
	            return pHead1;
	        if(s2.isEmpty())
	            return pHead2;
	        return null;
	   }
	
	 /**
	  * 数字在排序数组中出现的数字
	  */
	public static int GetNumberOfK(int [] array , int k) {
		//空数组或者k的值比顺序排列的数组的最后一个都大
		 if(array.length == 0 || array[array.length-1] < k)
			 return 0;
		 int first = GetFirstOfK(array, k, 0, array.length-1);
		 int last  = GetLastOfK(array, k, 0, array.length-1);
		 int num = 0;
         if(first > -1 && last > -1)
             num = last-first+1;
	     return num;
	}
	
	private static int GetFirstOfK(int[] array, int k, int start, int end){
		if(start > end)
			return -1;
		int middle = (start+end)/2;
		int middleNumber = array[middle];
		//中间数为k的情况下，先判断middle-1是否为k,如果不为k,那么就是k第一次出现的地方
		if(middleNumber == k){
			if(middle == 0 || (array[middle-1] != k && middle > 0)){
				return middle;
			}
			//前一个数仍然为k,那么继续在前半段找
			else {
				end = middle-1;
			}
		}
		//继续在前半段找
		else if(middleNumber > k)
			end = middle - 1;
		//在后半段找
		else if(middleNumber < k)
			start = middle + 1;
		return GetFirstOfK(array, k, start, end);
	}
	
	private static int GetLastOfK(int[] array, int k, int start, int end){
		if(start > end)
			return -1;
		int middle = (start+end)/2;
		int middleNumber = array[middle];
		//中间数为k的情况下，先判断middle+1是否为k,如果不为k,那么就是k最后一次出现的地方
		if(middleNumber == k){
			if((array[middle+1] != k && middle > 0) || middle == 0){
				return middle;
			}
			//去后半段找
			else {
				start = middle+1;
			}
		}
		//去前半段找
		else if(middleNumber > k)
			end = middle - 1;
		//去后半段找
		else if(middleNumber < k)
			start = middle + 1;
		return GetLastOfK(array, k, start, end);
	}
	
	/**
	 * 删除链表重复的节点
	 */
	public static ListNode deleteDuplication(ListNode pHead){
		if(pHead == null)
            return null;
        ListNode helper = new ListNode(0);
        helper.next = pHead;
        ListNode pre = helper;
        boolean flag = false;
        while(pHead != null){
            ListNode pNext = pHead.next;
            if(pNext == null) break;
            if(pHead.val == pNext.val){
                while(pNext != null && pHead.val == pNext.val)
                    pNext = pNext.next;
                pre.next = pNext;
                pHead = pNext;
                System.out.println(helper.next);
                System.out.println(pre.next);
            }
            else{
                if(!flag){
                    helper.next = pHead;
                    flag = true;
                }
                pre = pHead;
                pHead = pNext;
            }
        }
        return helper.next;
    }
	
	/**
	 * 用位运算实现两数相加
	 */
	public static int Add(int num1,int num2) {
        int sum,temp;
        do{
            sum = num1^num2;
            temp = (num1&num2)<<1;
            num1 = sum;
            num2 = temp;
        }
        while(num2 != 0);
        return sum;
    }
	
	/**
	 * 判断一个数组是否为二叉搜索树后序遍历的结果
	 */
	public static boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length < 1)
        	return false;
        return VerifySquenceOfSUBBST(sequence, 0, sequence.length-1);
    }
	
	public static boolean VerifySquenceOfSUBBST(int [] sequence, int start, int end){
//		if(end <= start)
//			return true;
        int i = start;
        int root = sequence[end];   //根节点的大小是数组中的最后一个元素
        // 从数组开始找到左子树最后一个索引的下一个，即右子树的第一个索引
        for(; i<end; i++){
            if(sequence[i] > root)
                break;
        }
        int j = i;
        //如果右子树有小于根节点大小的数存在，则不是二叉搜索树后序遍历的结果，返回false
        for(; j<end; j++){
            if(sequence[j] < root)
                return false;
        }
        //判断左子树是否满足
        boolean left = true;
        if(i > start)
        	left = VerifySquenceOfSUBBST(sequence, start,i-1);
        //判断右子树是否满足
        boolean right = true;
        if(i < end-1)
        	right = VerifySquenceOfSUBBST(sequence, i, end-1);
        return left&&right;
	}
	
	/**
	 * 根据前序遍历和中序遍历重建二叉树
	 */
	 public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
		if(pre.length == 0 || in.length ==0)
			 return null;
	    return reConstructSUBBinaryTree(0, pre.length-1, pre, 0, in.length-1, in);
	 }
	 
	 private static TreeNode reConstructSUBBinaryTree(int startPre, int endPre, int[] pre, int startIn, int endIn, int[] in){
		 if(startPre > endPre || startIn > endIn)
			 return null;
		 TreeNode root = new TreeNode(pre[startPre]);
		 for(int i = startIn; i<endIn; i++){
			 //中序遍历的数组中的值等于前序遍历数组首个大小
			 if(in[i] == root.val){
				 root.left = reConstructSUBBinaryTree(startPre+1, startPre+i-startIn, pre, startIn, i-1, in);
				 root.right = reConstructSUBBinaryTree(startPre+i-startIn+1, endPre, pre, i+1, endIn, in);
			 }
		 }
		 return root;
	 }
	 
	 /**
	  * 二叉搜索树根据中序遍历建立二叉搜索树
	  */
	 public static TreeNode Convert(TreeNode pRootOfTree) {
	        if(pRootOfTree == null)
	            return null;
	        Stack<TreeNode> stack = new Stack<>();
	        TreeNode p = pRootOfTree;  //记录前一个节点
	        TreeNode pre = null;
	        boolean flag = false;
	        while(p != null || !stack.isEmpty()){
	            //把当前根节点的左子节点一层层压入栈中
	            while(p != null){
	                stack.push(p);
	                p = p.left;
	            }
	            p = stack.pop();
	            if(!flag){
	                pRootOfTree = p;  //中序遍历的第一个节点,只进来一次
	                pre = p;
	                flag = true;
	            }
	            else{
	                //左子树需要建立right,右子树需要建立left
	                pre.right = p;
	                p.left = pre;
	                pre = p;
	            }
	            p = p.right;
	        }
	        return pRootOfTree;
	    }
	
	 /**
	  * 扑克牌顺子
	  */
	 public boolean isContinuous(int [] numbers) {
			if(numbers == null || numbers.length < 1)
	            return false;
	        Arrays.sort(numbers);    //排序
	        int numberOfZero = 0;    //数组中0的个数
	        int numberOfGap = 0;     //数组中非连续数字的间隔
	        for(int i=0; i<numbers.length; i++){
	            if(numbers[i] == 0)
	                numberOfZero++;
	        }
	        int small = numberOfZero;
	        int big = small + 1;
	        while(big < numbers.length){
	            //对子
	            if(numbers[small] == numbers[big])
	                return false;
	            numberOfGap += numbers[big] - numbers[small] -1;  //比如1,3，中间补1个数
	            small++;
	            big++;
	        }
	        return numberOfZero >= numberOfGap ? true:false;
	   }
	 
	 /**
	  * 二叉树中序遍历的下一个节点
	  */
	 public TreeLinkNode GetNext(TreeLinkNode pNode){
	        if(pNode == null)
	            return null;
	        TreeLinkNode pNext = null;
	        if(pNode.right != null){
	            TreeLinkNode node = pNode.right;
	            while(node.left != null)
	                node = node.left;
	            pNext = node;  //父节点
	        }
	        else{
	            TreeLinkNode parent = pNode.next; //父节点
	            //直到找到一个节点是它父亲节点的左子节点
	            while(parent != null && pNode != parent.left){ 
	               parent = parent.next;
	               pNode = pNode.next;
	            }
	            pNext = parent;
	        }
	        return pNext;
	  }
	 
	public static void main(String[] args) {
		
        
	}
}
