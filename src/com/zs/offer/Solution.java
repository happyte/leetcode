package com.zs.offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import dataStructure.ListNode;


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
	
	 
	public static void main(String[] args) {
//		System.out.println(NumberOf1Between1AndN_Solution(12));
//		System.out.println("==========================");
//		int[] test = new int[]{52,3,4,1};
//		System.out.println(PrintMinNumber(test));
//		System.out.println("==========================");
//		System.out.println(GetUglyNumber_Solution(5));
//		System.out.println("==========================");
//		System.out.println(FirstNotRepeatingChar("sPeYjppjOPHoiYdzlTUufOOzlnSudHuHzbWXzZnyPWrnkFfmQX"));
//		ListNode l1 = new ListNode(1);
//		ListNode l2 = new ListNode(2);
//		ListNode l3 = new ListNode(3);
//		ListNode l4 = new ListNode(6);
//		ListNode l5 = new ListNode(7);
//		l1.next = l2;
//		l2.next = l3;
//		l3.next = l4;
//		l4.next = l5;
//		
//		ListNode a1 = new ListNode(1);
//		ListNode a2 = new ListNode(2);
//		ListNode a3 = new ListNode(3);
//		ListNode a4 = new ListNode(6);
//		ListNode a5 = new ListNode(7);
//		a1.next = a2;
//		a2.next = a3;
//		a3.next = a4;
//		a4.next = a5;
//		ListNode listNode = FindFirstCommonNode(l1, a1);
//		System.out.println(listNode.val);
		int[] array = new int[]{1,3,3,3,3,4,5};
		System.out.println(GetLastOfK(array, 2, 0, array.length-1));
		System.out.println(GetFirstOfK(array, 2, 0, array.length-1));
		//System.out.println(GetNumberOfK(array, 3));
	}
}
