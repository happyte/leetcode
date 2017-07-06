package rotateList;

import dataStructure.ListNode;

public class Solution {
	//把链表的倒数k个节点反转到链表开头
	public static ListNode rotateRight(ListNode head, int n) {
		if(head == null)    
            return null;  
        ListNode walker = head;  
        ListNode runner = head;  
        int idx = 0; 
        //求出链表长度
        while(runner!=null){  
            runner = runner.next;  
            idx++;  
        }  
        //n的长度大于链表，则n对链表长度取余
        if(n >= idx)
        	n = n%idx;
        if(n == 0)
        	return head;
        //求出倒数第n个节点所在位置
        runner = head;
        idx = 0;
        //先求出正数第n个节点
        while(runner != null && idx<n){
        	idx++;
        	runner = runner.next;
        }
        //runner和walker同时开始跑
        while(runner.next != null){
        	runner = runner.next;
        	walker = walker.next;
        }
        ListNode preNode = walker;
        ListNode newNode = walker.next;
        ListNode lastNode = runner;
        lastNode.next = head;
        preNode.next = null;
        return newNode;
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
		ListNode head = rotateRight(l1, 0);
		while(head != null){
			System.out.println(head.val);
			head = head.next;
		}
	}
}
