package wangyiqiuzhao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	//这是道规律题
	public static String nixu(int n,long[] num){
		StringBuffer sb = new StringBuffer();
		//n为奇数的时候，前n/2+1个数字为奇数的递减，例如7,5,3,1,2,4,6
		//n为偶数的时候，前n/2个数字为偶数的递减，例如8,6,4,2,1,3,5,7
		for(int i=n;i>=1;i-=2){
			sb.append(num[i-1]+" ");
		}
		if(n%2==1){
			for(int i=2;i<=n-1;i+=2){
				if(i == n-1)
					sb.append(num[i-1]);
				else
					sb.append(num[i-1]+" ");
			}
		}
		else {
			for(int i=1;i<=n-1;i+=2){
				if(i == n-1)
					sb.append(num[i-1]);
				else
					sb.append(num[i-1]+" ");
			}
		}
		return sb.toString();
	}
	
	//小易想独自生活，每天要吃一个水果和付x元房租，如果他现在有f个水果和d元钱，商店的水果每个p元，问他能独自生活多少天
	//x元租金，f个水果，d元钱，水果店每个水果p元
	public static long shenghuo(long x,long f,long d,long p){
		//如果有水果的这段时间连租金都付不起
		if(d/x<=f)
			return d/x;
		long sum = f;
		sum += (d-f*x)/(p+x);
		return sum;
	}
	
	//漂亮的砖头
	//如果最多存在一对不同颜色的相邻砖块,那么这行砖块就很漂亮的
	//WXYZ输出应该为0
	public static int zhuantou(String str){
		Set<Character> set = new HashSet<>();
		for(int i=0;i<str.length();i++){
			set.add(str.charAt(i));
		}
		if(set.size() == 1)
			return 1;
		if(set.size()>2)
			return 0;
		return 2;
	}
	
	//通过任意次交换，是否能变成等差数列
	public static String dengcha(int n,int[] num){
		Arrays.sort(num);
		boolean flag = true;
		for(int i=1;i<num.length-1;i++){
			if(num[i+1]-num[i]!=num[i]-num[i-1]){
				flag = false;
				break;
			}
		}
		if(flag)
			return "Possible";
		return "Impossible";
	}
	
	//最长的01子串
	public static int onezero(boolean[] bools){
		//从数组中的每个元素开始找，后面一位是否取反
		int max = 0;
		for(int i=0;i<bools.length;i++){
			int count = 0;
			for(int j=i+1;j<bools.length;j++){
				if(bools[j]==!bools[j-1])
					count++;
				else
					break;
			}
			System.out.println("count:"+(count+1));
			max = Math.max(max, count+1);
		}
		return max;
	}
	
	//堆棋子
	//输出n个整数,第i个表示棋盘上有一个格子至少有i个棋子所需要的操作数
	public static String qizi(int n,int[] x,int[] y){
		StringBuffer sb = new StringBuffer();
		sb.append("0 ");
		int[] maxX = new int[n];
		int[] maxY = new int[n];
		int[] minX = new int[n];
		int[] minY = new int[n];
		int x_max = x[0];
		int y_max = y[0];
		int x_min = x[0];
		int y_min = y[0];
		for(int i=1;i<n;i++){
			x_max = Math.max(x_max, x[i]);
			maxX[i] = x_max;
			y_max = Math.max(y_max, y[i]);
			maxY[i] = y_max;
			x_min = Math.min(x_min, x[i]);
			minX[i] = x_min;
			y_min = Math.min(y_min, y[i]);
			minY[i] = y_min;
		}
		for(int i=0;i<n;i++){
			System.out.println("X最小:"+minX[i]+" X最大:"+maxX[i]+" Y最小:"+minY[i]+" Y最大:"+maxY[i]);
		}
		//有i个棋子需要找到合适的位置,一个棋子就不需要操作了
		for(int i=1;i<n;i++){
			//找出前i个棋子x和y的最大值
			//棋子的坐标为(j,k)
			int min = 65536;
			for(int j=minX[i];j<=maxX[i];j++){
				for(int k=minY[i];k<=maxY[i];k++){
					int cur = 0;
					for(int m=0;m<=i;m++){
						cur += Math.abs(j-x[m])+Math.abs(k-y[m]);
					}
					min = Math.min(min, cur);
				}
			}
			if(i == n-1)
				sb.append(min);
			else
				sb.append(min+" ");
		}
		return sb.toString();
	}
	
	//疯狂值为每队相邻排列学生身高差的绝对值总和，顺序排列的疯狂值是最小的，现在求出队列最大可能的疯狂值
		public static long fengkuangzhi(int n,long[] num){
			long[] arr = new long[n];
			Arrays.sort(num);
			//每次插入两个值
			int level = num.length/2;
			for(int i=0;i<level;i++){
				//奇数的插法和偶数是一样的，关键在于最中间的位置是最大数字还是最小数字,两边的插入方法还是小,大
				if(num.length%2==1){
					int mid = num.length/2;
					if(i%2==0){
						arr[mid-i-1] = num[i];
						arr[mid+i+1] = num[i+1];
					}
					else {
						arr[mid-i-1] = num[num.length-i-1];
						arr[mid+i+1] = num[num.length-i-2];
					}
				}
				//偶数个，例如先插1,2位置
				else {
					int mid = num.length/2;
					//i是2的倍数的话，插入是小，大
					if(i%2==0){
						arr[mid-i-1] = num[i];
						arr[mid+i] = num[num.length-i-1];
					}
					//i不是2的倍数的话，插入是大，小
					else {
						arr[mid-i-1] = num[num.length-i-1];
						arr[mid+i] = num[i];
					}
				}
			}
			if(num.length%2==1){
				arr[num.length/2] = num[n-1];
			}
			long sum = 0;
			for(int i=1;i<n;i++){
				sum += Math.abs(arr[i]-arr[i-1]);
			}
			for(int i=0;i<n;i++){
				System.out.print(arr[i]+" ");
			}
			System.out.println("");
			return sum;
		}
		
	//小易喜欢的数列
	//对于位置相邻的两个数A和B(A在B前),都满足(A <= B)或(A mod B != 0)(满足其一即可)
	public static int shulie(int n,int k){
		return 0;
	}
	
	public static void main(String[] args) {	
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long[] num = new long[n];
		for(int i=0;i<n;i++)
			num[i] = scanner.nextLong();
		System.out.println(nixu(n, num));
	}
}
