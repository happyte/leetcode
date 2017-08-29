package BAT;

//牛客网BAT算法练习
public class Main {
	/**
	 * @param source:目标串
	 * @param pattern:模式串
	 */
	private static boolean KMP(String source, String pattern){
		int[] N = getN(pattern);
		for(int i=0;i<N.length;i++)
			System.out.print(N[i]+" ");
		System.out.println("");
		int sourceLength = source.length();
		int patternLength = pattern.length();
		for(int i=0;i<=(sourceLength-patternLength);){
			String str = source.substring(i, i+patternLength);
			int count = getNext(pattern, str, N);
			if(count == 0){
				System.out.println("在i:"+i+"位置匹配成功");
				return true;
			}
			i+=count;
		}
		return false;
	}
	
	/**
	 * 返回下一次要移动的步数，移动步数:当前已经匹配的字符串－最后一位匹配的字符对应在Next表中的数字
	 * str和pattern是同样长度的
	 * @param pattern
	 * @param str
	 * @param N
	 * @return
	 */
	private static int getNext(String pattern,String str,int[] N){
		char[] v1 = str.toCharArray();
		char[] v2 = pattern.toCharArray();
		int n = str.length();
		int x = 0;
		while(n--!=0){
			if(v1[x]!=v2[x]){
				if(x == 0){
					return 1;
				}
				return x-N[x-1];
			}
			x++;
		}
		return 0;
	}
	
	/**
	 * 根据模式串返回next表
	 * @param pattern
	 * @return
	 */
	private static int[] getN(String pattern){
		char[] pat = pattern.toCharArray();
		int j = pat.length;
		int[] N = new int[j];
		for(int i=j;i>=2;i--){
			N[i-1] = getK(i, pat);
		}
		return N;
	}
	
	/**
	 * 计算next表中的某一位j，第一位不需要计算，肯定为0
	 * @param j
	 * @param pat
	 * @return
	 */
	private static int getK(int j, char[] pat){
		int x = j-2;
		int y = 1;
		while(x>=0&&compare(pat, 0, x, y, j-1)){
			x--;
			y++;
		}
		return ++x;
	}
	
	/**
	 * 比较字符串是否相同
	 * @param pat
	 * @param b1
	 * @param e1
	 * @param b2
	 * @param e2
	 * @return
	 */
	private static boolean compare(char[] pat,int b1,int e1,int b2,int e2){
		//e1-b1=e2-b2,最多需要比较e1-b1+1次
		int n = e1-b1+1;
		while(n--!=0){
			if(pat[b1]!=pat[b2])
				return true;
			b1++;
			b2++;
		}
		return false;
	}
	
	public static boolean chkRotation(String A, int lena, String B, int lenb) {
		String C = A+A;
		if(KMP(C, B))
			return true;
        return false;
    }
	
	public static void main(String[] args) {
		System.out.println(chkRotation("12345", 5, "64512", 5));
	}
	 
}
