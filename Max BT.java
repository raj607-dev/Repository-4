class Solution {
  public int widthOfBinaryTree(TreeNode root) {
    if (root == null)
      return 0;

    int ans = 0;
    // {node, index}
    Deque<Pair<TreeNode, Integer>> q = new ArrayDeque<>(List.of(new Pair<>(root, 1)));

    while (!q.isEmpty()) {
      final int offset = q.peekFirst().getValue() * 2;
      ans = Math.max(ans, q.peekLast().getValue() - q.peekFirst().getValue() + 1);
      for (int sz = q.size(); sz > 0; --sz) {
        final TreeNode node = q.peekFirst().getKey();
        final int index = q.pollFirst().getValue();
        if (node.left != null)
          q.offer(new Pair<>(node.left, index * 2 - offset));
        if (node.right != null)
          q.offer(new Pair<>(node.right, index * 2 + 1 - offset));
      }
    }

    return ans;
  }
}

Approach 2: DFS (Overflow)¶
Time: 
O
(
n
)
O(n)
Space: 
O
(
n
)
O(n)

C++
class Solution {
 public:
  int widthOfBinaryTree(TreeNode* root) {
    if (root == nullptr)
      return 0;

    long ans = 0;
    dfs(root, 0, 1, {}, ans);
    return ans;
  }

 private:
  void dfs(TreeNode* root, int level, long index, vector<long>&& startOfLevel,
           long& ans) {
    if (root == nullptr)
      return;
    if (startOfLevel.size() == level)
      startOfLevel.push_back(index);

    ans = max(ans, index - startOfLevel[level] + 1);
    dfs(root->left, level + 1, index * 2, std::move(startOfLevel), ans);
    dfs(root->right, level + 1, index * 2 + 1, std::move(startOfLevel), ans);
  }
};
