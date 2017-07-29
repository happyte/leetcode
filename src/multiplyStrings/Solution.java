package multiplyStrings;

public class Solution {
	 public static String multiply(String num1, String num2) {
		 if(num1 == null || num2 == null || num1.length()==0 || num2.length()==0)  
		        return "";  
		    if(num1.charAt(0)=='0')  
		        return "0";  
		    if(num2.charAt(0)=='0')  
		        return "0";  
		    StringBuilder res = new StringBuilder();  
		    int num = 0;
		    for(int i=num1.length()+num2.length();i>0;i--){
		    	for(int j=Math.min(i-1, num1.length());j>0;j--){
		    		if(i-j<=num2.length()){
		    			System.out.println("乘数:"+(int)(num1.charAt(j-1)-'0')+"  "+(int)(num2.charAt(i-j-1)-'0'));
		    			num += (int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-j-1)-'0');
		    		}
		    	}
		    	System.out.println("num:"+num+" i:"+i);
		    	//防止最高为0,其余位为0可以加入，比如3006的0
		    	if(i!=1||num>0)
		    		res.append(num%10);
		    	num = num/10;
		    }
		    return res.reverse().toString(); 
	 }
	 
	 public static void main(String[] args) {
		System.out.println(multiply("6", "501"));
	}
}
