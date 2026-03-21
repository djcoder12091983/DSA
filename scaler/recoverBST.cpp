/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
TreeNode *previous = NULL, *first = NULL, *second = NULL;

void recursive_inorder(TreeNode *node) {
    if(node == NULL) {
        return;
    }
    recursive_inorder(node->left);
    if(previous != NULL) {
        if(first == NULL) {
            if(node->val < previous->val) {
                // first sequence break
                first = previous;
            }
        } else if(first && second == NULL) {
            if(node->val > first->val) {
                // suitable position to fit second
                second = previous;
            }
        }
    }
    previous = node;
    // recursive right node scan
    recursive_inorder(node->right);
}

vector<int> Solution::recoverTree(TreeNode* root) {
    previous = NULL, first = NULL, second = NULL; // reset for every test case
    recursive_inorder(root);
    if(second == NULL) {
        // still second not found
        // so last is
        second = previous;
    }
    
    // return swapped nodes
    vector<int> ans;
    ans.push_back(min(first->val, second->val));
    ans.push_back(max(first->val, second->val));
    return ans;
}
