import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SparseArrays {
	
	Map<String, Integer> count = new HashMap<String, Integer>();
	
	void add(String word) {
		Integer c = count.get(word);
		if(c == null) {
			count.put(word, 1);
		} else {
			count.put(word, c.intValue()+1);
		}
	}
	
	int count(String word) {
		Integer c = count.get(word);
		if(c == null) {
			return 0;
		} else {
			return c.intValue();
		}
	}
	
	public static void main(String[] args) throws Exception {
		SparseArrays sa = new SparseArrays();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(reader.readLine());
		for(int i=0; i<n; i++) {
			sa.add(reader.readLine());
		}
		int q = Integer.valueOf(reader.readLine());
		for(int i=0; i<q; i++) {
			System.out.println(sa.count(reader.readLine()));
		}
		reader.close();
	}

}