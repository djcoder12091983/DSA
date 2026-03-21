import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the biggerIsGreater function below.
    static String biggerIsGreater(String w) {
        int l = w.length();
        int i;
        boolean f = false;
        List<Character> t= new ArrayList<Character>();
        for(i = l - 1; i >= 1; i--) {
            char ch1 = w.charAt(i);
            char ch2 = w.charAt(i - 1);
            t.add(ch1);
            if(ch2 < ch1) {
                f= true;
                break;
            }
        }
        if(!f) {
            // not possible
            return "no answer";
        }
        // possible
        i--;
        char point = w.charAt(i);
        t.add(point);
        Collections.sort(t);
        StringBuilder next = new StringBuilder(w.substring(0, i));
        int pos = i;
        f = false;
        l = t.size();
        for(i = 0; i < l;) {
            char ch = t.get(i);
            if(!f && ch == point) {
                // select closed to point
                if(t.get(i + 1) > ch) {
                    i++;
                    next.insert(pos, t.get(i));
                    f = true;
                }
            }
            next.append(ch);
            i++;
        }
        return next.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();

            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
