package pingduoduo;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	//给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大
	public static long shuzu(int[] num){
		int n = num.length;
		Arrays.sort(num);
		long min1 = num[0];
		long min2 = num[1];
		long max = 1;
		for(int i=n-1;i>=n-3;i--)
			max *= num[i];
		return Math.max(max, min1*min2*num[n-1]);
	}
	
	//大数相乘
	public static String dashu(int[] a,int[] b){
		StringBuffer sb = new StringBuffer();
		//c数组的最大位数为a的长度加上b的长度
		int[] c = new int[a.length+b.length];
		//逐个相乘
		for(int i=0;i<a.length;i++){
			for(int j=0;j<b.length;j++){
				c[i+j] += a[i]*b[j];
			}
		}
		for(int i=0;i<c.length;i++)
			System.out.print(c[i]+" ");
		System.out.println("");
		//移位进位
		for(int i=0;i<c.length-1;i++){
			c[i+1] += c[i]/10;
			c[i] = c[i]%10;
		}
		for(int i=c.length-1;i>=0;i--)
			sb.append(c[i]);
		if(sb.charAt(0) == '0')
			return sb.substring(1).toString();
		return sb.toString();
	}
	
	//分巧克力
	//他分到的巧克力大小达到h[i] (即w[j]>=h[i])，他才会上去表演节目
	public static int qiaokeli(int[] w,int[] h){
		Arrays.sort(w);
		Arrays.sort(h);
		int count = 0;
		//表示该巧克力已经被分给小朋友了
		boolean[] qvisited = new boolean[w.length];
		//表示哪个小朋友被分过巧克力了
		boolean[] visited = new boolean[h.length];
		for(int i=w.length-1;i>=0;i--){
			for(int j=h.length-1;j>=0;j--){
				//巧克力大小大于小朋友的需求且该小朋友没有被给过巧克力
				if (w[i]>=h[j]&&!visited[j]&&!qvisited[i]) {
					qvisited[i] = true;
					visited[j] = true;
					count++;
				}
			}
		}
		return count;
	}
	
	//迷宫寻路
	//0-墙，1-路，2-探险家的起始位置，3-迷宫的出口，大写字母-门，小写字母-对应大写字母所代表的门的钥匙 
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] h = new int[n];
		for(int i=0;i<n;i++)
			h[i] = scanner.nextInt();
		int m = scanner.nextInt();
		int[] w = new int[m];
		for(int i=0;i<m;i++)
			w[i] = scanner.nextInt();
		System.out.println(qiaokeli(w, h));
	}
}
