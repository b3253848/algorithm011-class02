class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = new TreeNode(-1);
        Map storePMap = new HashMap();
        Map storeQMap = new HashMap();
        Map storeAllMap = new HashMap();
        traverse(root, null, 1, storeAllMap);
        storePMap = (Map) storeAllMap.get(p);
        storeQMap = (Map) storeAllMap.get(q);
        while (p != root || q != root) {
            // 如果,p的深度大于等于q的深度,找p的parent,此处感觉类似递归，可以分解子问题
            if ((int) storePMap.get("depth") >= (int) storeQMap.get("depth")) {
                TreeNode parent = (TreeNode) storePMap.get("parent");
                if (parent == q) {
                    return q;
                } else {
                    storePMap = (Map) storeAllMap.get(parent);
                    p = parent;
                }
            } else {
                TreeNode parent = (TreeNode) storeQMap.get("parent");
                if (parent == p) {
                    return p;
                } else {
                    storeQMap = (Map) storeAllMap.get(parent);
                    q = parent;
                }
            }
        }
        return res;
    }

    private void traverse(TreeNode root, TreeNode parent, int depth, Map storeAllMap) {
        // 1.recursion terminator 递归终止条件
        if (root == null) {
            return;
        }
        // 2.process logic in current level
        Map tempMap = new HashMap();
        tempMap.put("parent", parent);
        tempMap.put("depth", depth);
        storeAllMap.put(root, tempMap);
        // 3.drill down
        traverse(root.left, root, depth+1, storeAllMap);
        traverse(root.right, root, depth+1, storeAllMap);
        // 4.reverse states
    }
}