import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
    static boolean anagram(int f1[], int f2[]) {
        for(int i = 0; i < 26; i++) {
            if(f1[i] != f2[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    static List<Integer> solve(String S, String P) {
        int N = S.length();
        int M = P.length();
        
        List<Integer> ans = new ArrayList<>();
        
        if(M > N) {
            // not possible
            ans.add(-1);
            return ans;
        }
        
        int pf[] = new int[26]; // P character frequency
        Arrays.fill(pf, 0);
        
        for(int i = 0; i < M; i++) {
            pf[P.charAt(i) - 'a']++;
        }
        
        // now slide window
        int i = 0, j = 0;
        int sf[] = new int[26];
        Arrays.fill(sf, 0);
        
        while(j < M) {
            sf[S.charAt(j) - 'a']++;
            j++;
        }
        
        if(anagram(pf, sf)) {
            ans.add(i);
        }
        
        while(j < N) {
            sf[S.charAt(i) - 'a']--;
            sf[S.charAt(j) - 'a']++;
            
            i++;
            j++;
            
            if(anagram(pf, sf)) {
                ans.add(i);
            }
        }
        
        if(ans.isEmpty()) {
            ans.add(-1); // not such index found
        }
        
        return ans;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String P = br.readLine();
        
        List<Integer> ans = solve(S, P);
        
        StringBuilder anstxt = new StringBuilder();
        for(int x : ans) {
            anstxt.append(x).append(' ');
        }
        System.out.println(anstxt);
	}
}
