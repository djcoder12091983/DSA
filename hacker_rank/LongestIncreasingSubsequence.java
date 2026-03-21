import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the longestIncreasingSubsequence function below.
    static int longestIncreasingSubsequence(int[] arr) {

        int max = 1;

        // save previus result
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0); // base case

        // count mapping to remove duplicate count
        Map<Integer, Integer> count = new HashMap<>();

        for(int a : arr) {
            int floor = dp.floorKey(a - 1); // strictly descending floor
            int c = dp.get(floor);
            int nc = c + 1; // new sequence count
            if(count.containsKey(nc)) {
                // remove old entry if exists
                int old = count.get(nc);
                dp.remove(old);
            }

            if(nc > max) {
                // new max
                max = nc;
            }

            // both way mapping
            dp.put(a, nc);
            count.put(nc, a);
        }

        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        int result = longestIncreasingSubsequence(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
