// https://leetcode.com/problems/closest-binary-search-tree-value-ii/
// TODO: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)!
// TODO may need to solve without using extra space or less than O(N)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    void sort(TreeNode node, List<Integer> data) {
        if(node != null) {
            sort(node.left, data);

            data.add(node.val);

            sort(node.right, data);
        }
    }

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // TODO need to think how BST itself alone will help to solve this

        // the idea to solve this like do inorder traversal and find insertion position of traget
        // then take k values on the left and same on right then find top K

        List<Integer> data = new ArrayList<>();
        sort(root, data);

        //System.out.println(data);

        int N = data.size();
        int l = 0, r = N - 1;

        List<Integer> ans = new ArrayList<>();

        // find insertion position
        int pos = 0;
        while(l <= r) {
            int mid = (l + r) / 2;
            if(data.get(mid) > target) {
                r = mid - 1;
            } else {
                pos = mid;
                l = mid + 1;
            }
        }

        // get k items from left and same from right then find top k closest items

        //System.out.println("Insertion Position: " + pos);
        
        int p1 = pos, p2 = pos + 1;
        int i = 0;
        
        while(i < k && p1 >= 0 && p2 < N) {
            double d1 = target - data.get(p1);
            double d2 = data.get(p2) - target;

            if(d1 <= d2) {
                ans.add(data.get(p1));
                p1--;
            } else {
                ans.add(data.get(p2));
                p2++;
            }

            i++;
        }

        while(i < k && p1 >= 0) {
            ans.add(data.get(p1));
            p1--;
            i++;
        }

        while(i < k && p2 < N) {
            ans.add(data.get(p2));
            p2++;
            i++;
        }

        return ans;
    }
}