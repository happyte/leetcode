package haikang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	//1,A,0;2,B,1;3,C,1         A-B;A-C
	//1,A,0; B,1,2;             incorrect data
	//1,A,0;2,B,1;3,C,2;4,D,2   A-B-C;A-B-D
	//2,A,0; 1,B,1;3,C,2        incorrect data
	public static void resolve(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<String> bumens) {
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, String> bumenMap = new HashMap<>();
        for(int i=0;i<ids.size();i++){
        	parentMap.put(parents.get(i), ids.get(i));
        	bumenMap.put(ids.get(i), bumens.get(i));
        }
        
        System.out.println("incorrect data");
    }
	
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()){
			String[] strs = scanner.nextLine().split(";");
			ArrayList<Integer> ids = new ArrayList<>();
			ArrayList<Integer> parents = new ArrayList<>();
			ArrayList<String> bumens = new ArrayList<>();
			for(int i=0;i<strs.length;i++){
				ids.add(Integer.valueOf(strs[i].split(",")[0]));
				bumens.add(strs[i].split(",")[1]);
				parents.add(Integer.valueOf(strs[i].split(",")[2]));
			}
			resolve(ids, parents, bumens);
		}
	}
}
