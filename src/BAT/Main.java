package BAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import dataStructure.ListNode;
import dataStructure.TreeNode;

//牛客网BAT算法练习
public class Main {
	/**
	 * @param source:目标串
	 * @param pattern:模式串
	 */
	private static boolean KMP(String source, String pattern){
		int[] N = getN(pattern);
		for(int i=0;i<N.length;i++)
			System.out.print(N[i]+" ");
		System.out.println("");
		int sourceLength = source.length();
		int patternLength = pattern.length();
		for(int i=0;i<=(sourceLength-patternLength);){
			String str = source.substring(i, i+patternLength);
			int count = getNext(pattern, str, N);
			if(count == 0){
				System.out.println("在i:"+i+"位置匹配成功");
				return true;
			}
			i+=count;
		}
		return false;
	}
	
	/**
	 * 返回下一次要移动的步数，移动步数:当前已经匹配的字符串－最后一位匹配的字符对应在Next表中的数字
	 * str和pattern是同样长度的
	 * @param pattern
	 * @param str
	 * @param N
	 * @return
	 */
	private static int getNext(String pattern,String str,int[] N){
		char[] v1 = str.toCharArray();
		char[] v2 = pattern.toCharArray();
		int n = str.length();
		int x = 0;
		while(n--!=0){
			if(v1[x]!=v2[x]){
				if(x == 0){
					return 1;
				}
				return x-N[x-1];
			}
			x++;
		}
		return 0;
	}
	
	/**
	 * 根据模式串返回next表
	 * @param pattern
	 * @return
	 */
	private static int[] getN(String pattern){
		char[] pat = pattern.toCharArray();
		int j = pat.length;
		int[] N = new int[j];
		for(int i=j;i>=2;i--){
			N[i-1] = getK(i, pat);
		}
		return N;
	}
	
	/**
	 * 计算next表中的某一位j，第一位不需要计算，肯定为0
	 * @param j
	 * @param pat
	 * @return
	 */
	private static int getK(int j, char[] pat){
		int x = j-2;
		int y = 1;
		while(x>=0&&compare(pat, 0, x, y, j-1)){
			x--;
			y++;
		}
		return ++x;
	}
	
	/**
	 * 比较字符串是否相同
	 * @param pat
	 * @param b1
	 * @param e1
	 * @param b2
	 * @param e2
	 * @return
	 */
	private static boolean compare(char[] pat,int b1,int e1,int b2,int e2){
		//e1-b1=e2-b2,最多需要比较e1-b1+1次
		int n = e1-b1+1;
		while(n--!=0){
			if(pat[b1]!=pat[b2])
				return true;
			b1++;
			b2++;
		}
		return false;
	}
	
	/**
	 * 字符串B是否为字符串A的变形
	 */
	public static boolean chkRotation(String A, int lena, String B, int lenb) {
		String C = A+A;
		if(KMP(C, B))
			return true;
        return false;
    }
	
	/**
	 * 字符串单词的逆序
	 */
	public static String reverseSentence(String A, int n) {
		//先对整个字符串逆序
		char[] strs = A.toCharArray();
		reverse(strs,0,n-1);
		int left = -1;
		int right = -1;
		for(int i=0;i<strs.length;i++){
			if(strs[i] != ' '){
				left = i==0||strs[i-1]==' '? i:left;
				right = i==strs.length-1||strs[i+1]==' '? i:right;
				//System.out.println("i:"+i+" left:"+left+" right:"+right);
			}
			if(left != -1&& right != -1) {
				reverse(strs, left, right);
				left = -1;
				right = -1;
			}
		}
		return String.valueOf(strs);
	}
	
	public static void reverse(char[] strs,int start,int end){
		while(start<end){
			char temp = strs[end];
			strs[end] = strs[start];
			strs[start] = temp;
			start++;
			end--;
		}
	}
	
	/**
	 * 对于一个字符串，请设计一个算法，将字符串的长度为len的前缀平移到字符串的最后。
	 */
	public static String stringTranslation(String A, int n, int len) {
		char[] strs = A.toCharArray();
		reverse(strs, 0, n-1);
		reverse(strs, 0, n-len-1);
		reverse(strs, n-len, n-1);
		return String.valueOf(strs);
	}
	
	/**
	 * 
	 */
	public static class MyComparator implements Comparator<String>{
		//o1+o2升序排列
		@Override
		public int compare(String o1, String o2) {
			return (o1+o2).compareTo(o2+o1);
		}
		
	}
	
	public static String findSmallest(String[] strs, int n) {
		StringBuilder sb = new StringBuilder();
		Arrays.sort(strs, new MyComparator());
		for(int i=0;i<n;i++)
			sb.append(strs[i]);
        return sb.toString();
    }
	
	/**
	 * 对于一个字符串,请设计一个高效算法，找到字符串的最长无重复字符的子串长度。
	 * map表中存储着上一次map表中字符的位置，pre以str[i－1]字符结尾的情况的最大不重复长度
	 */
	public static int longestSubstring(String A, int n) {
		char[] strs = A.toCharArray();
		int[] map = new int[256];
		for(int i=0;i<256;i++)
			map[i] = -1;
		int len = 0;
		int pre = -1;
		int cur = 0;
		for(int i=0;i<n;i++){
			//判断谁先出现在右边
			pre = Math.max(pre, map[strs[i]]);
			cur = i-pre;
			len = Math.max(len, cur);
			map[strs[i]] = i;
			System.out.println("pre:"+pre+" cur:"+cur+" len:"+len);
		}
		return len;
	}
	
	/**
	 * 现有两个升序链表，且链表中均无重复元素。请设计一个高效的算法，打印两个链表的公共值部分。
	 */
	public static int[] findCommonParts(ListNode headA, ListNode headB){
		LinkedList<Integer> list = new LinkedList<>();
		while(headA != null&&headB != null){
			if(headA.val < headB.val)
				headA = headA.next;
			else if (headA.val > headB.val)
				headB = headB.next;
			else {
				list.add(headA.val);
				headA = headA.next;
				headB = headB.next;
			}
		}
		int[] res = new int[list.size()];
		int i = 0;
		while(!list.isEmpty()){
			res[i++] = list.pollFirst();
		}
		return res;
	}
	
	/**
	 * 有一个单链表，请设计一个算法，使得每K个节点之间逆序，如果最后不够K个节点一组，则不调整最后几个节点。
	 */
	public static ListNode inverse(ListNode head, int k) {
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode pre = helper;
		ListNode cur = pre.next;
		int count = 0;
		while(cur != null){
			count++;
			ListNode next = cur.next;
			if(count == k){
				pre = reverse(pre, next);
				count = 0;
			}
			cur = next;
		}
		return helper.next;
    }
	
	public static ListNode reverse(ListNode pre,ListNode end){
		ListNode head = pre.next;
		ListNode cur = pre.next.next;
		while(cur != end){
			ListNode next = cur.next;
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
		head.next = end;
		return head;
	}
	
	/**
	 * 现在有一个单链表。链表中每个节点保存一个整数，再给定一个值val，把所有等于val的节点删掉。
	 */
	public static ListNode clear(ListNode head, int val) {
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode pre = helper;
		ListNode cur = pre.next;
		while(cur != null){
			ListNode next = cur.next;
			if(cur.val == val){
				pre.next = next;
				cur = next;
			}
			else {
				pre = cur;
				cur = next;
			}
		}
        return helper.next;
    }
	
	/**
	 * 请编写一个函数，检查链表是否为回文。
	 */
	public static boolean isPalindrome(ListNode pHead) {
		ListNode head = pHead;
		ListNode rHead = reverseList(head);
		while(pHead != null){
			System.out.print(pHead.val+" ");
			pHead = pHead.next;
		}
		System.out.println("");
		while(rHead != null){
			System.out.print(rHead.val+" ");
			rHead = rHead.next;
		}
		System.out.println("");
//		while(pHead != null&& rHead != null){
//			if(pHead.val != rHead.val)
//				return false;
//			else {
//				pHead = pHead.next;
//				rHead = rHead.next;
//			}
//		}
		return true;
    }
	
	public static ListNode reverseList(ListNode head){
		ListNode pre = null;
		ListNode cur = head;
		while(cur != null){
			ListNode next = cur.next;
			cur.next = pre;
			pre = cur;
			cur = next;
		}
		return pre;
	}
	
	/**
	 * 复杂链表的复制
	 */
	public static class RandomListNode {
	    int label;
	    RandomListNode next = null;
	    RandomListNode random = null;
	    RandomListNode(int label) {
	        this.label = label;
	    }
	}
	 
	public static RandomListNode Clone(RandomListNode pHead){
		if(pHead == null)
			return null;
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		RandomListNode newHead = new RandomListNode(pHead.label);
		map.put(pHead, newHead);
		RandomListNode pre = newHead;     //新链表头元素
		RandomListNode node = pHead.next; //旧链表头元素的下一个元素
		while(node != null){
			RandomListNode newNode = new RandomListNode(node.label);
			map.put(node, newNode);
			pre.next = newNode;  //新链表添加元素
			pre = newNode;
			node = node.next;
		}
		node = pHead;  //旧链表重新指向头节点
		RandomListNode copyNode = newHead;  //新链表指向头节点
		while(node != null){
			copyNode.random = map.get(node.random);  //新链表的随机节点指向旧链表的随机节点
			node = node.next;
			copyNode = copyNode.next;
		}
		return newHead;
	}
	
	/**
	 * 如何判断一个单链表是否有环？有环的话返回进入环的第一个节点的值，无环的话返回-1。
	 */
	public int chkLoop(ListNode head, int adjust) {
		if(head == null)
			return -1;
		ListNode walker = head;     //慢指针,每次走一步
		ListNode runner = head;     //快指针,每次走两步
		while(runner != null&& runner.next != null){
			walker = walker.next;
			runner = runner.next.next;
			if(walker == runner)
				break;
		}
		if(runner == null||runner.next == null)
			return -1;
		runner = head;
		while(runner != walker){
			runner = runner.next;
			walker = walker.next;
		}
		return walker.val;
    }
	
	/**
	 * 两个无环单链表，若两个链表的长度分别为m和n，请设计一个时间复杂度为O(n + m)，额外空间复杂度为O(1)的算法，判断这两个链表是否相交。
	 * 链表1先走n-m步，然后两个链表同步走，如果相交则会遇到一个公共
	 */
	public boolean chkIntersect(ListNode headA, ListNode headB) {
		if (headA == null || headB == null)
            return false;
        int n = 0;
        ListNode cur1 = headA;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        ListNode cur2 = headB;
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        return cur1 == cur2;
	}
	
	/**
	 * 在XxY的方格中，以左上角格子为起点，右下角格子为终点，每次只能向下走或者向右走，请问一共有多少种不同的走法
	 */
	public static int countWays(int x, int y) {
		boolean[][] visited = new boolean[x][y];
        return helper(0, 0, x-1, y-1, visited);
    }
	
	private static int helper(int startX,int startY,int endX,int endY,boolean[][] visited){
		int count = 0;
		if(startX==endX&&startY==endY)
			return ++ count;
		if(startX<=endX&&startY<=endY&&!visited[startX][startY]){
			visited[startX][startY] = true;
			count += helper(startX+1, startY, endX, endY, visited);
			count += helper(startX, startY+1, endX, endY, visited);
			visited[startX][startY] = false;
		}
		return count;
	}
	
	/**
	 *n个人站队，他们的编号依次从1到n，要求编号为a的人必须在编号为b的人的左边，但不要求一定相邻，请问共有多少种排法？
	 *第二问如果要求a必须在b的左边，并且一定要相邻，请问一共有多少种排法？
	 */
	public static int[] getWays(int n, int a, int b) {
        // write code here
		int[] res = new int[2];
		int sum = 1;
		for(int i=n;i>=2;i--)
			sum *= i;
		res[0] = sum/2;
		sum = 1;
		for(int i=n-1;i>=2;i--)
			sum *= i;
		res[1] = sum;
		return res;
    }
	
	/**
	 * n颗相同的糖果，分给m个人，每人至少一颗，问有多少种分法。
	 * Cn-1/m-1
	 */
	public static int getWays(int n, int m) {
		long sum = 1;
		for(int i=n-1;i>=n-m+1;i--)
			sum *= i;
		for(int i=m-1;i>=2;i--)
			sum /= i;
		return (int) sum;
	}
	
	/**
	 * 在一个n个人(其中编号依次为1到n)的队列中，他于其中的标号为b和标号c的人都有矛盾，
	 * 所以他不会和他们站在相邻的位置。现在问你满足A的要求的对列有多少种？
	 */
	public static int getWays(int n, int A, int b, int c) {
		//当A在两边时,2*(n-3)*(n-2)!
		//当A不在两边时 (n-2)*(n-3)*(n-4)*(n-3)!
		int leftSum = 2*(n-3);
		for(int i=n-2;i>=2;i--)
			leftSum *= i;
		int midSum = (n-2)*(n-3)*(n-4);
		for(int i=n-3;i>=2;i--)
			midSum *= i;
		return leftSum+midSum;
    }
	
	/**
	 * 卡塔兰数的常规表达式:C2n/n/n+1
	 * f(0)=1,f(1)=1,f(2)=2,f(3)=5
	 * f(n)=f(0)*f(n-1)+f(1)*f(n-1)+f(2)*f(n-3).....+f(n-1)*f(0)
	 */
	public static int countLegalWays(int n) {
		if(n == 1)
			return 1;
		int sum = 1;
		for(int i=2*n;i>=n+1;i--)
			sum *= i;
		for(int i=n;i>=2;i--)
			sum /= i;
		sum /= (n+1);
		return sum;
    }
	
	/**
	 * 给定一个二叉树的根结点root，请依次返回二叉树的先序，中序和后续遍历(二维数组的形式)。
	 */
	public static int[][] convert(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        preHelperII(root, list);
        int[][] res = new int[3][list.size()];
        for(int i=0;i<list.size();i++)
        	res[0][i] = list.get(i);
        list.clear();
        inHelperII(root, list);
        for(int i=0;i<list.size();i++)
        	res[1][i] = list.get(i);
        list.clear();
        postHelperII(root, list);
        for(int i=0;i<list.size();i++)
        	res[2][i] = list.get(i);
		return res;
    }
	
	//前序遍历，中左右
	private static void preHelper(TreeNode root,ArrayList<Integer> res){
		if(root == null)
			return;
		res.add(root.val);
		preHelper(root.left, res);
		preHelper(root.right, res);
	}
	
	//中序遍历，左中右
	private static void inHelper(TreeNode root,ArrayList<Integer> res){
		if(root == null)
			return;
		inHelper(root.left, res);
		res.add(root.val);
		inHelper(root.right, res);
	}
	
	//后序遍历，左右中
	private static void postHelper(TreeNode root,ArrayList<Integer> res){
		if(root == null)
			return;
		postHelper(root.left, res);
		postHelper(root.right, res);
		res.add(root.val);
	}
	
	//前序遍历非递归,一个栈模拟
	private static void preHelperII(TreeNode root,ArrayList<Integer> res){
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()){
			TreeNode cur = stack.pop();
			res.add(cur.val);
			if(cur.right != null)
				stack.push(cur.right);
			if(cur.left != null)
				stack.push(cur.left);
		}
	}
	
	//中序遍历非递归，一个栈模拟
	private static void inHelperII(TreeNode root,ArrayList<Integer> res){
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		TreeNode cur = stack.peek();
		while(!stack.isEmpty()){
			//当所有的左节点先压入栈中
			while(cur != null&&cur.left != null){
				stack.push(cur.left);
				cur = cur.left;
			}
			TreeNode node = stack.pop();
			res.add(node.val);
			cur = node.right;
			if(cur != null)
				stack.push(cur);
		}
	}
	
	//后序遍历非递归，两个栈模拟
	private static void postHelperII(TreeNode root,ArrayList<Integer> res){
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		stack1.push(root);
		while(!stack1.isEmpty()){
			TreeNode cur = stack1.pop();
			stack2.push(cur);
			if(cur.left != null)
				stack1.push(cur.left);
			if(cur.right != null)
				stack1.push(cur.right);
		}
		while(!stack2.isEmpty())
			res.add(stack2.pop().val);
	}
	
	//dp[i][j]表示用前0...i种前，总金额为j能组成的可能性
	public static int countWays(int[] penny, int n, int aim) {
		int[][] dp = new int[n][aim+1];
		for(int i=0;i<n;i++)
			dp[i][0] = 1;
		for(int j=1;j<=aim;j++){
			if(j%penny[0]==0)
				dp[0][j] = 1;
		}
		for(int i=1;i<n;i++){
			for(int j=1;j<=aim;j++){
				for(int k=0;k<=j/penny[i];k++){
					dp[i][j] = dp[i][j]+dp[i-1][j-k*penny[i]];
				}
			}
		}
		for(int i=0;i<n;i++){
			for(int j=0;j<=aim;j++)
				System.out.print(dp[i][j]+" ");
			System.out.println();
		}
		return dp[n-1][aim];
    }
	
	//最长递增子序列
	//dp[i]表示前i个的最长递增子序列,dp[i]
	public static int getLIS(int[] A, int n) {
		int[] dp = new int[n];
		int max = 0;
		Arrays.fill(dp, 1);
		for(int i=1;i<n;i++){
			for(int j=0;j<i;j++){
				if(A[j]<A[i]){
					dp[i] = Math.max(dp[j]+1, dp[i]);
					max = Math.max(max, dp[i]);
				}
			}
		}
		for(int i=0;i<n;i++)
			System.out.print(dp[i]+" ");
		System.out.println();
		return max;
    }
	
	public static void main(String[] args) {
		int[] A = new int[]{395,132,276,31,612,103,209,105,214,541,454,87,600,385,345,393,45,154,70,101,468,496,253,181,162,605,425,588,74,261,155,58,549,378,535,217,213,35,564,204,193,301,78,470,140,566,315,162,471,80,451,208,402,80,224,375,279,567,272,39,495,622,256,396,452,141,344,586,310,506,348,481,388,599,412,105,75,338,71,149,19,317,23,8,592,452,624,395,412,12,303,207,491,466,238,94,538,478,163,624,308,271,18,417,209,83,18,113,169,521,539,242,36,180,429,360,203,164,580,198,98,119,157,249,609,93,323,592,105,573,243,132,25,208,505,141,454,83,199,279,464,96,285,239,24,299,484,562,410,285,421,280,63,288,502,503,55,615,395,115,560,218,165,224,536,556,201,573,167,248,541,539,35,112,56,326,138,362,91,14,531,539,291,497,570,171,615,318,586,354,462,31,199,297,589,86,257,618,591,59,532,199,302,195,587,51,87,504,62,403,513,33,86,166,576,51,201,254,343,422,388,604,305,511,388,403,564,534,466,423,42,92,146,435,613,92,239,455,614,332,176,218,60,432,584,205,323,170,320}; 
		System.out.println(getLIS(A, A.length));
	}
	 
}
