public class Solution {
    ArrayList<ArrayList<String>> res = new ArrayList<>();
    HashSet<Integer> col = new HashSet<>();
    HashSet<Integer> right = new HashSet<>();
    HashSet<Integer> left = new HashSet<>();
    public ArrayList<ArrayList<String>> solveNQueens(int A) {
        char[][] mat = new char[A][A];
        for(char[] row:mat){
            Arrays.fill(row, '.');
        }
        placeQueen(mat, 0, A);
        
        /*Comparator<ArrayList<String>> cmp = new Comparator<ArrayList<String>>(){
            @Override
            public int compare(ArrayList<String> P, ArrayList<String> Q){
                for(int i=0; i<P.size(); i++){
                    String s1 = P.get(i);
                    String s2 = Q.get(i);
                    
                    for(int j=0; j<s1.length(); j++){
                        char c1 = s1.charAt(j);
                        char c2 = s2.charAt(j);
                        if(c1 == c2){
                            continue;
                        } else{
                            return c2-c1;
                        }
                    }
                }
                return 0;
            }
        };
        Collections.sort(res, cmp);*/
        return res;
    }

    public void placeQueen(char[][] mat, int r, int N){
        if(r == N){
            ArrayList<String> placement = new ArrayList<>();
            for(int i=0; i<N; i++){
                StringBuilder temp = new StringBuilder();
                for(int j=0; j<N; j++){
                    temp.append(mat[i][j]);
                }
                placement.add(temp.toString());
            }
            res.add(placement);
            return;
        }

        for(int c=0; c<N; c++){
            if(!(col.contains(c)
                || right.contains(r-c)
                || left.contains(r+c)
            )){
                mat[r][c] = 'Q';
                col.add(c);
                right.add(r-c);
                left.add(r+c);

                placeQueen(mat, r+1, N);

                mat[r][c] = '.';
                col.remove(c);
                right.remove(r-c);
                left.remove(r+c);
            }
        }
    }
}