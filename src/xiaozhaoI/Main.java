package xiaozhaoI;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;


public class Main {
	//放入的鱼的范围是[minSize,maxSize],鱼缸里面已经有的鱼是num,鱼的范围在[2,10]是会被吃掉的
	public static int fangyu(int minSize,int maxSize,int[] num){
		boolean[] visited = new boolean[maxSize+1];
		for(int i=0;i<num.length;i++){
			//对当前鱼缸里面的每条鱼进行分析，哪些大小的鱼是不能放了
			int temp = num[i];
			for(int j=2;j<=10;j++){
				if(temp*j>=minSize&&temp*j<=maxSize){
					visited[temp*j] = true;
				}
			}
		}
		int count = 0;
		for(int i=minSize;i<=maxSize;i++){
			if(!visited[i])
				count++;
		}
		return count;
	}
	
	public static int xunhuan(String[] strs){
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0;i<strs.length;i++){
			String str = strs[i];
			if(i == 0){
				map.put(str, 1);
				continue;
			}
			int n = str.length();
			String ori = str;
			System.out.println("原来的的str:"+str);
			boolean flag = false;
			for(int j=0;j<n;j++){
				//对除了第一个数外的每个数判断其是否为map中已经存在的循环单词
				str = str.substring(1, n)+str.substring(0, 1);
				System.out.println("移动后的str:"+str);
				if(map.containsKey(str)){
					flag = true;
					int count = map.get(str);
					map.put(str, ++count);
				}
			}
			//是循环字母
			System.out.println("flag:"+flag);
			if(!flag){
				map.put(ori, 1);
			}
		}
		return map.size();
	}
	
	public static int DNA(String s1,String s2){
		//记录两条链在相同位置的字符是否是配对的,比如s1的A,s2的T就是配对的
		int count = 0;
		int n = s1.length();
		for(int i=0;i<n;i++){
			String str = s1.substring(i, i+1)+s2.substring(i, i+1);
			if(str.equals("AT")||str.equals("TA")||str.equals("CG")||str.equals("GC"))
				count++;
		}
		return s1.length()-count;
	}
	
	public static void modiao(int n,int[] num){
		//被抹掉的数如果出现在中间，那么只会出现一次相邻两个数间隔为2
		Arrays.sort(num);
		int index = -1;
		int count = 0;
		for(int i=1;i<n;i++){
			int cha = num[i]-num[i-1];
			if(cha>2){
				System.out.println("mistake");
				return;
			}
			else if(cha == 2){
				count++;
				index = i;
			}
		}
		if(index >= 0){
			if(count>1)
				System.out.println("mistake");
			else
				System.out.println(num[index]-1);
			return;
		}
		if(num[0] == 1){
			System.out.println(num[n-1]+1);
			return;
		}
		System.out.print(num[0]-1+" ");
		System.out.println(num[n-1]+1);
	}
	
	//复杂度太大
	//一个数字能表示为p^q(^表示幂运算)且p为一个素数,q为大于1的正整数就称这个数叫做超级素数幂,27,3,3
	public static String sushumi(long n){
		StringBuffer sb = new StringBuffer();
		int index = -1;
		for(int i=2;i<=n/2;i++){
			long temp = n;
			//i为素数
			if(isSuShu(i)){
				while(temp%i==0){
					temp = temp/i;
					if(temp==1){
						index = i;
						break;
					}
				}
			}
			if(index != -1)
				break;
		}
		if(index == -1)
			return "No";
		sb.append(index).append(" ");
		int count = 0;
		while(n!=1){
			n=n/index;
			count++;
		}
		return sb.append(count).toString();
	}
	
	public static boolean isSuShu(int n){
		for(int i=2;i<=Math.sqrt(n);i++){
			if(n%i==0)
				return false;
		}
		return true;
	}
	
	//找出一段长度大于等于L的连续非负整数，他们的和恰好为N
	//20 2+3+4+5+6  20/5=4
	//18 5+6+7      18/3=6
	//17 8+9        
	public static String xulie(long N,int L){
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for(int n=L;n<=100;n++){
			if((2*N-n*n+n)%(2*n)==0){
				int a1 = (int) ((2*N-n*n+n)/(2*n));
				for(int j=0;j<n;j++){
					int temp = a1+j;
					if(j==n-1){
						sb.append(temp);
						flag = true;
						break;
					}
					else {
						sb.append(temp+" ");
					}
				}
				if(flag)
					break;
			}
		}
		if(!flag)
			return "No";
		return sb.toString();
	}
	
	public static String zucheng(String str){
		StringBuffer sb = new StringBuffer();
		int n = str.length();
		//数字0-9
		int[] shuzi = new int[10];
		//9 1  99 1*9+10＋1=20 999 20*9+100＋20=300  (数字9)
		//0 0  99 (0+1+0)*9+0  999  999 (9+2+9)*9+9=189  9999 (189+3+189)*9+189	(数字0)
		int jinzhi = 1;
		for(int i=0;i<str.length();i++){
			for(int j=0;j<=str.charAt(i)-'0';j++){
				if(j==0){
					shuzi[0] += (2*shuzi[0]+i)*(str.charAt(i)-'0');
 				}
				else {
					shuzi[j] = shuzi[j]*(str.charAt(i)-'0')+jinzhi+shuzi[j];
				}
			}
			jinzhi *= 10;
		}
		for(int i=0;i<shuzi.length;i++){
			if(i == shuzi.length-1)
				sb.append(shuzi[i]);
			else
				sb.append(shuzi[i]+" ");
		}
		return sb.toString();
	}
	
	public static int pick(int[] peaches) {
		int l = peaches.length;
		int[] dp = new int[l];
		//无论如何，最长递增最小长度肯定为1，因为就是当前项本身
		Arrays.fill(dp, 1);
		for(int i=1;i<l;i++){
			for(int j=0;j<i;j++){
				if(peaches[j]<peaches[i]){
					dp[i] = Math.max(dp[j]+1, 1);
				}
			}
		}
		return dp[l-1];
	}

	
	public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int trees = Integer.parseInt(in.nextLine().trim());
        int[] peaches = new int[trees];
        for (int i = 0; i < peaches.length; i++) {
            peaches[i] = Integer.parseInt(in.nextLine().trim());
        }
        System.out.println(pick(peaches));
    }
}
