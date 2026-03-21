public class Solution {

    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {

        ans.clear();

        Collections.sort(A);
        generate(A, -1, new ArrayList<Integer>());

        return ans;
    }

    void generate(ArrayList<Integer> A, int idx, ArrayList<Integer> temp) {

        ans.add(new ArrayList<Integer>(temp));

        int N = A.size();
        for(int i = idx + 1; i < N; i++) {
            temp.add(A.get(i));
            generate(A, i, temp);
            temp.remove(temp.size() - 1);
        }
    }
}
