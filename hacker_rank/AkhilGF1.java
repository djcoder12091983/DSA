import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AkhilGF1 {
    
    // https://stackoverflow.com/questions/1522825/calculating-sum-of-geometric-series-mod-m
    static long solve(long n, long m) {
        long t = 1;
        long e = 10 % m;
        long total = 0;
        while(n > 0) {
            if(n % 2 != 0) {
                total = (e * total + t) % m;
            }
            t = ((e + 1) * t) % m;
            e = (e * e) % m;
            n = n / 2;
        }
        return total;
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer tokens = new StringTokenizer(reader.readLine());
            long N = Long.parseLong(tokens.nextToken());
            long M = Long.parseLong(tokens.nextToken());
            
            System.out.println(solve(N, M));
        }
        reader.close();
    }
}