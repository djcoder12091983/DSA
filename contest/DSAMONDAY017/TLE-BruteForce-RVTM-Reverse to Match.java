import java.util.*;
import java.lang.*;
import java.io.*;

// TODO need to fix TLE

class Codechef
{
    static boolean validate(char s1[], char s2[], int l, int r) {
        // check whether l-r range S1 is reverse is same as S2
        int p1 = l, p2 = r;
        while(p1 <= r && p2 >= l) {
            if(s1[p1] != s2[p2]) {
                return false;
            }
            
            p1++;
            p2--;
        }
        
        // left
        for(int i = 0; i < l; i++) {
            if(s1[i] != s2[i]) {
                return false;
            }
        }
        
        int N = s1.length;
        // right
        for(int i = r + 1; i < N; i++) {
            if(s1[i] != s2[i]) {
                return false;
            }
        }
        
        return true;
    }
    
	public static void main (String[] args) throws java.lang.Exception
	{
		// will try BRUTE-FORCE
		// TODO need to fix TLE
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        
        char[] sch = S.toCharArray();
        char[] tch = T.toCharArray();
        
        int N = sch.length;
        // try possible pairs
        // TODO assume substring length would be > 1 -- need to clarify
        int c = 0;
        for(int i = 0; i < N; i++) {
            for(int j = i + 1; j < N; j++) {
                if(validate(sch, tch, i, j)) {
                    c++;
                }
            }
        }
        
        System.out.println(c);

	}
}
