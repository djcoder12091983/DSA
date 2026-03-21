/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
ListNode* Solution::reverseList(ListNode* head, int B) {
    // let's make it simple
    int i = 0;
    ListNode *node = head;
    ListNode *new_head;
    // first split
    stack<int> track;
    while(i++ < B) {
        track.push(node->val);
        node = node->next;
    }
    // reverse it and modify new head
    new_head = new ListNode(track.top());
    track.pop();
    ListNode *new_node = new_head;
    while(!track.empty()) {
        new_node->next = new ListNode(track.top());
        track.pop();
        new_node = new_node->next;
    }
    // remaining halves
    while(node) {
        i = 0;
        while(i++ < B) {
            track.push(node->val);
            node = node->next;
        }
        // reverse it
        while(!track.empty()) {
            new_node->next = new ListNode(track.top());
            track.pop();
            new_node = new_node->next;
        }
    }
    
    return new_head;
}
