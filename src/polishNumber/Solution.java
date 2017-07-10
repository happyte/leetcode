package polishNumber;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
		System.out.println(polishNumber(line.trim()));
	}
	
	public static int polishNumber(String str){
		//空格符隔开
		String[] strs = str.split(" ");
		Stack<Integer> stack = new Stack<>();
		for(int i=0;i<strs.length;i++){
			//暂时先不处理非法的输入
			if(strs[i].equals("*")){
				//连续出栈两数相乘，再压栈
				stack.push(stack.pop()*stack.pop());
			}
			else if(strs[i].equals("+")){
				stack.push(stack.pop()+stack.pop());
			}
			else if(strs[i].equals("^")){
				int num = stack.pop();
				stack.push(++num);
			}
			else{
				//严格一点判断，除了数字之外的其它都不可压入.+至少匹配一次,?是0或1次
				if(strs[i].matches("\\d+$"));{
				//数字压入栈中
					stack.push(Integer.parseInt(strs[i]));
				}
			}
		}
		return stack.pop();
	}
}
