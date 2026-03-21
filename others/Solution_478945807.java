class Solution {
    public String smallestGoodBase(String n) {
        long a = Long.parseLong(n);
        long min_b=a-1;
        for(int i=2;i<=64;i++) //maximum no. of 1's are 64 in 10^18
        {
            long b = solution(a,i);
            if(b > 0){
                min_b=Math.min(min_b,b);
            }
        }
        return String.valueOf(min_b);
    }
    
    public long solution(long a,int d){
        long limit = (1L << 63)-1;
        long start=2;
        // here make limit^(1.0/(d - 1))
        long end =  Double.valueOf(Math.pow(limit,1.0/(d - 1))).longValue();
        
        while(start<=end)
        {
            long mid;
            mid = start + (end-start)/2;
            long sum=0L;
            long p = 1L;
            for(int i=0;i<d;i++){
               sum += p;
               p *= mid; // power computation
            }
            if(sum == a){
                return mid;  
            }else if(sum > a){
                end=mid-1;
            }
            else{
                start=mid+1;
            }
            
        }
        return 0;
    }
    
    
}