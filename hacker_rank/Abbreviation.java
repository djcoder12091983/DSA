import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // converts A to B by removing lowercase letters
    // and capitalizing lowercase letters
    static boolean abbreviate(String A, String B) {
        boolean f = true;
        // base cases
        int l = A.length();
        boolean dp[] = new boolean[l + 1];
        dp[0] = true;
        for(int i = 0; i < l; i++) {
            char ch = A.charAt(i);
            if(Character.isUpperCase(ch)) {
                // if upper case then for dp(0, i + 1) not possible 
                f = false;
            }
            dp[i + 1] = f;
        }
        
        // base cases
        int l1 = B.length();
        // try to figure out whether abbreviation is possible or not
        for(int i = 0; i < l1; i++) {
            
            // new dp
            boolean ndp[] = new boolean[l + 1];
            ndp[0] = false;
            
            for(int j = 0; j < l; j++) {
                char chb = B.charAt(i);
                char cha = A.charAt(j);
                
                boolean f1 = false;
                if(Character.isLowerCase(cha)) {
                    f1 = true;
                    // can capitalize
                    cha = Character.toUpperCase(cha);
                }
                
                if(chb == cha) {
                    // if chb = cha then dp(i, j) = dp(i - 1, j - 1) 
                    // ndp[j + 1] in case of lowercase
                    ndp[j + 1] = dp[j];
                    if(f1) {
                        ndp[j + 1] |= ndp[j];
                    }
                } else {
                    if(f1) {
                        // we can remove characters from A if it's lowercase
                        ndp[j + 1] = ndp[j];
                    } else {
                        // straight way not possible
                        ndp[j + 1] = false;
                    }
                }
            }
            
            // copy ndp to dp
            for(int j = 0; j <= l; j++) {
                dp[j] = ndp[j];
            }
        }
        
        return dp[l]; // result
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviate(a, b) ? "YES" : "NO";

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
