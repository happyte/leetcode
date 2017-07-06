package lengthOfLastWord;

public class Solution {
	public static int lengthOfLastWord(String s) {
		 if(s==null || s.length()==0)  
		        return 0; 
		 System.out.println(s.length());
		 int idx = s.length()-1;  
		 while(idx>=0 && s.charAt(idx)==' ') idx--;  
		 int idx2 = idx;  
		 while(idx2>=0 && s.charAt(idx2)!=' ') idx2--;  
		 return idx-idx2;
    }
	
	public static void main(String[] args) {
		String s = " ";
		System.out.println(lengthOfLastWord(s));
	}
}
