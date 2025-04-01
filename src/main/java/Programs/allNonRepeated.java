package Programs;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class allNonRepeated {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String x = "SILLSISPIDEER";
		char y[] = x.toCharArray();
		int size = y.length;
		Map<Character, Integer> map = new LinkedHashMap<>();
		int i = 0;
		while (i != size) {
			if (map.containsKey(y[i]) == false) {
				map.put(y[i], 1);
			} else {
				int oldval = map.get(y[i]);
				int newval = oldval + 1;
				map.put(y[i], newval);
			}
			++i;
		}
		String res = "";
		Set<Entry<Character, Integer>> hmap = map.entrySet();
		for (Entry<Character, Integer> data : hmap) {
			res = res + data.getKey();
		}

		System.out.println(res);

	}

}
