package baidu2018;

import java.util.Scanner;
//题目并不难，已通过，
//简单的说一下吧，首先我也不知道他这个输入时按照行来输入的，
//所以看了牛友的讨论才知道，输入是按照行扫描的，表示又涨姿势了
//另外就是注意题目描述，之少钓到一条鱼的概率，不要忽略了之少这个关键字。
//所以就可以考虑对立事件了。一条鱼也钓不到的概率。然后用1减去对立事件概率就解决了
public class Main{
   //2 2 1 1 1
   //0.2 0.1
   //0.1 0.4	
   public static void fish(double[][] rectangle, double cc, int t) {
      //count记录所有格子的累加概率之和。因为随机钓一个格子，所以钓每个格子的概率
      //都是一样的，都是等概率。
      double ss = 0;
      for (int i = 0; i < rectangle.length; i++) {
          for (int j = 0; j < rectangle[0].length; j++) {
              ss += rectangle[i][j];
          }
      }
      //用累加的概率之和除以格子的总数，就是ss在每个格子钓到鱼的平均概率。
      //也就相当于他固定在一个格子上进行钓鱼。这个格子的概率也是固定的。
      ss = ss / (rectangle.length * rectangle[0].length);
      System.out.println("ss:"+ss+" cc:"+cc+" t:"+t);
      if (ss > cc) {
          System.out.println("ss");
          //因为至少调到一条鱼的情况比较多，所以考虑对立事件，一个鱼也钓不到的情况
          //又因为每分钟钓到鱼和钓不到鱼都是独立事件，互不影响，（感觉又回到高中了 = =）
          //把每分钟钓不到鱼的概率相乘，也就是求它的t次方，就是t分钟钓不到鱼的概率了。
          //最后用1减去这个概率就是能钓到鱼的概率了。
          //下面的分析同理。
          System.out.println(String.format("%.2f", 1 - Math.pow(1 - ss, t)));
      } else if (cc > ss) {
          System.out.println("cc");
          System.out.println(String.format("%.2f", 1 - Math.pow(1 - cc, t)));
      } else {
          System.out.println("equal");
          System.out.println(String.format("%.2f", 1 - Math.pow(1 - cc, t)));
      }
  }

  public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      do {
          int n = sc.nextInt();
          int m = sc.nextInt();
          int x = sc.nextInt() - 1;
          int y = sc.nextInt() - 1;
          int t = sc.nextInt();
          //注意一定要换行
          sc.nextLine();
          double[][] rectangle = new double[n][m];
          for (int i = 0; i < n; i++) {
              //将第一行读到的数字按照空格分开存放在字符串数组中，这个数组的大小就是m
              String[] conten = sc.nextLine().split(" ");
              for (int j = 0; j < m; j++) {
                  //然后再把没一个字符串强转放到二维数组里面就好了
                  rectangle[i][j] = Double.parseDouble(conten[j]);
              }
          }
          fish(rectangle, rectangle[x][y], t);
      } while (sc.hasNext());
  }
}
