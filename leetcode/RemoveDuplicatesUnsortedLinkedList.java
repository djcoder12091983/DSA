// https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/description/
// TODO -- can we think of doing in place - O(1) space

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
    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        Map<Integer, Integer> track = new HashMap<>();
        ListNode node = head;
        while(node != null) {
            int x = node.val;
            track.put(x, track.getOrDefault(x, 0) + 1);
            node = node.next;
        }

        node = head;
        ListNode dummy = new ListNode(-1);
        ListNode node1 = dummy;

        while(node != null) {
            if(track.get(node.val) == 1) {
                // unique
                node1.next = new ListNode(node.val);
                node1 = node1.next;
            }

            node = node.next;
        }

        return dummy.next;
    }
}