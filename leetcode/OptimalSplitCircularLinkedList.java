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
    public ListNode[] splitCircularLinkedList(ListNode list) {
        ListNode head1 = list;

        // find middle
        ListNode slow = head1.next, fast = head1.next.next;
        ListNode prev = head1;
        while(fast != head1 && fast.next != head1) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if(fast == head1) {
            // even length
            prev.next = head1;
        } else {
            // odd length
            ListNode temp = slow.next;
            slow.next = head1;
            slow = temp; // second list will start middle + 1
        }
        

        // we will start from slow to create second half circular linked list
        ListNode head2 = slow;
        
        while(slow.next != head1) {
            slow = slow.next;
        }
        slow.next = head2;

        return new ListNode[]{head1, head2};
    }
}