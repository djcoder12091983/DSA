import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MehtaLaziness {
	
	static List<Long> primefactors(long n) {
		int limit = Double.valueOf(Math.ceil(Math.sqrt(n))).intValue();
		List<Long> factors = new LinkedList<Long>();
		long c = 1;
		boolean found = false;
		while(++c<=limit) {
			if(n%c==0) {
				long q = n/c;
				factors.add(c);
				if(q != 1) {
					// exclude number itself
					// next recursive call
					factors.addAll(primefactors(q));
				}
				found = true;
				break;
			}
		}
		if(!found) {
			// no factors found
			factors.add(n);
		}
		return factors;
	}
	
	static boolean isPerfectSquare(long x)
	{  
	  double sqrt = Math.sqrt(x);
	  // If square root is an integer
	  return (sqrt - Math.floor(sqrt)) == 0;
	}
	
	static int gcd(int a, int b) {
		if(a<b) {
			// exchange
			int t = a;
			a = b;
			b = t;
		}
		int r;
		while((r = a%b) != 0) {
			r = a%b;
			a = b;
			b = r;
		}
		return b;
	}
	
	static int[] count(List<Long> factors, long n) {
		Set<Long> divisors = new HashSet<Long>(2);
		int l = factors.size();
		if(l == 1) {
			divisors.add(factors.get(0));
		} else {
			long f = factors.get(0);
			long s = factors.get(1);
			// first and second divisors
			divisors.add(f);
			divisors.add(s);
			divisors.add(f*s);
			int i = 1;
			while(++i<l) {
				long t = factors.get(i);
				// rest divisors
				Set<Long> newcurrent = new HashSet<Long>(2);
				newcurrent.add(t);
				for(long c : divisors) {
					newcurrent.add(c*t); // divisor
				}
				divisors.addAll(newcurrent); // merge
			}
		}
		
		int evenc = 0;
		for(long divisor : divisors) {
			if(divisor != n && divisor%2 == 0 && isPerfectSquare(divisor)) {
				// even perfect square and avoid number itself
				evenc++;
			}
		}

		int count = divisors.size();
		if(evenc == 0) {
			return new int[]{0, count};
		}
		
		int gcd = gcd(count, evenc);
		return new int[]{evenc/gcd, count/gcd}; // divide by gcd
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(reader.readLine());
		for(int i=0; i<tc; i++) {
			long n = Long.parseLong(reader.readLine());
			int[] count = count(primefactors(n), n);
			if(count[0] == 0) {
				// zero probability
				System.out.println(0);
			} else {
				System.out.println(count[0] + "/" + count[1]);
			}
		}
		reader.close();
	}
}