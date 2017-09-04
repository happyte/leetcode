package BAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import dataStructure.ListNode;

//牛客网BAT算法练习
public class Main {
	/**
	 * @param source:目标串
	 * @param pattern:模式串
	 */
	private static boolean KMP(String source, String pattern){
		int[] N = getN(pattern);
		for(int i=0;i<N.length;i++)
			System.out.print(N[i]+" ");
		System.out.println("");
		int sourceLength = source.length();
		int patternLength = pattern.length();
		for(int i=0;i<=(sourceLength-patternLength);){
			String str = source.substring(i, i+patternLength);
			int count = getNext(pattern, str, N);
			if(count == 0){
				System.out.println("在i:"+i+"位置匹配成功");
				return true;
			}
			i+=count;
		}
		return false;
	}
	
	/**
	 * 返回下一次要移动的步数，移动步数:当前已经匹配的字符串－最后一位匹配的字符对应在Next表中的数字
	 * str和pattern是同样长度的
	 * @param pattern
	 * @param str
	 * @param N
	 * @return
	 */
	private static int getNext(String pattern,String str,int[] N){
		char[] v1 = str.toCharArray();
		char[] v2 = pattern.toCharArray();
		int n = str.length();
		int x = 0;
		while(n--!=0){
			if(v1[x]!=v2[x]){
				if(x == 0){
					return 1;
				}
				return x-N[x-1];
			}
			x++;
		}
		return 0;
	}
	
	/**
	 * 根据模式串返回next表
	 * @param pattern
	 * @return
	 */
	private static int[] getN(String pattern){
		char[] pat = pattern.toCharArray();
		int j = pat.length;
		int[] N = new int[j];
		for(int i=j;i>=2;i--){
			N[i-1] = getK(i, pat);
		}
		return N;
	}
	
	/**
	 * 计算next表中的某一位j，第一位不需要计算，肯定为0
	 * @param j
	 * @param pat
	 * @return
	 */
	private static int getK(int j, char[] pat){
		int x = j-2;
		int y = 1;
		while(x>=0&&compare(pat, 0, x, y, j-1)){
			x--;
			y++;
		}
		return ++x;
	}
	
	/**
	 * 比较字符串是否相同
	 * @param pat
	 * @param b1
	 * @param e1
	 * @param b2
	 * @param e2
	 * @return
	 */
	private static boolean compare(char[] pat,int b1,int e1,int b2,int e2){
		//e1-b1=e2-b2,最多需要比较e1-b1+1次
		int n = e1-b1+1;
		while(n--!=0){
			if(pat[b1]!=pat[b2])
				return true;
			b1++;
			b2++;
		}
		return false;
	}
	
	/**
	 * 字符串B是否为字符串A的变形
	 */
	public static boolean chkRotation(String A, int lena, String B, int lenb) {
		String C = A+A;
		if(KMP(C, B))
			return true;
        return false;
    }
	
	/**
	 * 字符串单词的逆序
	 */
	public static String reverseSentence(String A, int n) {
		//先对整个字符串逆序
		char[] strs = A.toCharArray();
		reverse(strs,0,n-1);
		int left = -1;
		int right = -1;
		for(int i=0;i<strs.length;i++){
			if(strs[i] != ' '){
				left = i==0||strs[i-1]==' '? i:left;
				right = i==strs.length-1||strs[i+1]==' '? i:right;
				//System.out.println("i:"+i+" left:"+left+" right:"+right);
			}
			if(left != -1&& right != -1) {
				reverse(strs, left, right);
				left = -1;
				right = -1;
			}
		}
		return String.valueOf(strs);
	}
	
	public static void reverse(char[] strs,int start,int end){
		while(start<end){
			char temp = strs[end];
			strs[end] = strs[start];
			strs[start] = temp;
			start++;
			end--;
		}
	}
	
	/**
	 * 对于一个字符串，请设计一个算法，将字符串的长度为len的前缀平移到字符串的最后。
	 */
	public static String stringTranslation(String A, int n, int len) {
		char[] strs = A.toCharArray();
		reverse(strs, 0, n-1);
		reverse(strs, 0, n-len-1);
		reverse(strs, n-len, n-1);
		return String.valueOf(strs);
	}
	
	/**
	 * 
	 */
	public static class MyComparator implements Comparator<String>{
		//o1+o2升序排列
		@Override
		public int compare(String o1, String o2) {
			return (o1+o2).compareTo(o2+o1);
		}
		
	}
	
	public static String findSmallest(String[] strs, int n) {
		StringBuilder sb = new StringBuilder();
		Arrays.sort(strs, new MyComparator());
		for(int i=0;i<n;i++)
			sb.append(strs[i]);
        return sb.toString();
    }
	
	/**
	 * 对于一个字符串,请设计一个高效算法，找到字符串的最长无重复字符的子串长度。
	 * map表中存储着上一次map表中字符的位置，pre以str[i－1]字符结尾的情况的最大不重复长度
	 */
	public static int longestSubstring(String A, int n) {
		char[] strs = A.toCharArray();
		int[] map = new int[256];
		for(int i=0;i<256;i++)
			map[i] = -1;
		int len = 0;
		int pre = -1;
		int cur = 0;
		for(int i=0;i<n;i++){
			//判断谁先出现在右边
			pre = Math.max(pre, map[strs[i]]);
			cur = i-pre;
			len = Math.max(len, cur);
			map[strs[i]] = i;
			System.out.println("pre:"+pre+" cur:"+cur+" len:"+len);
		}
		return len;
	}
	
	/**
	 * 现有两个升序链表，且链表中均无重复元素。请设计一个高效的算法，打印两个链表的公共值部分。
	 */
	public static int[] findCommonParts(ListNode headA, ListNode headB){
		LinkedList<Integer> list = new LinkedList<>();
		while(headA != null&&headB != null){
			if(headA.val < headB.val)
				headA = headA.next;
			else if (headA.val > headB.val)
				headB = headB.next;
			else {
				list.add(headA.val);
				headA = headA.next;
				headB = headB.next;
			}
		}
		int[] res = new int[list.size()];
		int i = 0;
		while(!list.isEmpty()){
			res[i++] = list.pollFirst();
		}
		return res;
	}
	
	/**
	 * 有一个单链表，请设计一个算法，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点。
	 */
	public static ListNode inverse(ListNode head, int k) {
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode pre = helper;
		ListNode cur = pre.next;
		int count = 0;
		while(cur != null){
			count++;
			ListNode next = cur.next;
			if(count == k){
				pre = reverse(pre, next);
				count = 0;
			}
			cur = next;
		}
		return helper.next;
    }
	
	public static ListNode reverse(ListNode pre,ListNode end){
		ListNode head = pre.next;
		ListNode cur = pre.next.next;
		while(cur != end){
			ListNode next = cur.next;
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
		head.next = end;
		return head;
	}
	
	/**
	 * 现在有一个单链表。链表中每个节点保存一个整数，再给定一个值val，把所有等于val的节点删掉。
	 */
	public static ListNode clear(ListNode head, int val) {
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode pre = helper;
		ListNode cur = pre.next;
		while(cur != null){
			ListNode next = cur.next;
			if(cur.val == val){
				pre.next = next;
				cur = next;
			}
			else {
				pre = cur;
				cur = next;
			}
		}
        return helper.next;
    }
	
	/**
	 * 请编写一个函数，检查链表是否为回文。
	 */
	public static boolean isPalindrome(ListNode pHead) {
		ListNode head = pHead;
		ListNode rHead = reverseList(head);
		while(pHead != null){
			System.out.print(pHead.val+" ");
			pHead = pHead.next;
		}
		System.out.println("");
		while(rHead != null){
			System.out.print(rHead.val+" ");
			rHead = rHead.next;
		}
		System.out.println("");
//		while(pHead != null&& rHead != null){
//			if(pHead.val != rHead.val)
//				return false;
//			else {
//				pHead = pHead.next;
//				rHead = rHead.next;
//			}
//		}
		return true;
    }
	
	public static ListNode reverseList(ListNode head){
		ListNode pre = null;
		ListNode cur = head;
		while(cur != null){
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}
	
	/**
	 * 复杂链表的复制
	 */
	public static class RandomListNode {
	    int label;
	    RandomListNode next = null;
	    RandomListNode random = null;
	    RandomListNode(int label) {
	        this.label = label;
	    }
	}
	 
	public static RandomListNode Clone(RandomListNode pHead){
		if(pHead == null)
			return null;
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode newHead = new RandomListNode(pHead.label);
		map.put(pHead, newHead);
		RandomListNode pre = newHead;     //新链表头元素
		RandomListNode node = pHead.next; //旧链表头元素的下一个元素
		while(node != null){
			RandomListNode newNode = new RandomListNode(node.label);
			map.put(node, newNode);
			pre.next = newNode;  //新链表添加元素
			pre = newNode;
			node = node.next;
		}
		node = pHead;  //旧链表重新指向头节点
		RandomListNode copyNode = newHead;  //新链表指向头节点
		while(node != null){
			copyNode.random = map.get(node.random);  //新链表的随机节点指向旧链表的随机节点
			node = node.next;
			copyNode = copyNode.next;
		}
		return newHead;
	}
	
	/**
	 * 如何判断一个单链表是否有环？有环的话返回进入环的第一个节点的值，无环的话返回-1。
	 */
	public int chkLoop(ListNode head, int adjust) {
		if(head == null)
			return -1;
		ListNode walker = head;     //慢指针,每次走一步
		ListNode runner = head;     //快指针,每次走两步
		while(runner != null&& runner.next != null){
			walker = walker.next;
			runner = runner.next.next;
			if(walker == runner)
				break;
		}
		if(runner == null||runner.next == null)
			return -1;
		runner = head;
		while(runner != walker){
			runner = runner.next;
			walker = walker.next;
		}
		return walker.val;
    }
	
	/**
	 * 两个无环单链表，若两个链表的长度分别为m和n，请设计一个时间复杂度为O(n + m)，额外空间复杂度为O(1)的算法，判断这两个链表是否相交。
	 * 链表1先走n-m步，然后两个链表同步走，如果相交则会遇到一个公共
	 */
	public boolean chkIntersect(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
            return false;
        int n = 0;
        ListNode cur1 = headA;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        ListNode cur2 = headB;
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        return cur1 == cur2;
	}
	
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 10);
		map.put(1, 11);
		System.out.println(map.get(1));
	}
	 
}
