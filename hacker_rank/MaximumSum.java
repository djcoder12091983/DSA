import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maximumSum function below.
    static long maximumSum(long[] a, long m) {
        if(m == 1) {
            // base case
            //always 0
            return 0;
        }
        
        // assuming modulo operator is distributive over addition
        // the ways exactly k subarray sum works that same way it will work to find
        // maximum remainder
        
        // binary search on left side while doing prefix modulo sum
        // to find closest remainder to maximize remainder
        TreeSet<Long> prefixmodulosum = new TreeSet<>(); // BST
        prefixmodulosum.add(0L); // to cover whole subarray
        
        int l = a.length;
        long prev = 0;
        long max = Long.MIN_VALUE; // max modulo
        for(int i = 0; i < l; i++) {
            long t = prev + a[i];
            if(t >= m) {
                // modulo reduction
                t %= m;
            }
            // we always try to find t + 1
            long required = t + 1;
            
            // try to find closest uppper one if exists
            Long ceil = prefixmodulosum.ceiling(required);
            long rem;
            if(ceil != null) {
                // found
                rem = t - ceil;
                if(rem < 0) {
                    // minus modulo
                    rem += m;
                }
            } else {
                // remainder itself
                rem = t;
            }

            if(max < rem) {
                // new maximum remainder
                max = rem;
            }

            // add to BST
            prefixmodulosum.add(t);
            prev = t;
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            long m = Long.parseLong(nm[1]);

            long[] a = new long[n];

            String[] aItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                long aItem = Long.parseLong(aItems[i]);
                a[i] = aItem;
            }

            long result = maximumSum(a, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
