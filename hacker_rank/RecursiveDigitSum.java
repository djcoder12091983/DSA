import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // recursion to find super digit
    static int find(String s) {
        int l = s.length();
        if(l > 1) {
            // recursion
            long sum = 0;
            for(int i = 0; i < l; i++) {
                sum += s.charAt(i) - 48;
            }
            String news = String.valueOf(sum);
            return find(news);
        } else {
            // nore more recursion needed
            return Integer.parseInt(s);
        }
    }

    // Complete the superDigit function below.
    static int superDigit(String s, int k) {
        int l = s.length();
        long sum = 0;
        for(int i = 0; i < l; i++) {
            sum += s.charAt(i) - 48;
        }
        sum *= k; // k times

        // now solve
        return find(String.valueOf(sum));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        int result = superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
