package linkedListCycleII;

public class Solution {
	class ListNode {
		int val;
	    ListNode next;
		ListNode(int x) {
		   val = x;
		   next = null;
		}
	}
	public ListNode detectCycle(ListNode head) {
 		if(head == null || head.next == null)
            return null;
        ListNode worker = head.next;
        ListNode runner = head.next.next;
        //第一次worker按照一倍速度,runner按照两倍速度，判断是否有环
        while(runner != null && runner != worker){
            worker = worker.next;
            if(runner.next != null && runner.next.next != null)
            	runner = runner.next.next;
            else
                return null;
        } 
        if (runner == null)
            return null;
        //让runner回到头节点，worker在环后的b节点，满足a+b=kc,runner走a步，worker也走a步，来到环的头节点
        runner = head;
        while(runner != worker){
            runner = runner.next;
            worker = worker.next;
        }
        return runner;
    }
}
