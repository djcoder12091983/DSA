public class Solution {
    int c;
    public int solve(ArrayList<Integer> A) {
        if(A.size() == 1) {
            return 0;
        }
    generate(A, 0);
    return c;
    }
    public void generate(ArrayList<Integer> A, int pos){
        if(pos==A.size()){
            //System.out.println(A);
          c++;
          return;
        }
        HashSet<Integer> unique = new HashSet<>();
        for(int i=pos; i<A.size();i++){
            if(!unique.add(A.get(i))) {
                continue; // avoid duplicate permutations
            }
            Collections.swap(A, i, pos);
            boolean flag = true;
            if(pos > 0) {
                int sum = A.get(pos) + A.get(pos - 1);
                int x = (int)Math.sqrt(sum);
                flag = x * x == sum;
            }
            if(flag) {
                generate(A, pos + 1);
            }
           
           Collections.swap(A, i, pos);
        }
    }
}