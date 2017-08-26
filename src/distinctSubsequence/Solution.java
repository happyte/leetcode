package distinctSubsequence;

//S字符串中有多少个子序列与T相等,S的自序列不一定是连续的
public class Solution {
	public static int numDistince(String S,String T){
		int[] res = new int[T.length()+1];
		res[0] = 1;
		for(int i=0;i<S.length();i++){
			for(int j=T.length()-1;j>=0;j--){
				res[j+1]=(S.charAt(i)==T.charAt(j)?res[j]:0)+res[j+1];
			}
		//	System.out.println(res[0]+","+res[1]+","+res[2]+","+res[3]+","+res[4]+","+res[5]+","+res[6]);
		}
		return res[T.length()];
	}
	
	public static void main(String[] args) {
		System.out.println(numDistince("rabcbit", "rabit"));
	}
}
