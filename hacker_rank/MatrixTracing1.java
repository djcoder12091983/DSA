import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MatrixTracing1 {
	
	static long M = 1000000007; // 10e9+7
	
	static long[] gcdExtended(long a, long b)
	{
		long xy[] = new long[2];
	    // base Case
	    if (a == 0)
	    {
	        xy[0] = 0;
	        xy[1] = 1;
	        return xy;
	    }
	 
	    long xy1[] = gcdExtended(b%a, a);
	 
	    // update x and y using results of recursive
	    xy[0] = xy1[1] - (b/a) * xy1[0];
	    xy[1] = xy1[0];
	 
	    return xy;
	}
	
	static long nCr(long n , long r) {
		long denom1 = n-r;
		// try to make denominator lesser so that numerator becomes less
		// ex: 10C2 we choose 2 as denominator so that we have numerator as 10x9
		long denom = denom1 < r ? denom1 : r;
		// numerator modulo op.
		long num = n-denom;
		long res = 1;
		for(long i=n; i>num; i--) {
			res *= i;
			if(res > M) {
				// reduce to modulo
				res %= M;
			}
		}
		// denominator modulo inverse
		long f = 1;
		for(long i=2; i<=denom; i++) {
			f *= i;
			if(f > M || i == denom) {
				// reduced to modulo inverse
				long xy[] = gcdExtended(f, M);
				long inv = xy[0];
				res *= inv; // multiply with modulo inverse
				if(res < 0) {
					// negative
					res = (res + (((res * -1) / M) + 1) * M) % M;
				} else {
					// positive
					// reduce to modulo
					res %= M;
				}
				f = 1; // reset
			}
		}
		return res;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(reader.readLine());
		for(int i=0; i<tc; i++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			long m = Long.parseLong(tokens.nextToken());
			long n = Long.parseLong(tokens.nextToken());
			System.out.println(nCr(m+n-2, n-1)); // (m+n-2)C(n-1)
		}
		reader.close();
	}
}