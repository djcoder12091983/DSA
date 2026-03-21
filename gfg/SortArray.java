// { Driver Code Starts
//Initial Template for Java

/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

import java.util.HashMap; 
import java.util.Map; 
import java.util.Map.Entry; 


 // } Driver Code Ends
//User function Template for Java

class Solution{
    // A1[] : the input array-1
    // N : size of the array A1[]
    // A2[] : the input array-2
    // M : size of the array A2[]
    
    //Function to sort an array according to the other array.
    public static int[] sortA1ByA2(int A1[], int N, int A2[], int M)
    {
        LinkedHashSet<Integer> uniqueA2 = new LinkedHashSet<>();
        for(int x : A2) {
            // considering first occurence
            uniqueA2.add(x);
        }
        
        ArrayList<Integer> remaining = new ArrayList<>();
        HashMap<Integer, Integer> frequency = new HashMap<>();
        for(int x : A1) {
            if(!uniqueA2.contains(x)) {
                // filtering out
                remaining.add(x);
            } else {
                if(frequency.containsKey(x)) {
                    frequency.put(x, frequency.get(x) + 1);
                } else {
                    frequency.put(x, 1);
                }
            }
        }
        
        int[] ans = new int[A1.length];
        int idx = 0;
        for(int x : uniqueA2) {
            // first maintain the relatibe order same as A2
            if(frequency.containsKey(x)) {
                int c = frequency.get(x);
                for(int i = 0; i < c; i++) {
                    ans[idx++] = x;
                }
            }
        }
        // sort remaining
        Collections.sort(remaining);
        for(int x : remaining) {
            ans[idx++] = x;
        }
        
        return ans;
    }
}



// { Driver Code Starts.
class Main {
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		
		int t=sc.nextInt();
		
		while(t-->0)
		{
		    int n=sc.nextInt();
		    int m=sc.nextInt();
		    int a1[]=new int[n];
		    int a2[]=new int[m];
		    
		    for(int i=0;i<n;i++)
		    a1[i]=sc.nextInt();
		    
		    for(int i=0;i<m;i++)
		    a2[i]=sc.nextInt();
		    Solution ob=new Solution();
		    a1 = ob.sortA1ByA2(a1,n,a2,m);
		    for(int x:a1)
		    System.out.print(x+" ");
		    
		    System.out.println();
		}
	}
	

}


  // } Driver Code Ends