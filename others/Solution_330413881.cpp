typedef unsigned long long int LL;

// note: fixed size cache using LRU strategy
class LRUCache {
private:
    int N;
    unordered_map<int, int> cache;
    unordered_map<int, LL> key_use_rank;
    map<LL, int, greater<LL>> key_reverse_rank;
public:
    LRUCache(int N) {
        this->N = N;
    }
    
    int get(int key) {
        if(cache.find(key) == cache.end()) {
            // not found
            return -1;
        }
        // note: mark this key as RU
        LL rank = key_use_rank[key];
        int top_rank = key_reverse_rank.begin()->first + 1;
        key_reverse_rank.erase(rank);
        key_use_rank[key] = top_rank;
        key_reverse_rank[top_rank] = key;
        // cout << "TR(get): " << top_rank << "\n";
        
        return cache[key];
    }
    
    void put(int key, int value) {
        if(cache.find(key) != cache.end()) {
            // update the key
            cache[key] = value;
            // note: mark this key as RU
            LL rank = key_use_rank[key];
            int top_rank = key_reverse_rank.begin()->first + 1;
            key_reverse_rank.erase(rank);
            key_use_rank[key] = top_rank;
            key_reverse_rank[top_rank] = key;
            
            return;
        }
        if(cache.size() == N) {
            // size overflow
            // invalidate LRU
            LL lru_rank = key_reverse_rank.rbegin()->first;
            //cout << "R: " << lru_rank << "\n";
            int key = key_reverse_rank[lru_rank];
            cache.erase(key);
            key_use_rank.erase(key);
            key_reverse_rank.erase(lru_rank);
        }
        int top_rank = 1;
        if(!cache.empty()) {
            top_rank = key_reverse_rank.begin()->first + 1;
        }
        //cout << "TR(put): " << top_rank << "\n";
        key_use_rank[key] = top_rank;
        key_reverse_rank[top_rank] = key;
        cache[key] = value;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */