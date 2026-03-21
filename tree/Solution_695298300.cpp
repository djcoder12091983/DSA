/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    
    int ans = 0;
    
    // first values is sum, second is nodes count
    pair<int, int> count(TreeNode *node) {
        if(node == NULL) {
            // base condition
            return make_pair(0, 0);
        }
        // recursive calls
        pair<int, int> left = count(node->left);
        pair<int, int> right = count(node->right);
        
        // merge
        int sum = left.first + node->val + right.first; // sum
        int nodes = left.second + 1 + right.second; // count nodes
        
        if(sum / nodes == node->val) {
            // valid node
            ans++;
        }
        
        return make_pair(sum, nodes);
    }
    
    int averageOfSubtree(TreeNode* root) {
        ans = 0;
        count(root);
        
        return ans;
    }
};