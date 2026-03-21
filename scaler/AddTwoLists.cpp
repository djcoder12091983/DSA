/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
void add_node(ListNode **new_head, ListNode **new_node, int v) {
    if(*new_head == NULL) {
        *new_head = new ListNode(v);
        *new_node = *new_head;
    } else {
        (*new_node)->next = new ListNode(v);
        *new_node = (*new_node)->next;
    }
}
ListNode* Solution::addTwoNumbers(ListNode* A, ListNode* B) {
    ListNode *C = NULL, *c_node;
    int carry = 0;
    ListNode *a_node = A, *b_node = B;
    while(a_node && b_node) {
        // add two digit
        int add = a_node->val + b_node->val + carry;
        carry = add / 10;
        add %= 10;
        add_node(&C, &c_node, add); // add to new list
        // move a and b
        a_node = a_node->next;
        b_node = b_node->next;
    }
    // remaining A if any
    while(a_node) {
        int add = a_node->val + carry;
        carry = add / 10;
        add %= 10;
        add_node(&C, &c_node, add); // add to new list
        // move a
        a_node = a_node->next;
    }
    // remaining B if any
    while(b_node) {
        int add = b_node->val + carry;
        carry = add / 10;
        add %= 10;
        add_node(&C, &c_node, add); // add to new list
        // move b
        b_node = b_node->next;
    }
    if(carry) {
        // carry exists
        add_node(&C, &c_node, carry); // add to new list
    }
    
    return C;
}
