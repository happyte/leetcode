package wangyiyoudao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static String fanpan(int[] num,int n,int k){ 
		StringBuffer sb = new StringBuffer();
		for(int j=0;j<k;j++){
			ArrayList<Integer> list = new ArrayList<>();
			for(int i=n-1;i>=0;i--){
				list.add(num[i+n]);
				list.add(num[i]);
			}
			Collections.reverse(list);
			for(int i=0;i<list.size();i++)
				num[i] = list.get(i);
		}
		for(int i=0;i<num.length;i++){
			if(i == num.length-1)
				sb.append(num[i]);
			else
				sb.append(num[i]+" ");
		}
		return sb.toString();
	}
	
	//小易打怪
	public static int daguai(int[] num,int n,int ori){
		int sum = ori;
		for(int i=0;i<n;i++){
			if(sum>=num[i])
				sum += num[i];
			else
				sum += gongyue(sum, num[i]);
		}
		return sum;
	}
	
	//求两个数的最大公约数
	public static int gongyue(int a,int b){
		if(b == 0)
			return a;
		return gongyue(b, a%b);
	}
	
	//炮台给敌人造成的最大伤害
	public static String paotai(int[] x,int[] y,int n,int max,int emyX,int emyY){
		int sum = 0;
		for(int i=0;i<n;i++){
			double dis = Math.sqrt(Math.abs(x[i]-emyX)*Math.abs(x[i]-emyX)+Math.abs(y[i]-emyY)*Math.abs(y[i]-emyY));
			System.out.println("dis:"+dis);
			if(dis<=max)
				sum ++;
		}
		return sum+"x";
	}
	
	//最多可以扫描的爆炸蘑菇
	//3*3扫描镜只能用两次
	//N行,M列
	public static int mogu(int N,int M,int K,int[][] mushroom){
		//在外面再构建一层
		int[][] graph = new int[N+2][M+2];
		//有k个蘑菇,填到矩阵中去，1≤x≤N,1≤y≤M)，最外层的padding是没有蘑菇的
		for(int i=0;i<K;i++)
			graph[mushroom[i][0]][mushroom[i][1]]++;
		int max = 0;
		for(int i=1;i<=N;i++){
			for(int j=1;j<=M;j++){
				//开始扫描第一个九宫格,有蘑菇的话则用一个二位数组保存去掉的蘑菇数
				int[][] matrix = new int[N+2][M+2];
				int max1 = 0;
				for(int dx=-1;dx<2;dx++){
					for(int dy=-1;dy<2;dy++){
						if(graph[i+dx][j+dy]>0){
							max1++;
							matrix[i+dx][j+dy]--;
						}
					}
				}
				for(int k=1;k<=N;k++){
					for(int l=1;l<=M;l++){
						int max2 = 0;
						for(int dx=-1;dx<2;dx++){
							for(int dy=-1;dy<2;dy++){
								if(graph[k+dx][l+dy]+matrix[k+dx][l+dy]>0)
									max2++;
							}
						}
						if(max1+max2>max)
							max = max1+max2;
						if(max == 18)
							return 18;
					}
				}
			}
		}
		return max;
	}
	
	//W*H的网格最多能放几块蛋糕
	//网格行编号0-H-1,列编号0-W-1
	public static int dangao(int W,int H){
		boolean[][] visited = new boolean[H][W];
		int count = 0;
		for(int i=0;i<H;i++){
			for(int j=0;j<W&&!visited[i][j];j++){
				count++;
				//距离差为2是不能放蛋糕的
				if(i+2<H)
					visited[i+2][j]=true;
				if(j+2<W)
					visited[i][j+2]=true;
			}
		}
		return count;
	}
	
	public static int xianjin(int[] x,int[] y,int n){
		int min = 65536;
		for(int i=0;i<n;i++){
			int dis = Math.abs(x[i]-1)+Math.abs(y[i]-1);
			min = Math.min(min, dis);
		}
		return min;
	}
	
	//B字符串插入A中，问有多少种插入方法使得插入后是个回文串
	public static int huiwen(String s1,String s2){
		int count = 0;
		int n = s1.length();
		for(int i=0;i<=n;i++){
			String newStr = new String(s1.substring(0, i)+s2+s1.substring(i, n));
			if (isHuiwen(newStr)) {
				count++;
			}
		}
		return count;
	}
	
	public static boolean isHuiwen(String s){
		for(int i=0;i<s.length()/2;i++){
			if(s.charAt(i) != s.charAt(s.length()-i-1))
				return false;
		}
		return true;
	}
	
	//幸运的袋子，从一个袋子中可以拿出一些数，使得剩下的数的累加大于累乘
	public static int dfs(int[] num,int index,long sum,long multi){
		int count = 0;
		for(int i=index;i<num.length;i++){
			sum += num[i];
			multi *= num[i];
			//考虑前0...i-1的累加和累乘
			if(sum>multi)
				count = count+1+dfs(num, index+1, sum, multi);
			else if (num[i] == 1)
				count = count+dfs(num, index+1, sum, multi);
			else
				break;
			sum -= num[i];
			multi /= num[i];
			//去除重复
			while(i<num.length-1&&num[i]==num[i+1])
				i++;
		}
		return count;
	}
	
	//输入的字符串是否为两种排序中的一种
	public static String paixu(String[] strs){
		String str= "none";
		if(isDictonary(strs)&&isLength(strs))
			return "both";
		else if (isDictonary(strs))
			return "lexicographically";
		else if (isLength(strs))
			return "lengths";
		return "none";
	}
	
	public static boolean isDictonary(String[] strs){
		String[] strC = strs.clone();
		//本身这个API就是按照字典排序
		Arrays.sort(strs);
		for(int i=0;i<strs.length;i++){
			if(strC[i] != strs[i])
				return false;
		}
		return true;
	}
	
	public static int feibonaqi(int n){
		int a = 1;
		int b = 1;
		//b大，a小
		while(!(n>=a&&n<=b)){
			int temp = b;
			b = b+a;
			a = temp;
		}
		int min = Math.min(n-a, b-n);
		System.out.println("a:"+a);
		System.out.println("b:"+b);
		return min;
	}
	
	public static boolean isLength(String[] strs){
		for(int i=1;i<strs.length;i++){
			if(strs[i].length()<strs[i-1].length())
				return false;
		}
		return true;
	}
	
	//找出最小不能由n个数选取求和组成的数
	public static int mincost(int[] num){
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println(feibonaqi(22));
//		Scanner scanner = new Scanner(System.in);
//		int n = scanner.nextInt();
//		int[] x = new int[n];
//		int[] y = new int[n];
//		for(int i=0;i<n;i++)
//			x[i] = scanner.nextInt();
//		for(int i=0;i<n;i++)
//			y[i] = scanner.nextInt();
//		System.out.println(xianjin(x, y, n));
	}
}
