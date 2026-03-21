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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    // prev mid
    ListNode mid(ListNode head) {
        ListNode prev = null;
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null) {
            prev = slow; // previous node
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }

        if(head.next == null) {
            // leaf
            return new TreeNode(head.val);
        }

        if(head.next.next == null) {
            // two nodes
            TreeNode root = new TreeNode(head.val);

            root.left = null;
            root.right = new TreeNode(head.next.val);

            // disconnect
            head.next = null;

            return root;
        }

        ListNode left = head;
        ListNode prevmid = mid(head);
        ListNode t = head;
        /*while(t != null) {
            System.out.print(t.val + " => ");
            t = t.next;
        }
        if(prevmid != null) {
            System.out.println("Mid: " + prevmid.val);
        }*/
        ListNode right = prevmid.next.next;
        TreeNode root = new TreeNode(prevmid.next.val);

        // disconnect
        prevmid.next.next = null;
        prevmid.next = null;

        root.left = sortedListToBST(left);
        root.right = sortedListToBST(right);

        return root;

    }
}