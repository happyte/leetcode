package PlusOne;

public class Solution {
	//这道题是进位操作
	public static int[] plusOne(int[] digits) {
		if(digits == null || digits.length == 0)
			return digits;
		int carry = 1;  
		for(int i=digits.length-1;i>=0;i--){
			int digit = (digits[i]+carry)%10;
			carry = (digits[i]+carry)/10;
			digits[i] = digit;    //进位后的结果
			//没有发生进位
			if(carry == 0)
				return digits;
		}
		int[] res = new int[digits.length+1];
		res[0] = 1;
		return res;
	}
	
	public static void main(String[] args) {
		int[] A = new int[]{9,9,9,9,9};
		A = plusOne(A);
		for(int i=0;i<A.length;i++)
			System.out.print(A[i]+",");
	}
}
