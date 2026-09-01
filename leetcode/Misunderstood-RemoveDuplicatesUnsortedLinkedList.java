// https://leetcode.com/problems/remove-duplicates-from-an-unsorted-linked-list/
// TODO -- Question asked to remove all duplicates, it means if an element occurs more than once
// that should be deleted with entire occurences
// but this approach it will keep one occurance remaining it will delete

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
        Set<Integer> track = new HashSet<>();
        ListNode node = head;
        ListNode prev = null;
        while(node != null) {
            if(track.contains(node.val)) {
                prev.next = node.next; // remove the link
            } else {
                track.add(node.val);
                prev = node;
            }

            node = node.next;
        }

        return head;
    }
}