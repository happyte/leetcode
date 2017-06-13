package linkedListCycle;

public class Solution {
	class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
		  val = x;
		  next = null;
		}
	}
	public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode worker = head;
        ListNode runner = head;
        while(runner!= null && runner.next != null && runner.next.next != null){
            worker = worker.next;
            runner = runner.next.next;
            if(worker == runner)
                return true;
        }
        return false;
    }
	public static void main(String[] args) {
		int count = 0;
		char[] chs = Integer.toBinaryString(-13).toCharArray();
		for(int i = 0;i < chs.length; i++){
			if (chs[i] == '1')
				count += 1;
		}
		System.out.println("count"+count);
	}
}
