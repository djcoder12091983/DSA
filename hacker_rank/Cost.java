import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the cost function below.
    static int cost(int[] B) {
        int dp[][] = new int[2][2];
        // base case
        // two options, the number itself and 1
        dp[0][0] = B[0];
        dp[0][1] = 0;
        dp[1][0] = 1;
        dp[1][1] = 0;

        int l = B.length;
        for(int i = 1; i < l; i++) {
            int b1 = B[i]; // number itself
            int max1 = Math.max(dp[0][1] + Math.abs(dp[0][0] - b1), dp[1][1] + Math.abs(dp[1][0] - b1));
            // 1
            int b2 = 1;
            int max2 = Math.max(dp[0][1] + Math.abs(dp[0][0] - b2), dp[1][1] + Math.abs(dp[1][0] - b2));

            // save result into dp
            dp[0][0] = b1;
            dp[0][1] = max1;
            dp[1][0] = b2;
            dp[1][1] = max2;
        }

        return Math.max(dp[0][1], dp[1][1]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] B = new int[n];

            String[] BItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int BItem = Integer.parseInt(BItems[i]);
                B[i] = BItem;
            }

            int result = cost(B);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
