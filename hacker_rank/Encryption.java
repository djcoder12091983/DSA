import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the encryption function below.
    static String encryption(String s) {
        StringBuilder t = new StringBuilder();
        int l = s.length();
        for(int i = 0; i < l; i++) {
            char ch = s.charAt(i);
            if(ch != ' ') {
                t.append(ch);
            }
        }
        l = t.length();
        //int f = new Double(Math.floor(Math.sqrt(l))).intValue();
        int c = new Double(Math.ceil(Math.sqrt(l))).intValue();
        List<StringBuilder> matrix = new ArrayList<StringBuilder>();
        int i = 0;
        while(i < l) {
            StringBuilder row = new StringBuilder();
            for(int j = 0; j < c && i < l; j++, i++) {
                row.append(t.charAt(i));
            }
            matrix.add(row);
        }
        StringBuilder res = new StringBuilder();
        int ml = matrix.size();
        for(i = 0; i < c; i++) {
            for(int j = 0; j < ml; j++) {
                StringBuilder ms = matrix.get(j);
                int msl = ms.length();
                if(i < msl) {
                    res.append(ms.charAt(i));
                } else {
                    break;
                }
            }
            res.append(" ");
        }
        return res.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
