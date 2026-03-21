import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ManasaAndFactorials {
    
    static long solve(long x) {
        long c = 1;
        long i = 5;
        while(true) {
            long prevc = c;
            long nexti = i * 5; // multiple of 5
            long rem = nexti - i;
            // count with rem
            for(long k = i; k >= 5; k /= 5) {
                c += rem / k;
            }
            c += 1;
            
            if(c > x) {
                // restore previous count
                c = prevc;
                break;
            } else if(c == x) {
                // best case
                return nexti;
            }
            
            i = nexti;
        }
        
        long requiredc = x - c;
        long numerator = 0;
        for(long k = i; k >= 5; k /= 5) {
            numerator += i / k;
        }
        long expected = new BigDecimal(requiredc).multiply(new BigDecimal(i))
                .divide(new BigDecimal(numerator), RoundingMode.HALF_UP).longValue();
        long rem = expected % 5;
        if(rem != 0) {
            expected -= rem;
        }
        //System.out.println(expected + " " + i + " " + requiredc);
        long trial = expected;
        // now try +5
        while(true) {
            long required = 0;
            for(long k = i; k >= 5; k /= 5) {
                required += trial / k;
            }
            if(required >= requiredc) {
                break;
            }
            trial += 5;
        }
        
        return trial + i;
    }
    
    public static void main(String[] args) throws Exception {
        //long start = System.currentTimeMillis();
        //System.out.println(solve(4264777785391999L));
        //long end = System.currentTimeMillis();
        //System.out.println(end - start);
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for(int i=0; i<n; i++) {
            long x = Long.parseLong(reader.readLine());
            System.out.println(solve(x));
        }
        reader.close();
    }
}