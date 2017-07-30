package tengxun;

import java.util.HashMap;
import java.util.Scanner;

public class Solution {
	//一种编码格式
	public static int bianma(String code){
		int[] factor = new int[]{25*25*25+25*25+25+1,25*25+25+1,25+1,1};
		int len= code.length();
	    int index=len-1;
	    for(int i=0;i<len;++i)
	    {
	        index+=factor[i]*(code.charAt(i)-'a');
	    }
	    return index;
	}
	
	//判断是否为素数
	public static boolean isPrime(int num){
		if(num == 0 || num == 1)
			return false;
		if(num == 2)
			return true;
		int key = (int) Math.sqrt(num);
		for(int i=key;i>=2;i--){
			if(num%i == 0)
				return false;
		}
		return true;
	}
	
	//有多少个质数对
	public static int sushudui(int num){
		int count = 0;
		int length = (num%2==1? num/2+1:num/2);
		for(int i=num;i>=length;i--){
			if (isPrime(i)) {
				if (isPrime(num-i)) {
					System.out.println("第一个数:"+i+" 第二个数:"+(num-i));
					count++;
				}
			}
		}
		return count;
	}
	
	//genhash编码
	public static String genhash(int num){
		//6位精度
		int left = -90;
		int right = 90;
		int middle = 0;
		StringBuilder str = new StringBuilder();
		for(int i=0;i<6;i++){
			//左区间
			if(num>=left&&num<middle){
				right = middle;
				middle = (left+right)/2;
				str.append("0");
			}
			//右区间
			else if (num>=middle&&num<=right) {
				left = middle;
				middle = (left+right)/2;
				str.append("1");
			}
		}
		return str.toString();
	}
	
	//任务标记，觉得这道题有点奇怪
	public static int renwu(int num1,int num2){
		HashMap<Integer, Boolean> record = new HashMap<>();
		if(num1>1024||num1<1||num2>1024||num2<1)
			return -1;
		record.put(num1, true);
		if(record.get(num2) != null){
			if(record.get(num2) == true)
				return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			System.out.println(renwu(scanner.nextInt(), scanner.nextInt()));
		}
	}
}
