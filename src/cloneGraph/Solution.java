package cloneGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import dataStructure.UndirectedGraphNode;

public class Solution {
	//对图的克隆
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		if(node == null)
			return null;
		//用于记录已有的图中的顶点
		LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
		queue.offer(node);
		UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
		map.put(node, copy);
		while(!queue.isEmpty()){
			UndirectedGraphNode cur = queue.poll();
			for(int i=0;i<cur.neighbors.size();i++){
				if(!map.containsKey(cur.neighbors.get(i))){
					queue.offer(cur.neighbors.get(i));
					copy = new UndirectedGraphNode(cur.neighbors.get(i).label);
					map.put(cur.neighbors.get(i), copy);
				}
				map.get(cur).neighbors.add(cur.neighbors.get(i));
			}
		}
        return map.get(node);
    }
}
