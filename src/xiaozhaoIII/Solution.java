package xiaozhaoIII;

import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static int bianhuan(long num){
		int count = 0;
		while(num>=10){
			String str = String.valueOf(num);
			num = 1;
			for(int i=0;i<str.length();i++){
				num *= (str.charAt(i)-'0');
			}
			System.out.println("num:"+num);
			count++;
		}
		return count;
	}
	
	public static int shenqishucount(int a,int b){
		int count = 0;
		for(int i=a;i<=b;i++){
			if(shenqishu(i)){
				System.out.println("i:"+i);
				count++;
			}
		}
		return count;
	}
	
	//判断一个数是否为神奇数
	public static boolean shenqishu(int num){
		//任意抽取num的两个位置，判断是否为质数
		String str = String.valueOf(num);
		for(int i=0;i<str.length();i++){
			for(int j=i+1;j<str.length();j++){
				//首位为0的话，就不需要进行判断了
				if(str.charAt(i) == '0'||str.charAt(j)== '0')
					continue;
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(str.charAt(i)));
				sb.append(String.valueOf(str.charAt(j)));
				if(isPrime(Integer.valueOf(sb.toString()))
						||isPrime(Integer.valueOf(sb.reverse().toString()))){
					return true;
				}
			}
		}
		return false;
	}
	
	//判断是否为质数
	public static boolean isPrime(int num){
		//System.out.println("num:"+num);
		int k = (int) Math.sqrt(num);
		for(int i=2;i<=k;i++){
			if(num%i==0)
				return false;
		}
		return true;
	}
	
	//这道题应该题目理解错了
	//求str1和str2的最长连续子串
	//dp[i][j]表示str1从0-i-1位置开始与str2从0-j-1开始字符串匹配的最大长度
	//不是求最长公共子串，而是str1可以从0开始到l2-l1个位子开始与str2匹配，因为其余位子的都可以补上
	public static int maxlength(String str1,String str2){
		int l1 = str1.length();
		int l2 = str2.length();
		int max = 65536;
		for(int i=0;i<=l2-l1;i++){
			int count = 0;
			for(int j=0;j<l1;j++){
				if(str1.charAt(j)==str2.charAt(i+j)){
					count++;
				}
			}
			max = Math.min(max, l1-count);
		}
		return max;
	}
	
	//找出数组中最大的那个元素，如果中最大的那个元素，如果其它元素＊2都可以得到这个数，那么是成立的
	public static String shuzu(int[] num){
		//从小到大排序
		Arrays.sort(num);
		for(int i=num.length-1;i>=1;i--){
			if(num[i]==num[i-1])
				continue;
			if(num[i]%num[i-1]==0){
				int temp = num[i]/num[i-1];
				//判断temp是否为2的倍数
				while(temp!=1)
	            {
	                if(temp%2==0)
	                    temp=temp/2;
	                else
	                    return "NO";
	            }
			}
			else {
				 return "NO";
			}
		}
		return "YES";
	}
	
	//只能改变一个数，求严格递增的最长子序列长度
	public static int dizengsub(int[] num){
		int l = num.length;
		int max = 0;
		for(int i=0;i<l;i++){
			int count = 0;
			int j = i+1;
			for(;j<l;j++){
				if(num[j]<=num[i]){
					count++;
					//只允许出现一次
					if(count == 2)
						break;
				}
			}
			max = Math.max(max, j-i);
			System.out.println("j-i:"+(j-i));
			System.out.println("max:"+max);
		}
		return max;
	}
	
	//只要剔除能力值最高的n个队员，其余能力值次高的n个队员就是分到每个队的水平值
	//超出内存限制
	public static long team(int n,long[] num){
		Arrays.sort(num);
		long sum = 0;
		for(int i=0;i<n;i++){
			sum += num[n+2*i];
		}
		return sum;
	}
	
	/**
	 * 挑选兵王
	 * 因为打赢对手自身的战斗值＝自己战斗值＋对象潜力值－对手战斗值，那么说明要打赢所有潜力值大于战斗值的选手
	 * 记下所有选手潜力值和战斗值的差的累加，记住add
	 * 挑选的兵王会出现两种清空
	 * 1⃣潜力值>战斗值 因为不能与自身交战，那么先减去自身的(潜力值－战斗值)，然后加上自身的潜力值和战斗值
	 * 即为add-(qian-zhan)+qian+zhan=add+2*zhan最大
	 * 2⃣战力值>潜力值，首先他不会加到add中，因此为add+qian+zhan最大
	 * 我们要求的是上面两种情况的最大值
	 */
	public static long zhandou(long[] qianli,long[] zhanli,int n){
		long[] sub = new long[n]; //存储潜力>战力的差值
		long add = 0;
		long max = 0;
		for(int i=0;i<n;i++){
			if(qianli[i]>zhanli[i]){
				add += (qianli[i]-zhanli[i]);
			}
		}
		for(int i=0;i<n;i++){
			//挑战的兵王潜力大于战力
			if(qianli[i]>zhanli[i]){
				max = Math.max(max, add+2*zhanli[i]);
			}
			//挑战的兵王战力大于潜力
			else if (qianli[i]<zhanli[i]) {
				max = Math.max(max, add+zhanli[i]+qianli[i]);
			}
		}
		return max;
	}
	
	/**
	 * 考虑序列A_1, A_2, . . . , A_i是一个单调的序列.
       显然如果对于j < i把序列分为A_1, A_2. . . A_j 和 A_j+1, A_j+2, . . . , A_i 两个部分不会使问题变得更优.
	   于是我们可以贪心的去重复下面过程: 1、 从序列中找出最长的单调连续子序列 2、 删除找出的最长的单调连续子序列
	   这里的单调序列包括非递增和非递减,而判断子序列是否单调的时候,注意处理等于的情况。
	 */
	public static int paixu(int n,long[] num){
		int flag = 0;
		long last = num[0];
		int count = 0;
		for(int i=1;i<n;i++){
			long now = num[i];
			if(flag == 0){
				if(now>last)
					flag = 1;
				else if (now<last)
					flag = 2;
			}
			else if (flag==1) {
				if(now<last){
					count++;
					flag = 0;
				}
			}
			else if (flag == 2) {
				if(now>last){
					count++;
					flag = 0;
				}
			}
			last = now;
		}
		count++;
		return count;
	}
	
	public static void main(String[] args) {
//		Scanner scanner = new Scanner(System.in);
//		String s1 = scanner.nextLine();
//		String s2 = scanner.nextLine();
//		System.out.println(maxlength("abe", "cabc"));
		Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] pre = new int[100005];            //从前往后最大递增子序列
        int[] suf = new int[100005];            //从后往前最大递增子序列
        int[] num = new int[100005];
        Arrays.fill(pre,1);
        Arrays.fill(suf, 1);
        for ( int i = 1 ; i <= N ; i++){
             num[i] = in.nextInt();
        }
        for ( int i = 1 ; i <= N ; i++){
            if ( num[i] > num[i-1]){
                pre[i] = pre[i-1]+1;
            }
        }
        for ( int i = 1 ; i <= N ; i++){
        	System.out.print(pre[i]+" ");
        }
        System.out.println("");
        for ( int i = N ; i >= 1 ; i--){
            if (num[i] < num[i+1]){
                suf[i] = suf[i+1]+1;
            }
        }
        for ( int i = 1 ; i <= N ; i++){
        	System.out.print(suf[i]+" ");
        }
        System.out.println("");
        int res = 1;
        for(int i = 1 ; i <= N ; i++){
            res = Math.max(res, pre[i-1]+1);
            res = Math.max(res, suf[i+1]+1);//以上两步是在找当前最长递增子序列，无论正的或者反的
            if(num[i+1] - num[i-1] >= 2){
            	System.out.println("i:"+i+" res:"+res);
                res = Math.max(res, pre[i-1]+suf[i+1]+1);
                System.out.println("i:"+i+" res:"+res);
            }
        }
        System.out.println(res);

        in.close();
	}
}
