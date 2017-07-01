package deleteDuplicationII;

import dataStructure.ListNode;

public class Solution {
	
	 public static ListNode deleteDuplicates(ListNode head) {
		 if(head == null)
			 return null;
		 ListNode helper = new ListNode(0);
		 helper.next = head;
		 ListNode pre = helper;
		 ListNode p = head;
		 while(p != null){
			 ListNode q = p.next;
			 if(q == null) break;
			 if(p.val == q.val){
				 while(q != null && p.val == q.val){
					 q = q.next;
				 }
				 pre.next = q;
				 p = q;
			 }
			 else {
				pre = p;
				p = q;
			}
		 }
		 return helper.next;
	 }
	 
	 public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(2);
		l1.next = l2;
		ListNode head = deleteDuplicates(l1);
		while(head != null){
			System.out.println(head.val);
			head = head.next;
		}
	}
}
