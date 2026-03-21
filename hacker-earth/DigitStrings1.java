import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class DigitStrings1 {
	
	static final long MODULO = 1000000007;
	
	// 2 power modulo
	static long power2(int n) {
		long r = 1;
		long c = 2;
		
		while(n > 0) {
			if((n & 1) == 1) {
				r = (r * c) % MODULO;
			}
			
			n = n >> 1; // n/2
			c = (c * c) % MODULO;
		}
		
		return r;
	}
	
	// checks whether substring <= ktext
	// start, end both inclusive
	static boolean valid(String input, String ktext, int start, int end) {
		int l = ktext.length();
		int t = end - start + 1;
		if(t < l) {
			return true;
		} else if(t > l) {
			return false;
		}
		// equals, now check digit by digit
		for(int i = start; i < end; i++) {
			int ch1 = input.charAt(i);
			int ch2 = ktext.charAt(i - start);
			
			if(ch1 > ch2) {
				// invalid
				return false;
			} else if(ch1 < ch2) {
				// valid
				return true;
			}
		}
		// last digit to compare
		int ch1 = input.charAt(end);
		int ch2 = ktext.charAt(l - 1);
		return ch1 < ch2;
	}
	
	// count number of ways
	static long count(String input, long k) {
		int l = input.length();
		
		// k-text
		StringBuilder t = new StringBuilder();
		do {
			t.append(k % 10);
			k /= 10;
		} while(k > 0);
		
		String ktext = t.reverse().toString();
		
		// stored 0-ith range count-value
		long countdp[] = new long[l];
		for(int i = 0; i < l; i++) {
			boolean v = valid(input, ktext, 0, i);
			long dpc;
			if(v) {
				// all possible combinations will be valid
				dpc = power2(i);
			} else {
				// otherwise have to split
				long c = 0;
				for(int j = i; j > 0; j--) {
					boolean v1 = valid(input, ktext, j, i);
					if(v1) {
						// partition possible
						c += countdp[j - 1];
						if(c >= MODULO) {
							// reduce
							c %= MODULO;
						}
					} else {
						// otherwise not
						break;
					}
				}
				dpc = c;
			}
			countdp[i] = dpc;  // store result to DP
		}
		
		// result
		return countdp[l - 1];
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(reader.readLine());
		while(--t >= 0) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			// by pass n
			tokens.nextToken();
			long k = Long.valueOf(tokens.nextToken());
			
			// result
			System.out.println(count(reader.readLine(), k));
		}
		
		reader.close();
	}
}