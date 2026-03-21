import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Superpowers2 {
    
    // 2^(2^exp)
    static long exponentiation(long exp, long N) {
        long base = 2; // base is 2
        while (exp > 0) {
            base = (base * base) % N;
            exp--;
        }
        return base % N;
    }
    
    public static void main(String[] args) throws Exception {
        //System.out.println(exponentiation(2, 7));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(reader.readLine());
        long a = Long.parseLong(tokens.nextToken());
        long b = Long.parseLong(tokens.nextToken());
        System.out.println(exponentiation(a, b));
        
        reader.close();
    }
}