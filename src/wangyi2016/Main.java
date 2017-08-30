package wangyi2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dataStructure.TreeNode;

public class Main {
	//2,3,[[1,2],[2,4],[1,3],[4,3]],4
	//实际上就是个深搜
	public static int cmp(int g1, int g2, int[][] records, int n) {
		int max = 0;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int[] pair:records){
			if(map.containsKey(pair[0])){
				List<Integer> list = map.get(pair[0]);
			    list.add(pair[1]);
			    map.put(pair[0], list);
			}
			else {
				List<Integer> list = new ArrayList<>();
				list.add(pair[1]);
				map.put(pair[0], list);
			}
			int temp = Math.max(pair[0], pair[1]);
			max = Math.max(max, temp);
		}
		++max;
		boolean[] visitedForward = new boolean[max];
		boolean[] visitedBackward = new boolean[max];
		if(shensou(g1, g2, map, visitedForward))
			return 1;
		else if(shensou(g2, g1, map, visitedBackward))
			return -1;
        return 0;
    }
	
	public static boolean shensou(int source,int tartget,Map<Integer, List<Integer>> map,boolean[] visited){
		if(source == tartget)
			return true;
		if(map.get(source) == null)
			return false;
		visited[source] = true;
		List<Integer> list = map.get(source);
		for(int i=0;i<list.size();i++){
			int next = list.get(i);
			if(visited[next])
				continue;
			else if (shensou(next, tartget, map, visited)) {
				return true;
			}
		}
		return false;
	}
	
	public static int findKth(int[] a, int n, int K) {
		return findK(a, 0, a.length-1, K);
	}
	
	public static int findK(int[] num,int low,int high,int K){
		if(low==high)
			return num[low];
		int privot = partition(low, high, num);
		int kp = privot+1;
		if(kp == K)
			return num[privot];
		else if(K<kp)
			return findK(num, low, privot-1, K);
		else 
			return findK(num, privot+1, high, K);
	}
	
	//找出枢轴应所在位置
	public static int partition(int low ,int high, int[] num){
		int temp = num[low];
		int i = low;
		int j = high;
		while(i<j){
			//右边找大的放在前面
			while(i<j&&num[j]<=temp){
				j--;
			}
			if(i<j){
				num[i]=num[j];
			}
			//左边找小的放到后面
			while(i<j&&num[i]>temp){
				i++;
			}
			if(i<j){
				num[j]=num[i]; 
			}
		}
		num[i] = temp;
		return i;
	}
    
    //请设计一个算法算出权值最大的叶节点到权值最小的叶节点的距离
    public static int getDis(TreeNode root) {
    	//自下而上记录(左节点，根节点)，(右节点，根节点)
    	Map<Integer, Integer> map = new HashMap<>();
    	helper(root, map);
    	List<Integer> paht1 = getRoute(new ArrayList<>(), map, min);
    	List<Integer> path2 = getRoute(new ArrayList<>(), map, max);
    	for(int i=paht1.size()-1,j=path2.size()-1;i>=0&&j>=0;i--,j--){
    		if(paht1.get(i) == path2.get(j)){
    			return i+j;
    		}
    	}
        return 0;
    }  
    
    private static ArrayList<Integer> getRoute(ArrayList<Integer> list,Map<Integer, Integer> map,int i){
    	while(map.containsKey(i)){
    		list.add(i);
    		i = map.get(i);
    	}
    	list.add(i);
    	return list;
    }
    
    private static int min = Integer.MAX_VALUE,max = Integer.MIN_VALUE;
    
    private static void helper(TreeNode root,Map<Integer, Integer>map){
    	if(root == null)
    		return;
    	//叶子节点,计算权值最大的叶子节点和权值最小的叶子节点
    	if(root.left == null&&root.right == null){
    		int val = root.val;
    		min = Math.min(min, val);
    		max = Math.max(max, val);
    	}
    	if(root.left != null){
    		map.put(root.left.val, root.val);
    		helper(root.left, map);
    	}
    	if(root.right != null){
    		map.put(root.right.val, root.val);
    		helper(root.right, map);
    	}
    }
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);  
        TreeNode left = new TreeNode(4);  
        TreeNode right = new TreeNode(6);  
        root.left = left;  
        root.right = right;  
        TreeNode r2 = new TreeNode(8);  
        right.right = r2;  
//        TreeNode l2 = new TreeNode(2);
//        left.left = l2;
        System.out.println(getDis(root));
	}
	
}
