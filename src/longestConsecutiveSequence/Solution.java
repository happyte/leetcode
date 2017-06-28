package longestConsecutiveSequence;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Solution {
	public static int longestConsecutive(int[] num){
		if(num == null || num.length == 0)
			return 0;
		Set<Integer> set = new HashSet<>();
		//第一次扫描将数组中的元素都加入到set中
		for(int i=0; i<num.length; i++)
			set.add(num[i]);
		int result = 1;
		while(!set.isEmpty()){
			Iterator<Integer> iterator = set.iterator();
			int item = iterator.next();
			set.remove(item);
			//向小的深度搜索
			int length = 1;
			int i = item-1;
			while(set.contains(i)){
				set.remove(i);
				i--;
				length++;
			}
			int j = item+1;
			while(set.contains(j)){
				set.remove(j);
				j++;
				length++;
			}
			if(length > result)
				result = length;
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] num = new int[]{100,4,200,1,3,2};
		System.out.println(8%13);
	}
}
