import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SherlockPermutations {
	
	static long MODULO = Double.valueOf(Math.pow(10, 9)).longValue()+7;
	static int LIMIT = 2*1000;
	long C[][] = new long[LIMIT+1][LIMIT+1];
	
	void load() {
		// pre-populate table 
		for(int i=0; i<=LIMIT; i++) {
			for(int j=0; j<=i; j++) {
	            if (j==0 || j==i) {
	            	// best case
	                C[i][j] = 1;
	            } else {
	            	// use previous values
	            	C[i][j] = modulo(modulo(C[i-1][j-1]) + modulo(C[i-1][j]));
	            }
			}
		}
	}
	
	long get(int n, int r) {
		return C[n][r];
	}
	
	long modulo(long n) {
		if(n>MODULO) {
			return n%MODULO;
		} else {
			return n;
		}
	}
	
	long count(int zeros, int ones) {
		return get(zeros+ones-1, zeros);
	}
	
	public static void main(String[] args) throws Exception {
		
		/*String infile = "/home/dspace/debasis/NDL/HackerRank/data/sp.input02.txt";
		System.setIn(new FileInputStream(infile));*/
		
		SherlockPermutations sp = new SherlockPermutations();
		sp.load();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(reader.readLine());
		for(int i=0; i<tc; i++) {
			StringTokenizer tokens = new StringTokenizer(reader.readLine());
			int zeros = Integer.parseInt(tokens.nextToken());
			int ones = Integer.parseInt(tokens.nextToken());
			System.out.println(sp.count(zeros, ones));
		}
		reader.close();
	}

}