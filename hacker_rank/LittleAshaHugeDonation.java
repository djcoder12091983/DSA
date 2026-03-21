import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class LittleAshaHugeDonation {
    
    // http://www2.trinity.unimelb.edu.au/~rbroekst/MathX/Cubic%20Formula.pdf
    
    static long solve(long x) {
        
        double delta = new BigDecimal(3 * x).pow(2).divide(new BigDecimal(4))
                .subtract(new BigDecimal(1 / (64.0 * 27.0))).doubleValue();
        double q2 = (3 * x / 2.0);
        double deltaSqrt = Math.sqrt(delta);
        // System.out.println(delta);
        //System.out.println(q2);
        //System.out.println(deltaSqrt);
        double p1 = q2 + deltaSqrt;
        double p2 = q2 - deltaSqrt;
        //System.out.println(p1 + " " + p2);
        double root = Math.pow(p1, 1 / 3.0) + Math.pow(p2, 1 / 3.0) - 0.5;
        //System.out.println(root);
        
        return new Double(Math.floor(root + 0.00000999999)).longValue();
    }
    
    public static void main(String[] args) throws Exception {
        /*System.out.println(solve(1));
        System.out.println(solve(5));
        System.out.println(solve(13));*/
        //System.out.println(solve(4695275635L));
        //System.setIn(new FileInputStream("/home/dspace/debasis/NDL/HackerRank/data/lahd_input03.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(reader.readLine());
        for(int i=0; i<tc; i++) {
            long x = Long.parseLong(reader.readLine());
            System.out.println(solve(x));
        }
        reader.close();
    }
}