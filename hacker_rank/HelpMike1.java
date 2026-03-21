import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class HelpMike1 {
	
	static long count(long n, int k) {
		
		BigDecimal d[] = new BigDecimal(n).divideAndRemainder(new BigDecimal(k));
		int dd = d[0].intValue();
		int drem = d[1].intValue();
		
		long rem[] = new long[k];
		for(int i = 0; i < k; i++) {
			rem[i] = dd;
		}
		if(drem > 0) {
			for(int i = 1; i <= drem; i++) {
				rem[i]++;
			}
		}
		
		long c = 0;
		c += (rem[0] * (rem[0] - 1)) / 2;
		boolean even = k % 2 == 0;
		int l = k/2;
		if(even) {
			c += (rem[l] * (rem[l] - 1)) / 2;
		} else {
			l++;
		}
		for(int i = 1; i < l; i++) {
			c += rem[i] * rem[k - i];
		}
		
		return c;
	}
	
	public static void main(String[] args) throws Exception {
		
		//System.out.println(count(46931070, 183));
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(reader.readLine());
		for(int i=0; i<tc; i++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			long n = Long.parseLong(tokens.nextToken());
			int k = Integer.parseInt(tokens.nextToken());
			System.out.println(count(n, k));
		}
		reader.close();
	}
}