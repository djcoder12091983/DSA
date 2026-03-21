import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrings function below.
    static long substrings(String s) {
        long MOD = 1000000000+7;
        long res = 0;
        int l = s.length();
        long f = 1;
        for(int i = l-1; i >= 0; i--) {
            res = (res + (s.charAt(i) - 48) * f * (i + 1)) % MOD;
            f = (f * 10 +1) % MOD;
        }
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String n = scanner.nextLine();

        long result = substrings(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
