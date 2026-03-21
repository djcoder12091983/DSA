import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SherlockAndSquare {
	
	static long MODULO = Double.valueOf(Math.pow(10, 9)+7).longValue();
	
	static long length(long sec)
	{
		sec++; // one sec ahead
		long x = 2;
	    long p = 1;
	    while (sec>0)
	    {
	        // odd, multiply with result
	        if ((sec&1) == 1) {
	            p = (p*x)%MODULO;
	        }
	 
	        sec=sec>>1;
	        x =(x*x)%MODULO; // keep on multiplying  
	    }
	    return (p+2)%MODULO;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(reader.readLine());
		for(int i=0; i<tc; i++) {
			long n = Integer.parseInt(reader.readLine());
			System.out.println(length(n));
		}
		reader.close();
	}

}