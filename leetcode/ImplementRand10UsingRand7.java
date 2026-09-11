// https://leetcode.com/problems/implement-rand10-using-rand7

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Solution extends SolBase {

    // this is a kind of brute force approach
    // like calling rand7 2 times populate first 10 by combining two outcomes
    Set<Integer> first10 = new HashSet<>();
    Iterator<Integer> first10i = null;

    public int rand10() {
        if(first10i == null || first10i.hasNext() == false) {
            // not more elements to return
            // now we can repopulate the value from 1 to 10
            
            // generate first 7 numbers
            for(int i = 0; i < 7; i++) {
                first10.add(rand7());
            }
            // remaining 3
            for(int i = 0; i < 3; i++) {
                first10.add(7 + rand7() % 3 + 1);
            }

            first10i = first10.iterator();
        }

        return first10i.next();
    }
}