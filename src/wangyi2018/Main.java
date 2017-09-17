package wangyi2018;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static String pipei(ArrayList<Integer> num,int n){
		int number = num.get(0);
		while(num.size()>1){
			boolean flag = false;
			int i = 1;
			for(;i<num.size();i++){
				if((number*num.get(i))%4==0){
					flag = true;
					break;
				}
			}
			if(!flag)
				return "No";
			number = num.get(i);
			num.set(0, number);
			num.remove(i);
		}
		return "Yes";
	}
	
	public static void solution(int[] array){
		if(array==null || array.length==0){
			System.out.println("No");
		}
		int num4 = 0;
		int num2 = 0;
		int num1 = 0;
		for(int i=0;i<array.length;i++){
			if(array[i] % 4 == 0){
				System.out.println("array[i]:"+array[i]);
				num4++;
			}
			else if(array[i] % 2 == 0){
				num2++;
			}
			else{
				num1++;
			}
		}
		System.out.println("num4:"+num4+" num2:"+num2+" num1:"+num1);
		//这里的判断是重点()
		if(num4 >= num1){
			System.out.println("Yes");
		}else{
			if(num2 == 0)
				System.out.println("No");
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			int cishu = scanner.nextInt();
			for(int i=0;i<cishu;i++){
				int n = scanner.nextInt();
				int[] num = new int[n];
				for(int j=0;j<n;j++)
					num[j] = scanner.nextInt();
				solution(num);
			}
		}
	}
}
