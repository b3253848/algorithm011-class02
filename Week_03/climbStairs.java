class Solution {
    public int climbStairs(int n) {
        Map<String, Object> map = new HashMap<String, Object>();
        int res = help(n, map);
        return res;
    }

    private int help(int n, Map<String, Object> map) {
        // 1.recursion terminator
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        // 2.process logic in current level
        if (map.containsKey(String.valueOf(n))) {
            return (int) map.get(String.valueOf(n));
        }
        // 3.drill down
        int temp = help(n - 1, map) + help(n - 2, map);
        // 4.reverse or revert
        map.put(String.valueOf(n), temp);
        return temp;
    }
}