class TrieNode:

    def __init__(self):
        self.isEnd = False
        self.nodes = {}

class WordDictionary:

    def __init__(self):
        self.root = TrieNode()

    def addWord(self, word: str) -> None:
        t = self.root
        for i in range(len(word)):
            ch = word[i]
            if ch in t.nodes:
                t = t.nodes[ch]
            else:
                nn =  TrieNode()
                t.nodes[ch] = nn
                t = nn
        t.isEnd = True

    def searchExtend(self, t, word, idx):
        for i in range(idx, len(word)):
            ch = word[i]
            if ch == '.':
                # recursively call for all possibilities
                ok = False
                for possible in t.nodes:
                    ok = ok | self.searchExtend(t.nodes[possible], word, i + 1)
                    if ok == True:
                        return True
                
                return ok
            else:
                if ch in t.nodes:
                    t = t.nodes[ch]
                else:
                    return False
        
        return t.isEnd
        

    def search(self, word: str) -> bool:
        t = self.root
        return self.searchExtend(t, word, 0)


# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)