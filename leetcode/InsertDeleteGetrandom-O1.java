// https://leetcode.com/problems/insert-delete-getrandom-o1/

// TODO if this approach gets TLE then we may need to come up with some strategy like
// when most of the elements having false flag then we can can restructure the set and
// the list both to reduce number of misses

class RandomizedSet {

    Random random = new Random();

    // we will track whether it exists or not
    // if deleted we will not directly remove rather we will mark it as false
    Map<Integer, Boolean> set = new HashMap<>();

    // this will help us to find a random value
    // now when an element is removed then it's marked as false and if random call tries
    // to select that element then we will bypass and look for next random value
    // TODO if this approach gets TLE then we may need to come up with some strategy like
    // when most of the elements having false flag then we can can restructure the set and
    // the list both to reduce number of misses
    List<Integer> elements = new ArrayList<>();

    public RandomizedSet() {
        // TODO
    }
    
    public boolean insert(int val) {
        if(!set.containsKey(val)) {
            // actually it's not in the set
            // we need to put in into array when it's deleted
            // then we need to mark it as false in set only 
            elements.add(val);
            set.put(val, true);

            return true;
        } else {
            boolean flag = set.get(val);
            if(!flag) {
                // was there but deleted
                set.put(val, true); // bring it back
                return true;
            } else {
                // it's already there
                return false;
            }
        }
    }
    
    public boolean remove(int val) {
        if(set.containsKey(val)) {
            boolean flag = set.get(val);
            if(flag == false) {
                // it was there but it's removed
                return false;
            }
            
            set.put(val, false); // mark it as false
            return true; // removed
        } else {
            // it was not there ever
            return false;
        }
    }
    
    public int getRandom() {
        int limit = set.size();
        int val = -1;
        // TODO if this approach gets TLE then we may need to come up with some strategy like
        // when most of the elements having false flag then we can can restructure the set and
        // the list both to reduce number of misses
        while(true) {
            int idx = random.nextInt(limit);
            val = elements.get(idx);
            if(set.get(val) == true) {
                // if exists then only we should return or else
                // we should look next random available value
                break;
            }
        }

        return val; // aval will be always populated with correct one 
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */