package reverseLinkedListII;

import dataStructure.ListNode;

//反转从m到n的链表
public class Solution {
	public ListNode reverseBetween(ListNode head, int m, int n) {  
	    if(head == null)  
	        return null;  
	    ListNode dummy = new ListNode(0);  
	    dummy.next = head;  
	    ListNode preNode = dummy;  
	    int i=1;  
	    while(preNode.next!=null && i<m)  
	    {  
	        preNode = preNode.next;  
	        i++;  
	    }  
	    if(i<m)  
	        return head;  
	    ListNode mNode = preNode.next;  
	    ListNode cur = mNode.next;  
	    while(cur!=null && i<n)  
	    {  
	        ListNode next = cur.next;  
	        cur.next = preNode.next;  
	        preNode.next = cur;  
	        mNode.next = next;  
	        cur = next;  
	        i++;  
	    }  
	    return dummy.next;  
	}  
}
