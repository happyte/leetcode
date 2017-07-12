package wangYiSpring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;



public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int n = Integer.parseInt(scanner.nextLine());
			String str = scanner.nextLine();
			System.out.println(xiaochu(n, str));
		}
	}
	
	private static long cpuTime(int n,long[] nums){
		if(n == 1)
			return nums[nums.length-1];
		int sum = 0;
		Arrays.sort(nums);
		ArrayList<Long> arr = new ArrayList<>();
		for(int i=0;i<n;i++){
			arr.add(nums[i]);
		}
		while(arr.size()>1){
			//如果最后一个数大于倒数第二个数
			if(arr.get(arr.size()-1) > arr.get(arr.size()-2)){
				long tmp = arr.get(arr.size()-1);//取出最后一个数
				tmp -= arr.get(arr.size()-2);//减去倒数第二个数
				arr.set(arr.size()-1, tmp); //设置成相减后的数
				sum += arr.get(arr.size()-2);
				arr.remove(arr.size()-2);  //移除倒数第二个数
			}
			//如果最后一个数小于倒数第二个数
			else{
				long tmp = arr.get(arr.size()-2);//取出倒数第二个数
				tmp -= arr.get(arr.size()-1);//减去最后一个数
				arr.set(arr.size()-2, tmp); //设置成相减后的数
				sum += arr.get(arr.size()-1);
				arr.remove(arr.size()-1);  //移除最后一个数
			}
			for(int i=0;i<arr.size();i++){
				System.out.print(arr.get(i)+" ");
			}
			System.out.println("sum:"+sum);
		}
		System.out.println("size:"+arr.size());
		sum += arr.get(0);
		return sum;
	}
	
	private static String xiaochu(int n,String num){
		String[] strs = num.split(" ");
		int[] nums = new int[n];
		for(int i=0;i<n;i++)
			nums[i] = Integer.parseInt(strs[i]);
		StringBuilder str = new StringBuilder();
		Set<Integer> set = new TreeSet<>();
		for(int i=0;i<n;i++){
			set.add(nums[i]);
		}
		//统计set中字符出现最后的位置
		Map<Integer, Integer> map = new HashMap<>();
		for(Integer value:set){
			int key = num.lastIndexOf(String.valueOf(value));
			int lastIndex = value;
			//System.out.println("key:"+key+" "+"value:"+lastIndex);
			map.put(key, lastIndex);
		}
		ArrayList<Integer> arr = new ArrayList<>();
		for(Map.Entry<Integer, Integer> entry:map.entrySet()){
			arr.add(entry.getKey());
		}
		Collections.sort(arr);
		for(int i=0;i<arr.size();i++){
			str.append(map.get(arr.get(i))+" ");
		}
		str.toString().trim();
		//不重复的元素都存在set中了
		return str.toString();
	}
}
