// https://leetcode.com/problems/smallest-common-region/description/

class Solution {

    // LCA problem
    // TODO may need to think if go with TREE class and all
    // how to find ROOT easily
    /*
    class TreeNode {
        String label;
        List<String> children = new ArrayList<>();

        TreeNode(String label) {
            this.label = label;
        }

        void add(Strong label) {
            this.children.add(label); // asumking all are unique
        }
    }
    */

    // rather we can think of maintaining parent child
    // then we can from two region we can keep on travelling up and find the LCA

    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        HashMap<String, String> parent = new HashMap<>();
        for(List<String> region : regions) {
            int N = region.size();
            for(int i = 1; i < N; i++) {
                // assumed once chiild can have one parent
                parent.put(region.get(i), region.get(0)); // child to parent
            }
        }

        // now backtrack
        List<String> l1 = new ArrayList<>();
        String label1 = region1;
        while(label1 != null) {
            l1.add(label1);
            label1 = parent.get(label1); // move to parent
        }

        List<String> l2 = new ArrayList<>();
        String label2 = region2;
        while(label2 != null) {
            l2.add(label2);
            label2 = parent.get(label2); // move to p arent
        }

        // now traverse parent list from opposite side then track lowest common
        String ans = "";
        int N = l1.size(), M = l2.size();

        int i = N - 1, j = M - 1;
        while(i >= 0 && j >= 0) {
            if(!l1.get(i).equals(l2.get(j))) {
                // stop the process we have found our lowest common
                break;
            }
            ans = l1.get(i);
            i--;
            j--;
        }

        return ans;
    }
}