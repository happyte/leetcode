package deleteDuplication;

import dataStructure.ListNode;

//删除链表重复的节点
public class Solution {
	public static ListNode deleteDuplication(ListNode pHead)
    {    
        if (pHead == null) 
        	return null;
        ListNode helper = new ListNode(0);
        helper.next = pHead;
        ListNode p = pHead;
        while(p != null){
        	ListNode q = p.next;
        	if(q == null) break;
        	//p,q两者值相同
        	if(p.val == q.val){
        		while(q != null && q.val == p.val){
            		q = q.next;
            	}
        		p.next = q;
        	}
        	p = q;
        }
        return helper.next;
    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(1);
		ListNode l3 = new ListNode(2);
		ListNode l4 = new ListNode(3);
		ListNode l5 = new ListNode(3);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		ListNode head = deleteDuplication(l1);
		while(head != null){
			System.out.println(head.val);
			head = head.next;
		}
	}
}
