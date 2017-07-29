package xiaozhaoIV;

import java.util.Scanner;

public class Solution {
	
	//dp[i]代表输入为i有多少种组合
	//1.如果i是素数，那么dp[i]=2*dp[i-1] 
	//2.i不是素数的幂次，例如6，dp[i]=dp[i-1]
	//3.i是素数的幂次，例如8是4，2的幂次，(2,4)有3种组合，(2,4,8)有4种组合，只有之前全为Y的组合到下一个才能组合成两种
	//例如2,4的(Y,Y)到2,4,8的(Y,Y,Y)或(Y,Y,N)
    final static int MOD = (int) (1E9+7);
    private static long caishu(int n) {
    	long count = 1;
        if(n == 1)
        	return count;
        boolean[] visited = new boolean[n+1];
        for(int i=2;i<=n;i++){
        	int ans = 0;
        	if(visited[i])
        		continue;
        	for(int j=i+i;j<=n;j+=i){
        		visited[j]=true;
        	}
        	long mi=i;
        	while(mi<=n){
        		ans++;
        		mi *= i;
        	}
        	count = count*(ans+1)%MOD;
        }
        return count;
    }
	
	//最少组合的回文串个数，其实就是个求奇次字符出现的个数
	public static int huiwen(String str){
		int count = 0;
		if(str == null||str.length()==0)
			return count;
		int[] ans = new int[26];
		for(int i=0;i<str.length();i++){
			ans[str.charAt(i)-'a']++;
		}
		for(int j=0;j<ans.length;j++){
			if(ans[j]%2==1)
				count++;
		}
		return count;
	}
	
	//偶串
	public static boolean ouchuan(String str){
		if(str==null||str.length()==0||str.length()%2==1)
			return false;
		int length = str.length()/2;
		if(str.substring(0, length).equals(str.substring(length, str.length()))){
			System.out.println(str.substring(0, length));
			System.out.println(str.substring(length, str.length()));
			return true;
		}
		return false;
	}
	
	public static int deleteou(String str){
		int count = 0;
		if(str==null||str.length()==0)
			return count;
		String temp = str;
		while(temp.length()!=0){
			temp = temp.substring(0, temp.length()-1);
			count++;
			if(ouchuan(temp)){
				System.out.println("temp:"+temp);
				return str.length()-count;
			}
		}
		return 0;
	}
	
	//求最长DNA子串
	public static int DNA(String str){
		int count = 0;
		if(str==null || str.length() == 0)
			return count;
		for(int i=0;i<str.length();i++){
			int temp = 0;
			while(str.charAt(i) == 'A'||str.charAt(i) == 'T'||str.charAt(i) == 'C'||str.charAt(i) == 'G'){
				temp++;
				i++;
				if(i>str.length()-1)
					break;
			}
			count = Math.max(count, temp);
		}
		return count;
	}
	
	//需要换掉的瓷砖数量 
	public static int change(String str){
		int count = 0;
		if(str==null || str.length() == 0)
			return count;
		String[] s = new String[str.length()];
		for(int i=0;i<str.length();i++){
			s[i] = String.valueOf(str.charAt(i));
		}
		for(int i=1;i<str.length();i++){
			if(s[i].equals(s[i-1])){
				count++;
				s[i] = "A";
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String string = scanner.nextLine();
			System.out.println(caishu(Integer.valueOf(string)));
		}
	}
}
