package sortList;

import dataStructure.ListNode;

public class Solution {

	//归并排序
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }
    
	private ListNode mergeSort(ListNode head)  
	{  
    	if(head == null || head.next == null)  
        	return head;  
    	ListNode walker = head;  
    	if(walker.next!=null && walker.next.next!=null)  
    	{  
        	walker = walker.next;  
    	}  
    	ListNode head2 = walker.next;  
    	walker.next = null;  
    	ListNode head1 = head;  
    	head1 = mergeSort(head1);  
    	head2 = mergeSort(head2);  
    	return mergeTwoLists(head1, head2);  
	}  
    //两个有顺序链表的排序
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(0);    //新建一个虚指针，指向链表头
        ListNode pre = helper;       //pre指针始终指向l1的前一个节点，初始指向的就是helper
        helper.next = l1;           //虚指针指向表头
        while(l1 != null && l2 != null){
            // A链表节点的值大于B链表节点的值
            if(l1.val > l2.val){
                ListNode next = l2.next;
                l2.next = pre.next;
                pre.next = l2;
                l2 = next;
                
            }
            else{
                l1 = l1.next;
            }
            pre = pre.next;
        }
       if(l2 != null){
           pre.next = l2;
       }
       return helper.next;
    }
}
