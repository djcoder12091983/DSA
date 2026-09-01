// https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/
// TreeMap -- ceilingKey won't help here -- TODO think!
// TODO the problem with brute force logic not the ceilingKey -- TODO - THINK!

class Solution {

    // track operations to make implementation easy
    void remove(TreeMap<Integer, Set<Integer>> track, int x, int idx) {
        Set<Integer> pos = track.get(x);
        pos.remove(idx);
        if(pos.size() == 0) {
            track.remove(x);
        }
    }

    int findFirst(TreeMap<Integer, Set<Integer>> track, int x) {
        Integer found = track.ceilingKey(x);
        if(found == null) {
            return -1;
        }

        Iterator<Integer> i = track.get(found).iterator();
        return i.next();
    }

    void add(TreeMap<Integer, Set<Integer>> track, int x, int idx) {
        Set<Integer> pos = track.get(x);
        if(pos == null) {
            pos = new HashSet<>(2);
            track.put(x, pos);
        }

        pos.add(idx);
    }

    public int[] lexicographicallySmallestArray(int[] A, int limit) {
        int N = A.length;

        // we will use Treemap to find closest possible within limit
        TreeMap<Integer, Set<Integer>> track = new TreeMap<>();
        for(int i = 0; i < N; i++) {
            int x = A[i];
            add(track, x, i);
        }

        for(int i = 0; i < N; i++) {
            // now we will swap as per constraint
            // first we will remove the current element and see whether swap possible or not
            int x = A[i];
            remove(track, x, i);

            // now look suitable item on right side
            int y = x - limit;
            int found = findFirst(track, y);
            if(found != -1) {
                int e1 = A[i], e2 = A[found];
                if(e2 < x && Math.abs(e2 - e1) <= limit) {
                    // TODO need to think whether difference check is required or not
                    // remove e2 from index found and add e1 at index found
                    remove(track, e2, found);
                    add(track, e1, found);
                    // modify original array with swap
                    A[found] = e1;
                    A[i] = e2;
                }
            }
        }

        return A;
    }
}