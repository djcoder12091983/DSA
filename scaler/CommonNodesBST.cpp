/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
typedef long long int ll;

int Solution::solve(TreeNode* A, TreeNode* B) {
    TreeNode* node1 = A, *node2 = B;
    stack<TreeNode *> track1, track2;
    // iterative inorder traversal
    ll s = 0L;
    while(true) {
        if(node1) {
            track1.push(node1);
            node1 = node1->left;
        } else if(node2) {
            track2.push(node2);
            node2 = node2->left;
        } else if(!track1.empty() && !track2.empty()) {
            node1 = track1.top();
            node2 = track2.top();
            if(node1->val == node2->val) {
                s += node1->val; // sum common values
                track1.pop();
                track2.pop();
                
                node1 = node1->right;
                node2 = node2->right;
            } else if(node1->val < node2->val) {
                // move node1
                track1.pop();
                node1 = node1->right;
                
                node2 = NULL;
            } else {
                track2.pop();
                node2 = node2->right;
                
                node1 = NULL;
            }
        } else {
            // done
            break;
        }
    }
    return s % 1000000007;
}
