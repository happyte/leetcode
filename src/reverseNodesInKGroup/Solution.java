package reverseNodesInKGroup;

import dataStructure.ListNode;

public class Solution {
	 public static ListNode reverseKGroup(ListNode head, int k) {
		 if(head == null)
	            return null;
	        ListNode helper = new ListNode(0);
	        helper.next = head;
	        ListNode pre = helper;
	        ListNode cur = pre.next;
	        int count = 0;
	        while(cur!=null){
	            count++;
	            ListNode next = cur.next;
	            if(count == k){
	                pre = reverse(pre,next);
	                count = 0;
	            }
	            cur = next;
	        }
	        return helper.next; 
	 }
	 
	 private static ListNode reverse(ListNode pre,ListNode end){
	        if(pre == null || pre.next == null)
	            return pre;
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
	 
	 public static void main(String[] args) {
		 ListNode l1 = new ListNode(1);
		 ListNode l2 = new ListNode(2);
		 ListNode l3 = new ListNode(3);
		 ListNode l4 = new ListNode(4);
		 ListNode l5 = new ListNode(5);
		 l1.next = l2;
		 l2.next = l3;
		 l3.next = l4;
		 l4.next = l5;
		 ListNode head = reverseKGroup(l1, 2);
		 while(head != null){
			System.out.println(head.val);
			head = head.next;
		 }
	}
}
