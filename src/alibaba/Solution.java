package alibaba;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {

//        ArrayList<Integer> _ids = new ArrayList<Integer>();
//        ArrayList<Integer> _parents = new ArrayList<Integer>();
//        ArrayList<Integer> _costs = new ArrayList<Integer>();
//
//        Scanner in = new Scanner(System.in);
//        String line = in.nextLine();
//
//        while(line != null && !line.isEmpty()) {
//            if(line.trim().equals("0")) break;
//            String []values = line.trim().split(" ");
//            if(values.length != 3) {
//                break;
//            }
//            _ids.add(Integer.parseInt(values[0]));
//            _parents.add(Integer.parseInt(values[1]));
//            _costs.add(Integer.parseInt(values[2]));
//            line = in.nextLine();
//        }
//        int res = resolve(_ids, _parents, _costs);
//
//        System.out.println(String.valueOf(res));
		ArrayList<Integer> t1 = new ArrayList<>();
		t1.add(1);
		t1.add(2);
		t1.add(3);
		t1.add(4);
		ArrayList<Integer> t2 = new ArrayList<>();
		t2.add(0);
		t2.add(0);
		t2.add(1);
		t2.add(1);
		ArrayList<Integer> t3 = new ArrayList<>();
		t3.add(2);
		t3.add(3);
		t3.add(2);
		t3.add(3);
		System.out.println(resolve(t1, t2, t3));
    }
	
	public static int resolve(ArrayList<Integer> ids, ArrayList<Integer> parents, ArrayList<Integer> costs) {
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, Integer> costMap = new HashMap<>();
        for(int i=0;i<ids.size();i++){
        	parentMap.put(ids.get(i), parents.get(i));
        	costMap.put(ids.get(i), costs.get(i));
        }
        int maxCost = 0;
        for(int j=0;j<ids.size();j++){
        	int id = ids.get(j);
        	int tempCost = 0;
        	while(id != 0){
        		tempCost+=costMap.get(id);
        		id = parentMap.get(id);
        	}
        	System.out.println("tempCost:"+tempCost);
        	if(tempCost > maxCost)
        		maxCost = tempCost;
        }
        return maxCost;
    }
}
