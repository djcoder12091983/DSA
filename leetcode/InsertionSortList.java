// https://leetcode.com/problems/insertion-sort-list/
// TODO need to solve in O(1) space

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
    public ListNode insertionSortList(ListNode head) {
        // easy way -- TODO -- can we solve in O(1) space
        // NEED to THINK!
        List<Integer> data = new ArrayList<>();
        ListNode node = head;
        while(node != null) {
            data.add(node.val);
            node = node.next;
        }

        Collections.sort(data);

        head = new ListNode(data.get(0)); // new head
        node = head;
        int i = 1;
        while(i < data.size()) {
            node.next = new ListNode(data.get(i));
            node = node.next;
            i++;
        }

        return head; // new head
    }
}