import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LittlePandaPower {
	
	static long[] gcdExtended(long a, long b)
	{
		long xy[] = new long[2];
	    // base Case
	    if (a == 0) {
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
	
	// power by squaring
	static long power(long x, long y, long m)
	{
	    long res = 1;
	    x = x % m; 
	 
	    while(y > 0) {
	        // y is odd, multiply x with result
	        if(y%2 != 0) {
	            res = (res*x) % m;
	        }
	 
	        // y must be even now
	        x = (x*x) % m;
	        y = y>>1; // y = y/2
	    }
	    return res;
	}
	
	// gets modulo
	static long get(long a, long b, long x) {
		if(b >= 0) {
			// normal case
			return power(a, b, x);
		} else {
			// inverse case, a & x co-prime
			// so gcdExtended will be used to get inverse modulo
			long xy[] = gcdExtended(a, x);
			long inv = xy[0];
			if(inv < 0) {
				// negative
				inv = (inv + (((inv * -1) / x) + 1) * x) % x;
			} else {
				// positive
				inv %= x;
			}
			b = b*-1;
			if(b == 1) {
				return inv;
			} else {
				return power(inv, b, x);
			}
		}
	}
	
	// run
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(reader.readLine());
		for(int i=0; i<tc; i++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			long a = Long.parseLong(tokens.nextToken());
			long b = Long.parseLong(tokens.nextToken());
			long x = Long.parseLong(tokens.nextToken());
			
			System.out.println(get(a, b, x));
		}
		reader.close();
		//System.out.println(get(4, -1, 5));
	}
}