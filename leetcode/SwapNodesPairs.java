// https://leetcode.com/problems/swap-nodes-in-pairs/description/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = head;
        // we will swap only if we have pairs
        ListNode prev = dummy;
        while(node != null && node.next != null) {
            ListNode a = node;
            ListNode b = node.next;
            ListNode c = node.next.next;

            // swap pairs
            a.next = null;
            b.next = null;
            prev.next = b;
            b.next = a;

            // preparation for next swap
            a.next = c;
            prev = a;
            node = c;
        }

        return dummy.next;
    }
}