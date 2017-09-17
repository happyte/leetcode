package xunfei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;



public class Main {
	public static ArrayList<Dui> duiList = new ArrayList<>();
	public static ArrayList<Dui> nameList = new ArrayList<>();
	
	public static class Dui{
		String name;
		int jifen;
		int jinshen;
		public Dui(String name){
			this.name = name;
		}
		
		public void setJifen(int jifen) {
			this.jifen = jifen;
		}
		
		public void setJinshen(int jinshen) {
			this.jinshen = jinshen;
		}
		
		public int getJifen() {
			return jifen;
		}
		
		public int getJinshen() {
			return jinshen;
		}
	}
	
	//根据队伍名字修改其信息
	public static void changeStatus(String name,int jifen,int jinshen){
		for(Dui dui:duiList){
			if(dui.name.equals(name)){
				int fen = dui.getJifen();
				int qiu = dui.getJinshen();
				dui.setJifen(fen+jifen);
				dui.setJinshen(qiu+jinshen);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			//n个球队 
			int n = Integer.valueOf(scanner.nextLine());
			for(int i=0;i<n;i++){
				String name = scanner.nextLine();
				Dui dui = new Dui(name);
				duiList.add(dui);
				nameList.add(dui);
			}
			int total = (n-1)*n/2;
			for(int i=0;i<total;i++){
				String[] strs = scanner.nextLine().split(" ");
				String changci = strs[0];
				String bifen = strs[1];
				String A = strs[0].split("-")[0];
				String B = strs[0].split("-")[1];
				int aJin = Integer.valueOf(strs[1].split(":")[0]);
				int bJin = Integer.valueOf(strs[1].split(":")[1]);
				//A胜,A加3分，加球数
				if(aJin>bJin){
					changeStatus(A, 3, aJin-bJin);
					changeStatus(B, 0, bJin-aJin);
				}
				else if (aJin == bJin) {
					changeStatus(A, 1, 0);
					changeStatus(B, 1, 0);
				}
				else {
					changeStatus(B, 3, bJin-aJin);
					changeStatus(A, 0, aJin-bJin);
				}
			}
			Collections.sort(duiList,new Comparator<Dui>() {

				@Override
				public int compare(Dui o1, Dui o2) {
					//两者积分相同,净胜球
					if(o1.jifen == o2.jifen){
						return o2.jinshen-o1.jinshen;
					}
					//否则按积分排序
					else {
						return o2.jifen - o1.jifen;
					}
				}
			});
			Dui miDui = duiList.get(n/2);
			for(Dui dui:nameList){
				if(dui.jifen > miDui.jifen)
					System.out.println(dui.name);
				else if(dui.jifen == miDui.jifen){
					if(dui.jinshen > miDui.jinshen)
						System.out.println(dui.name);
				}
			}
			
		}
	}
}
