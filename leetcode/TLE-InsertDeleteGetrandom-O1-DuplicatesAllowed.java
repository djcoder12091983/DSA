// https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

class RandomizedCollection {

    Random random = new Random();

    // original-set, we will track the frequency
    Map<Integer, Integer> original = new HashMap<>();
    
    // this linear list will help to find a random index
    // and return the corresponding element but the elements exists or not
    // we will check frequency in the original set
    List<Integer> elements = new ArrayList<>();

    // this will track how many elements we have returned
    // when no more elements to return but still random call is happening
    // then we need to clear this returned map and insert all available elements into linear list
    // ramdom generation cycle repeats 
    Map<Integer, Integer> returned = new HashMap<>();

    public RandomizedCollection() {
        // TODO
    }
    
    public boolean insert(int val) {
        boolean exists = original.containsKey(val);
        original.put(val, original.getOrDefault(val, 0) + 1);

        // TODO TRICK!
        // we can think of adjusting frequency with returned map
        // so that we can avoid adding unnecessary duplicate elements into linear list
        elements.add(val);

        return !exists;
    }
    
    public boolean remove(int val) {
        if(original.containsKey(val)) {
            original.put(val, original.get(val) - 1);
            if(original.get(val) == 0) {
                original.remove(val);
            }

            return true;
        } else {
            return false;
        }
    }
    
    public int getRandom() {
        int limit = elements.size();
        int val = -1;
        boolean found = false;

        // TODO if this approach gets TLE then we may need to come up with some strategy like
        // when most of the elements already returned then we can can restructure the returned map and
        // the list both to reduce number of misses
        // TODO -- Need to Think!
		// TODO -- This is the issue -- This while loop run with condition i = 0 to elements.size()
        while(found) {
            int idx = random.nextInt(limit);
            val = elements.get(idx);
            int f1 = returned.getOrDefault(val, 0);
            int f2 = original.getOrDefault(val, 0);
            if(f1 < f2) {
                // still we have some to return
                // valid random element to return
                returned.put(val, f1 + 1);
                found = true;
            }
        }

        if(found) {
            // found
            return val;
        }

        // TODO restructure the elements and returned map
        // we didn't find any element to return
        // we have done with the full cycle of random number generation
        // we clear the returned value and elements
        returned.clear();
        elements.clear();

        for(int x : original.keySet()) {
            for(int i = 0; i < original.get(x); i++) {
                elements.add(x);
            }
        }

        // return a random value
        limit = elements.size();
        int idx = random.nextInt(limit);
        val = elements.get(idx);
        returned.put(val, 1);

        return val;
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */