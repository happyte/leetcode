package partitionList;


import dataStructure.ListNode;

public class Solution {
	
	public static ListNode partition(ListNode head, int x) {
		if(head == null)
			return null;
		if(head.next == null)
			return head;
		ListNode helper = new ListNode(0);
		ListNode preNode = helper;
		helper.next = head;
		//找到第一个大于x的位置,小于等于则一直往后遍历
		while(preNode.next != null && preNode.next.val < x){
			preNode = preNode.next;
		}
		System.out.println(preNode.val);
		//在链表的遍历过程中没有大于x的出现，不需要操作链表，直接返回
		if(preNode.next == null)
			return head;
		ListNode mNode = preNode.next;
		ListNode cur = mNode.next;
//		//链表的最后一个数字小于x
//		if(cur == null){
//			if(mNode.val < x){
//				mNode.next = helper.next;
//				helper.next = mNode;
//				preNode.next = cur;
//			}
//		}
		while(cur != null){
			ListNode next = cur.next;
			//比如2小于3的时候才需要移动链表
			if(cur.val < x){
				cur.next = preNode.next;
				preNode.next = cur;
				mNode.next = next;
				preNode = cur;  //preNode也需要移动位置
				cur = next;
			}
			else{
				mNode = mNode.next;
				cur = cur.next;
			}
		}
        return helper.next;
    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(4);
		ListNode l3 = new ListNode(3);
		ListNode l4 = new ListNode(2);
		ListNode l5 = new ListNode(5);
		ListNode l6 = new ListNode(2);
		l1.next = l2;
		l2.next = l3;
		l3.next = l4;
		l4.next = l5;
		l5.next = l6;
		
		ListNode head = partition(l1, 3);
		while(head != null){
			System.out.println(head.val);
			head = head.next;
		}
	}
}
