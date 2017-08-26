package suanfa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import dataStructure.Graph;
import dataStructure.Vertex;

public class Main {
	private static void KMP(String source, String pattern) {
        int[] N=getN(pattern);
        for(int i=0;i<N.length;i++)
        	System.out.print(N[i]+" ");
        System.out.println("");
        int res=0;
        int sourceLength=source.length();
        int patternLength=pattern.length();
        for(int i=0;i<=(sourceLength-patternLength);){
            res++;
            String str=source.substring(i, i+patternLength);//要比较的字符串,与目标串比较
            p(str);
            int count=getNext(pattern, str,N);
            p("移动"+count+"步");
            if(count==0){
                p("KMP：匹配成功");
                break;
            }
            i=i+count;
        }
        p("KMP：一共匹配"+res+"次数");
    }
	
    /**
     * 得到下一次要移动的次数 移动位数 = 已匹配的字符数 - 对应的部分匹配值
     * 
     * @param pattern
     * @param str
     * @param N
     * @return 0,字符串匹配；
     */
	
    private static int getNext(String pattern,String str,int[] N) {
        int n = pattern.length();
        char v1[] = str.toCharArray();
        char v2[] = pattern.toCharArray();
        int x = 0;
        while (n-- != 0) {
            if (v1[x] != v2[x]){
                if(x==0){
                    return 1;//如果第一个不相同，移动1步
                }
                return x-N[x-1];//x:第一次出现不同的索引的位置，即j
            }
            x++;
        }
        return 0;
    }
    
    private static int[] getN(String pattern) {
        char[] pat=pattern.toCharArray();
        int j=pattern.length()-1;
        int[] N=new int[j+1];
        for(int i=j;i>=2;i--){
            N[i-1]=getK(i,pat);
        }
//        for(int a:N)
//            p(a);
        return N;
    }
    
    private static int getK(int j, char[] pat) {
        int x=j-2;
        int y=1;
        while (x>=0 && compare(pat, 0, x, y, j-1)) {
            x--;
            y++;
        }
        return x+1;
    }
    
    private static boolean compare(char[] pat,int b1,int e1,int b2,int e2){
        int n = e1-b1+1;
        while (n-- != 0) {
            if (pat[b1] != pat[b2]){
                return true;
            }
            b1++;
            b2++;
        }
        return false;
    }
    
    public static void p(Object obj) {
        System.out.println(obj);
    }
    
    /**
     * 快速排序
     */
    public static void sort(int a[], int low, int hight) {
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i]; // 用子表的第一个记录做基准
        while (i < j) { // 从表的两端交替向中间扫描
        	//右边找小的放到左边
            while (i < j && a[j] >= index)
                j--;
            if (i < j)
                a[i++] = a[j];// 用比基准小的记录替换低位记录
            //左边找大的放到右边
            while (i < j && a[i] < index)
                i++;
            if (i < j) // 用比基准大的记录替换高位记录
                a[j--] = a[i];
        }
        a[i] = index;// 将基准数值替换回 a[i]
        sort(a, low, i - 1); // 对低子表进行递归排序
        sort(a, i + 1, hight); // 对高子表进行递归排序
    }
    
    public static void quickSort(int a[]) {
        sort(a, 0, a.length - 1);
    }
    
    //java线程间的通信方式，除了voltaile变量(管道机制怎么用)
    //Arrays.sort方法的排序原理是什么
    //ArrayList的底层原理
    //类的继承除了构造函数和static代码块，其余的变量的构造函数
    //static同样的变量能不能被覆盖
    //object有哪些方法,hashcode
    //http请求头包括哪些东西
    //http的状态码 3开头的是什么状态码
    //Spring事务注解的内容
    //AOP除了日志管理、异常管理还有事务
    //动态代理的两种方式,Proxy,CGLIB,问了两次
    //数据库索引，各种隔离级别，比如可重复读的规则
    //JVM虚拟机的内存模型是什么,刚才只回答了多线程的一些规则
    //MyBatis
    //SpringMVC返回Json,ResponseBody,ResponseRequest
    //wait和sleep方法的区别
    //Java类的加载机制
    
    //快速排序
//    public static void quickSort(int low,int high,int[] num){
//    	if(low>high||num.length==0)
//    		return;
//    	int i = low;
//    	int j = high;
//    	int index = num[i];
//    	while(i<j){
//    		//从右边找小的
//    		while(i<j&&num[j]>index){
//    			j--;
//    		}
//    		if(i<j)
//    			num[i++] = num[j];
//    		while(i<j&&num[i]<index){
//    			i++;
//    		}
//    		if(i<j)
//    			num[j--]=num[i];
//    	}
//    	num[i]=index;
//    	quickSort(low, i-1, num);
//    	quickSort(i+1, high, num);
//    }

    public static int findK(int low, int high,int[] num, int K) {
        if (low == high) {
            return num[low];
        }
        int pivot = quickSort(num, low, high);
        int kp = pivot - low + 1;//计算pivot是第几大数
        if (kp == K) {
            return num[pivot];
        }
        if (K < kp) {
        	//在前面部分查找第K大的数
            return findK(low, pivot - 1, num, K);
        }else {
        	//在后面部分查找第K大的数
            return findK(pivot + 1, high, num, K - kp);
        }
    }

    public static int quickSort(int[] arr, int low, int high) {
    	int i = low;
        int j = high;
        int temp = arr[i];
        //将>=temp的元素换到左边区域,将<=temp的元素换到右边区域
        while (i < j) {
            while (i < j && arr[j] <= temp) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
            }
            while (i < j && arr[i] >= temp) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
            }
        }
        arr[i] = temp;
        return i;
    }
    
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	String[] strs = scanner.nextLine().split(" ");
    	int length = strs.length;
    	int K = Integer.valueOf(scanner.nextLine());
    	int[] num = new int[length];
    	for(int i=0;i<length;i++)
    		num[i] = Integer.parseInt(strs[i]);
    	System.out.println(findK(0, length-1, num, K));
	}
}
