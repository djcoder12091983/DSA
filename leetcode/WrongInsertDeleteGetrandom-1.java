class RandomizedSet {

    // idea is like we will maintain two set as values are unique
    // one will track the original and another one we will track whether
    // it's returned on random call in current cycle

    Set<Integer> original = new HashSet<>();
    Set<Integer> returned = new HashSet<>();

    public RandomizedSet() {
        
    }
    
    public boolean insert(int val) {
        boolean f = original.add(val); // blind add
        returned.add(val);

        return f;
    }
    
    public boolean remove(int val) {
        boolean f = original.remove(val);
        returned.remove(val);

        return f;
    }
    
    public int getRandom() {
        if(returned.isEmpty()) {
            // that means we have finished one cycle of random call we will refill the keys in returned set
            // so that we can start a fresh new cycle of random call
            for(int x : original) {
                returned.add(x);
            }
        }

        Iterator<Integer> i = returned.iterator();
        int x = i.next();

        returned.remove(x);

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