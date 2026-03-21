/*
Definition for binary tree
struct TreeNode {
      int val;
      TreeNode *left;
      TreeNode *right;
      TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
*/
TreeNode *head, *previous;
void recursive_inorder(TreeNode *node) {
    if(node == NULL) {
        return;
    }
    recursive_inorder(node->left);
    if(previous == NULL) {
        // head
        head = node;
    } else {
        // update next and previous pointer
        node->left = previous;
        previous->right = node;
    }
    previous = node;
    recursive_inorder(node->right);
}

TreeNode* solve(TreeNode *root) {
    head = NULL;
    previous = NULL;
    recursive_inorder(root);
    
    // make it circular
    if(head != NULL) {
        head->left = previous;
        previous->right = head;
    }
    return head; // return head node
}