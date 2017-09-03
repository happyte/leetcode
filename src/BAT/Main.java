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
	
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(4);
		ListNode l5 = new ListNode(3);
		ListNode l6 = new ListNode(2);
		ListNode l7 = new ListNode(1);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		l6.next = l7;
		ListNode r1 = new ListNode(2);
		ListNode r2 = new ListNode(4);
		ListNode r3 = new ListNode(6);
		ListNode r4 = new ListNode(8);
		ListNode r5 = new ListNode(10);
		r1.next = r2;
		r2.next = r3;
		r3.next = r4;
		r4.next = r5;
		System.out.println(isPalindrome(l1));
//		ListNode l = reverseList(l1);
//		while(l != null){
//			System.out.println(l.val);
//			l = l.next;
//		}
	}
	 
}
