import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class Easysum {
	
	static long sum(BigDecimal limit, BigDecimal mod) {
		BigDecimal divides[] = limit.divideAndRemainder(mod);
		BigDecimal q = divides[0];
		BigDecimal r = divides[1];
		// q*(mod*(modl-1))/2
		BigDecimal sum = q.multiply(mod.multiply(mod.subtract(new BigDecimal(1)))).divide(new BigDecimal(2));
		if(r.longValue() > 0) {
			// r(r-1)/2
			BigDecimal sum1 = r.multiply(r.add(new BigDecimal(1))).divide(new BigDecimal(2));
			// sum 
			return sum.add(sum1).longValue();
		} else {
			return sum.longValue();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.out.println(sum(new BigDecimal(10), new BigDecimal(5)));
		// System.setIn(new FileInputStream("/home/dspace/debasis/NDL/HackerRank/data/es.input03.txt"));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(reader.readLine());
		for(int i=0; i<tc; i++) {
			String tokens[] = reader.readLine().split(" ");
			System.out.println(sum(new BigDecimal(tokens[0]), new BigDecimal(tokens[1])));
		}
		reader.close();
	}

}