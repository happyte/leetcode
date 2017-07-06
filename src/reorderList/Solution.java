package reorderList;

public class Solution {
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
		   val = x;
		   next = null;
		}
	}
	public void reorderList(ListNode head) {
        if(head == null || head.next==null)  
    	{  
        	return;  
    	}  
    	ListNode walker = head;  
    	ListNode runner = head;  
    	while(runner.next!=null && runner.next.next!=null)  
    	{  
        	walker = walker.next;  
        	runner = runner.next.next;  
    	}  
    	ListNode head1 = head;  
    	ListNode head2 = walker.next;  
    	walker.next = null;  
    	head2 = recursive_reverse(head2);  
    	while(head1!=null && head2!=null)  
    	{  
        	ListNode next = head2.next;  
        	head2.next = head1.next;  
        	head1.next = head2;  
        	head1 = head2.next;  
        	head2 = next;  
    	}  
    }
	//非递归方式反转链表
	private ListNode reverse(ListNode head){
		ListNode pre = null;
		ListNode cur = head;
		ListNode next = head.next;
		while(cur != null){
			next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}
	//递归方式的反转链表
	public ListNode recursive_reverse(ListNode head) {  
    	if(head == null || head.next==null)  
        	return head;  
    	return recursive_reverse(head, head.next);  
	}
    private ListNode recursive_reverse(ListNode current, ListNode next)   
	{  
    	if (next == null) return current;  
    	ListNode newHead = recursive_reverse(current.next, next.next);  
    	next.next = current;  
    	current.next = null;  
    	return newHead;  
    	
    	
//      if(runner == null)  
//      {  
//          n %= idx;  
//          runner = head;  
//          idx=0;  
//          while(runner!=null && idx<n)  
//          {  
//              runner = runner.next;  
//              idx++;  
//          }  
//      }  
//      while(runner.next!=null)  
//      {  
//          walker = walker.next;  
//          runner = runner.next;  
//      }  
//      runner.next = head;  
//      ListNode newHead = walker.next;  
//      walker.next = null;  
//      return newHead;
	}  
}
