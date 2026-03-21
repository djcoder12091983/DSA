/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
ListNode* Solution::solve(ListNode* head) {
    // note: let's try other than "hare tortoise" method
    unordered_set<ListNode *> track; // track address
    ListNode *node = head, *prev;
    while(node) {
        if(track.find(node) != track.end()) {
            // loop found
            prev->next = NULL; // break the loop
            break;
        }
        track.insert(node);
        prev = node;
        node = node->next;
    }
    return head;
}
