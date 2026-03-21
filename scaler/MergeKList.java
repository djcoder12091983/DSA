/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    public ListNode mergeKLists(ArrayList<ListNode> A) {
        // note: idea is taking minimum from each list and add to min heap then keep on popping
        // and next element to be pushed to heap from where popping taken place
        Queue<ListNode> min = new PriorityQueue<>(new Comparator<ListNode>() {
            // comparison logic
            @Override
            public int compare(ListNode node1, ListNode node2) {
                return Integer.valueOf(node1.val).compareTo(node2.val);
            }
        });
        // push all mins into heaps
        int N = A.size();
        for(int i = 0; i < N; i++) {
            ListNode node = A.get(i);
            min.add(node);
        }
        // sort
        ListNode top = min.poll();
        ListNode head = new ListNode(top.val);
        if(top.next != null) {
            // next to add into heap
            min.add(top.next);
        }
        ListNode node = head;
        while(!min.isEmpty()) {
            top = min.poll();
            node.next = new ListNode(top.val); // add to sorted list
            if(top.next != null) {
                // next to add into heap
                min.add(top.next);
            }
            node = node.next;
        }
        
        return head;
    }
}
