import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // check for anagram
    static boolean anagram(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        if(map1 == null && map2 == null) {
            return true;
        } else if(map1 == null || map2 == null) {
            return false;
        }
        
        for(char key : map1.keySet()) {
            int f1 = map1.get(key);
            if(map2.containsKey(key)) {
                int f2 = map2.get(key);
                if(f1 != f2) {
                    // not possible
                    return false;
                }
                // after done remove the key from map2
                map2.remove(key);
            } else {
                // not possible
                return false;
            }
        }
        // all odd cases overcome
        // now check if any extra characters remaining in map2 or not
        return map2.size() == 0;
    }

    // prepares character frequency map
    // start and end inclusive
    static Map<Character, Integer> preparemap(String str, int s, int e) {
        int l = str.length();
        if(s <= e && e < l) {
            // valid position
            Map<Character, Integer> f = new HashMap<>();
            for(int i = s; i <= e; i++) {
                char ch = str.charAt(i);
                if(f.containsKey(ch)) {
                    // incremental
                    f.put(ch, f.get(ch) + 1);
                } else {
                    // first time
                    f.put(ch, 1);
                }
            }
            return f;
        } else {
            // not possible
            return null;
        }
    }

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {

        int l = s.length();
        int c = 0;

        // check for all substrings
        for(int i = 1; i < l; i++) {

            // check all substrings
            for(int j = 0; j <= l - i; j++) {
                Map<Character, Integer> map1 = preparemap(s, j, j + i - 1); // map1
                for(int k = j + 1; k <= l - i; k++) {
                    // map2
                    Map<Character, Integer> map2 = preparemap(s, k, k + i - 1);
                    if(anagram(map1, map2)) {
                        // anagram count
                        c++;
                    }
                }
            }
        }

        return c; // count
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
