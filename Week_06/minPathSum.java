class Solution {
    public int minPathSum(int[][] grid) {
        // 参数校验
        if (grid == null || grid.length <= 0 || grid[0].length <= 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] store = new int[m][n];
        store[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            store[i][0] = grid[i][0] + store[i - 1][0];
        }
        for (int j = 1; j < n; j++) {
            store[0][j] = grid[0][j] + store[0][j - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                store[i][j] = Math.min(store[i - 1][j], store[i][j - 1]) + grid[i][j];
            }
        }
        return store[m - 1][n - 1];
    }
}
// 1.和面试官确认题意，边界条件，特殊情况
// 2.想所有解决办法，计算时间空间复杂度，比较，找出最优解
// 2.1倒推，从右下角倒推O(2^n)
// 2.2顺推，计算所有点的最小数O(n)
// 3.code
// 4.test
