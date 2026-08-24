// https://leetcode.com/problems/insert-delete-getrandom-o1/

// the logic I will write that also handles duplicates as well, it's generalized in that way
// TODO: duplicates not working, so just want to see whether it's working for unique values or not

// TODO: will not work for unique values because if same elements inserted twice then only one should be there

class RandomizedSet {

    Map<Integer, Integer> original = new HashMap<>();
    Map<Integer, Integer> returned = new HashMap<>();

    public RandomizedSet() {
        
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
        
        // balance with original one
        // NOTE: if we keep this key in returned one then on random call we may return some old elements
        // which already returned in current cycle on random calls
        // NOTE: when you keep on removing and after some point it's frequency <= returned frequency
        // that means it's deleted so before comparing frequency with original we will check whether it's there or not
        if(returned.containsKey(val)) {
            int f1 = returned.get(val);
            int f2 = original.get(val);
            if(f1 == f2) {
                returned.remove(val);
            }
        }

        if(original.get(val) == 0) {
            original.remove(val);
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
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */