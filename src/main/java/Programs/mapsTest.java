package Programs;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class mapsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String x = "brown";
		char y[] = x.toCharArray();
		int size = y.length;
		Map<Character, Integer> map = new HashMap<>();
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
		Set<Entry<Character, Integer>> hmap = map.entrySet();
		for (Entry<Character, Integer> data : hmap) {
			if (data.getValue() > 1) {
				System.out.println("it doesn't contains all unique characters");
				System.exit(0);
			}
		}
		System.out.println("it contains all unique characters");
	}

}
