https://leetcode.com/problems/snapshot-array/description/

class SnapshotArray {

    // will maintain TREEMAP to track version wise values for each index
    // when it will be updated then only i will store previous version value
    // when searched then floor value version mapped value will be returned

    List<TreeMap<Integer, Integer>> data; // version mapped values for each index
    int nextsnap = 0;

    public SnapshotArray(int N) {
        data = new ArrayList<>(N);

        for(int i = 0; i < N; i++) {
            TreeMap<Integer, Integer> versions = new TreeMap<>();
            versions.put(0, 0); // initially all 0's and initial version is 0 as well
            data.add(versions);
        }
    }
    
    public void set(int index, int val) {
        // we don't store value for each snap when it's changed only modified value is stored for a range
        // so that we can save time and space
        data.get(index).put(nextsnap, val); // store it for next snapid
    }
    
    public int snap() {
        return nextsnap++; // next snap id
    }
    
    // assumed at least one snap id will be called before this API is called
    public int get(int index, int snap_id) {
        // we don't store value for each snap when it's changed only modified value is stored for a range
        // so that we can save time and space
        return data.get(index).floorEntry(snap_id).getValue(); // floor snapid value is returned
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */