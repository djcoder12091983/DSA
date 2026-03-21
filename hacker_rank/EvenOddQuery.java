import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EvenOddQuery {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int data[] = new int[n];
		StringTokenizer tokens = new StringTokenizer(reader.readLine());
		for(int i=0; i<n; i++) {
			data[i] = Integer.parseInt(tokens.nextToken());
		}
		int q = Integer.parseInt(reader.readLine());
		for(int i=0; i<q; i++) {
			tokens = new StringTokenizer(reader.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			// odd/even function
			String r;
			if(data[x-1]%2 == 0) {
				if(x+1 <= y&& data[x]==0) {
					r = "Odd";
				} else {
					r = "Even";
				}
			} else {
				r = "Odd";
			}
			System.out.println(r);
		}
		reader.close();
	}

}