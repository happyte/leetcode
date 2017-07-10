package sliceNumber;

import java.util.HashMap;

public class Solution {
	public static boolean sliceNumer(int[] nums){
		if(nums == null || nums.length == 0)
			return false;
		int left = 0;
		int right = nums.length-1;
		int leftSum = nums[left];
		int rightSum = nums[right];
		//2,5,1,1,1,1,4,1,7,3,7
		while(left <= right){
			if(leftSum == rightSum){
				System.out.println("left:"+left+" sum:"+leftSum);
				System.out.println("right:"+right+" sum:"+rightSum);
				break;
			}
			if(leftSum < rightSum)
				leftSum += nums[++left];
			else
				rightSum += nums[--right];
		}
		if(left > right)
			return false;
		int secondSum = 0;
		int i = 0;
		for(i=left+2;i<right-1;i++){
			secondSum += nums[i];
			if(secondSum == leftSum){
				System.out.println("left:"+i+" sum:"+secondSum);
				break;
			}
		}
		int thirdSum = 0;
		int j = 0;
		for(j=right-2;j>left+1;j--){
			thirdSum += nums[j];
			if(thirdSum == rightSum){
				System.out.println("right:"+j+" sum:"+thirdSum);
				break;
			}
		}
		//切割成四段，且二三两端中间间隔了一个
		if(j-i==2)
			return true;
		return false;
	}
	
	public static boolean canDevide(int[] nums){
        if(nums == null || nums.length < 7){
            return false;
        }
        HashMap<Long,Integer> indexMap = new HashMap<Long,Integer>();//<总和，位置>
        HashMap<Integer,Long> sumMap = new HashMap<Integer,Long>();//<位置，总和>

        long curSum = 0;
        for(int i = 0; i < nums.length; i++){
            indexMap.put(curSum,i);
            sumMap.put(i,curSum);
            curSum += nums[i];
        }
        long leftSum = nums[0];//最左段总和
        long rightSum = nums[nums.length - 1];//最右段总和
        int leftIndex = 1;//左分割点
        int rightIndex = nums.length - 2;//右分割点

        while(leftIndex + 3 < rightIndex){
            if(leftSum == rightSum){
                if(indexMap.get((leftSum << 1) + nums[leftIndex]) != null){
                    int middleIndex = indexMap.get((leftSum << 1) + nums[leftIndex]);//中间分割点
                    if(middleIndex > leftIndex + 1 && middleIndex < rightIndex - 1){
                        if(sumMap.get(rightIndex) - sumMap.get(middleIndex + 1) == leftSum){
                            return true;
                        }
                    }
                }
                leftSum += nums[leftIndex++];
                rightSum += nums[rightIndex--];
            }
            else{
                if(leftSum < rightSum){
                    leftSum += nums[leftIndex++];
                }
                else{
                    rightSum += nums[rightIndex--];
                }
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		int[] A = new int[]{2,5,1,1,1,1,4,1,7,3,7};
//		int[] A = new int[]{1,2,1,3,1,4,2};
//		int[] A = new int[]{2,5,4,7,2,5,4,7,2,5,4,7,2,5,4,7,2,5,4,7,2,5,4,7};
//		int[] A = new int[]{10,2,11,13,1,1,5,1,1,1,10,2,11,12,5,1,1,1,1,1,10,2,11,10,5,1,1,1,1};
		System.out.println(sliceNumer(A));
		System.out.println(canDevide(A));
	}
}
