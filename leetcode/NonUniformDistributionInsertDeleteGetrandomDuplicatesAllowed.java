// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
// TODO BUG FIX - 30/32 passed

class RandomizedCollection {

    // we will maintain hashmp for frequency tracking (multiset)
    // also frequency will help how many we have returned on random call and how many it's remaining
    // another frequency map we will track how many times we have returned on random call
    // if all returned (when current frequency == returned frequency) then remove from another map
    // when frequency updated in original map then check whether the entry exists in returned map
    // if not exists then put entry with 0

    Map<Integer, Integer> original = new HashMap<>();
    Map<Integer, Integer> returned = new HashMap<>();

    public RandomizedCollection() {
        // TODO: may need to initialize the maps here
    }
    
    public boolean insert(int val) {
        boolean f = true;
        if(original.containsKey(val)) {
            // already exists
            original.put(val, original.get(val) + 1);
            f = false;
        } else {
            original.put(val, 1);
        }

        // now check in the returned map
        if(!returned.containsKey(val)) {
            // note: if it's first time entry then we need to set with 0
            // if the frequency is > 1 and the entry is not there then all values returned that's why we have removed
            // from returned map so that we can get random next elemet to return on next call
            // so in that case we need to reentry in returned map with original frequency - 1 
            
            // whether it's a frist entry or deleted as all keys returned so it will start from original frequency - 1
            // why frequency - 1 to avoid returning same old elements in the same cycle of random call
            returned.put(val, original.get(val) - 1);
        }
        // if it's already there then no need to do anything because it's return values updated properly

        return f;
    }
    
    public boolean remove(int val) {

        if(!original.containsKey(val)) {
            return false; // not exists
        }

        // when elements removed then if the original current frequency < returned frequency
        // then returned frequency should be updated with the lesser current frequency
        // because after that if elements added then those elements are new so that can be part of random call
        original.put(val, original.get(val) - 1);
        if(original.get(val) == 0) {
            // remove from both
            original.remove(val);
            returned.remove(val);
        } else if(original.get(val) == returned.get(val)) {
            // balance with original one
            // NOTE: if we keep this key in returned one then on random call we may return some old elements
            // which already returned in current cycle on random calls
            returned.remove(val);
        }

        return true;
    }
    
    public int getRandom() {
        // on random call returned value frequency will be added
        // note: hashmap iterator is lazy so we will take advantage of that
        // get first key in O(1) TC and return that key and update frequency + 1
        // when added frequency same as original frequency then we can't return this key anymore
        // so weil remove from returned one

        if(returned.isEmpty()) {
            // that means we have finished one cycle of random call we will refill the keys in returned map witn 0 frequency
            // so that we can start a fresh new cycle of random call
            for(int x : original.keySet()) {
                returned.put(x, 0);
            }
        }

        Iterator<Integer> i = returned.keySet().iterator();
        int x = i.next();
        returned.put(x, returned.get(x) + 1);
        int f1 = returned.get(x);
        int f2 = original.get(x);

        if(f1 == f2) {
            // remove from returned one because no more this key is the part of random call
            // so we can choose next element in hashmap as part of random call
            // note: and in hashmap next element is also sort random element
            returned.remove(x);
        }

        return x;

    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */