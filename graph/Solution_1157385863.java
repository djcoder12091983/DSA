class Solution {
    class Pair {
		int row, col;
		TreeNode node;

		public Pair(int row, int col, TreeNode node) {
			this.row = row;
            this.col = col;
			this.node = node;
		}
	}

	 public List<List<Integer>> verticalTraversal(TreeNode root) {
		Queue<Pair> pq = new LinkedList<Pair>();
		
		pq.offer(new Pair(0, 0, root));
		TreeMap<Integer, List<Pair>> map = new TreeMap<>();
		while (!pq.isEmpty()) {
			int size = pq.size();
			for (int i = 0; i < size; i++) {
				Pair cur = pq.poll();
				if (map.containsKey(cur.col)) {
					map.get(cur.col).add(cur);
				} else {
					List<Pair> list = new ArrayList<Pair>();
					list.add(cur);
					map.put(cur.col, list);
				}
				if (cur.node.left != null)
					pq.offer(new Pair(cur.row + 1, cur.col - 1, cur.node.left));
				if (cur.node.right != null)
					pq.offer(new Pair(cur.row + 1, cur.col + 1, cur.node.right));
			}
		}
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		map.forEach((k, v) -> {
            Collections.sort(v, (p1, p2) -> {
                if(p1.row != p2.row) {
                    return p1.row - p2.row;
                } else {
                    return p1.node.val - p2.node.val;
                }
            });
            List<Integer> row = new ArrayList<>();
            v.forEach((p) -> row.add(p.node.val));
            ans.add(row);
        });

		return ans;
	}
}