package spiralMatrixII;

public class Solution {
	public static int[][] generateMatrix(int n) {
		int[][] res = new int[n][n];
		if(n == 0)
			return res;
		//螺旋次数
		int levelNum = n/2;
		int num = 1;
		for(int level=0;level<levelNum;level++){
			//上层
			for(int i=level;i<n-level-1;i++){
				res[level][i] = num++;
			}
			//右层
			for(int i=level;i<n-level-1;i++){
				res[i][n-level-1] = num++;
			}
			//下层
			for(int i=n-level-1;i>level;i--){
				res[n-level-1][i] = num++;
			}
			for(int i=n-level-1;i>level;i--){
				res[i][level] = num++;
			}
		}
		if(n%2 == 1){
			res[levelNum][levelNum] = num++;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[][] res = generateMatrix(5);
		System.out.println(res[0][0]+" "+res[0][1]+" "+res[0][2]+" "+res[0][3]+" "+res[0][4]);
		System.out.println(res[1][0]+" "+res[1][1]+" "+res[1][2]+" "+res[1][3]+" "+res[1][4]);
		System.out.println(res[2][0]+" "+res[2][1]+" "+res[2][2]+" "+res[2][3]+" "+res[2][4]);
		System.out.println(res[3][0]+" "+res[3][1]+" "+res[3][2]+" "+res[3][3]+" "+res[3][4]);
		System.out.println(res[4][0]+" "+res[4][1]+" "+res[4][2]+" "+res[4][3]+" "+res[4][4]);
		//System.out.println(res[5][0]+" "+res[5][1]+" "+res[5][2]+" "+res[5][3]+" "+res[5][4]);
	}
}	
