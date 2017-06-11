package insertionSort;

public class Solution {
	 public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
		   val = x;
		   next = null;
	     }
	 }
	 
	 public ListNode insertionSortList(ListNode head) {
	        if(head == null || head.next == null)
	            return head;
	        ListNode helper = new ListNode(0);
	        ListNode pre = helper;
	        ListNode cur = head;
	        while(cur != null){
	            ListNode next = cur.next;   //记录下当前的节点的下一个节点
	            pre = helper;   //每次都将pre指针指向链表头head前的helper虚指针
	            //第一次进来pre.next为null
	            while(pre.next != null && pre.next.val < cur.val){
	                pre = pre.next;   //遍历找到合适的位置
	            }
	            cur.next = pre.next; //第一次进来第一个节点的next为null
	            pre.next = cur; //第一次helper=pre，两者在内存中是同一地址,next属性指向第一次进来的第一个节点
	            cur = next;
	        }
	        return helper.next;
	    }
}
