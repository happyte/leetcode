package wildCardMatching;

//正则匹配
public class Solution {
	public static boolean matchPattern(String s,String p){
		if(s==null||s.length()==0||p==null||p.length()==0)
			return false;
		boolean res[] = new boolean[s.length()+1];
		res[0] = true;
		for(int j=0;j<p.length();j++){
			if(p.charAt(j) != '*'){
				for(int i=s.length()-1;i>=0;i--){
					//如果是问号或者两者相等，则当前该字符是匹配的，如果res[i]为true的话，res[i+1]也为true
					res[i+1] = res[i]&(p.charAt(j)=='?'||p.charAt(j)==s.charAt(i));
				}
			}
			//如果该字符为*
			else{
				int i=0;
				while(i<s.length()&&!res[i])
					i++;
				for(;i<s.length();i++)
					res[i+1]=true;
			}
			res[0] = res[0]&(p.charAt(j)=='*');
			for(int k=0;k<=s.length();k++){
				System.out.print(res[k]+" ");
			}
			System.out.println(" ");
		}
		return res[s.length()];
	}
	
	public static void main(String[] args) {
		System.out.println(matchPattern("200-paid-done", "200-?*-done"));
	}
}
