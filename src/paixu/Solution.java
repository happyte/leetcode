package paixu;

import java.util.Arrays;

public class Solution {
	public static void charu(int[] a){
		//直接插入排序
        for (int i = 1; i < a.length; i++) {
            //待插入元素
            int temp = a[i];
            int j;
            for (j = i-1; j>=0; j--) {
                //将大于temp的往后移动一位
                if(a[j]>temp){
               	 a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = temp;
        }
	}
	
	//选择排序
	public static void xuanze(int[] a){
		//简单的选择排序
		for (int i = 0; i < a.length; i++) {
			int min = a[i];
			int n=i; //最小数的索引
			for(int j=i+1;j<a.length;j++){
				if(a[j]<min){  //找出最小的数
					min = a[j];
					n = j;
				}
			}
			a[n] = a[i];
			a[i] = min;   
		}
	}
	
	//对data数组从0到lastIndex建大顶堆
	public static void buildMaxHeap(int[] data, int lastIndex){
		//从lastIndex处节点（最后一个节点）的父节点开始 
		for(int i=(lastIndex-1)/2;i>=0;i--){
			//k保存正在判断的节点 
			int k=i;
			//如果当前k节点的子节点存在  
			while(k*2+1<=lastIndex){
				//k节点的左子节点的索引 
				int biggerIndex=2*k+1;
				//如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
				if(biggerIndex<lastIndex){  
					//若果右子节点的值较大  
					if(data[biggerIndex]<data[biggerIndex+1]){  
						//biggerIndex总是记录较大子节点的索引  
						biggerIndex++;  
					}  
				}  
				//如果k节点的值小于其较大的子节点的值  
				if(data[k]<data[biggerIndex]){  
					//交换他们  
					swap(data,k,biggerIndex);  
					//将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值  
					k=biggerIndex;  
				}else{  
					break;  
				}  
			}
		}
	}
	
	//交换
	private static void swap(int[] data, int i, int j) {  
		int tmp=data[i];  
		data[i]=data[j];  
		data[j]=tmp;  
	} 
	
	//快速排序
	private static void quickSort(int[] a,int low,int high){
		if(low<high){
			int middle = partition(a, low, high);
			quickSort(a, low, middle-1);
			quickSort(a, middle+1, high);
			
		}
	}
	
	private static int partition(int[] a,int low,int high){
		int i = low;
		int j = high;
		int temp = a[low];
		while(i<j){
			//右边找小,左边找大
			while(i<j&&a[j]>=temp)
				j--;
			if (i<j) 
				a[i] = a[j];
			while(i<j&&a[i]<temp)
				i++;
			if(i<j)
				a[j] = a[i];
		}
		a[i] = temp;
		return i;
	}
	
	private static void mergeSort(int[] a, int left, int right) {
		if(left<right){
		int middle = (left+right)/2;
		//对左边进行递归
		mergeSort(a, left, middle);
		//对右边进行递归
		mergeSort(a, middle+1, right);
		//合并
		merge(a,left,middle,right);
		}
	}

	private static void merge(int[] a, int left, int middle, int right) {
		int[] tmpArr = new int[a.length];
		int mid = middle+1; //右边的起始位置
		int tmp = left;
		int third = left;
		while(left<=middle && mid<=right){
			//从两个数组中选取较小的数放入中间数组
			if(a[left]<=a[mid]){
				tmpArr[third++] = a[left++];
			}else{
				tmpArr[third++] = a[mid++];
			}
		}
		//将剩余的部分放入中间数组
		while(left<=middle){
			tmpArr[third++] = a[left++];
		}
		while(mid<=right){
			tmpArr[third++] = a[mid++];
			}
		//将中间数组复制回原数组
		while(tmp<=right){
			a[tmp] = tmpArr[tmp++];
			}
		}
	
	public static void main(String[] args) {
		int[] a={49,38,65,97,76,13,27,49,78,34,12,64};
		int arrayLength=a.length;  
		quickSort(a, 0, arrayLength-1);
		System.out.println(Arrays.toString(a));  
		//循环建堆  
//		for(int i=0;i<arrayLength-1;i++){  
//			//建堆  
//			buildMaxHeap(a,arrayLength-1-i);  
//			//交换堆顶和最后一个元素  
//			swap(a,0,arrayLength-1-i);  
//			System.out.println(Arrays.toString(a));  
//		}  
	}
}
