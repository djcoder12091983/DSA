/**
 * Definition for binary tree
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
// which path X exists in BST
vector<int> path(TreeNode* root, int X) {
    TreeNode *node = root;
    vector<int> track;
    while(node) {
        track.push_back(node->val); // path
        if(node->val == X) {
            break;
        } else if(node->val < X) {
            // take right substree
            node = node->right;
        } else {
            // take left subtree
            node = node->left;
        }
    }
    
    return track;
}

int Solution::solve(TreeNode* root, int B, int C) {
    vector<int> path1 = path(root, B);
    vector<int> path2 = path(root, C);
    
    int N1 = path1.size(), N2 = path2.size();
    int i = 0;
    while(i < N1 && i < N2) {
        if(path1[i] != path2[i]) {
            break;
        }
        i++;
    }
    return (N1 - i) + (N2 - i); // path difference
}
