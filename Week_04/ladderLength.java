class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1.首先，新增中间态的概念，作为转换的缓冲，用hash来存中间态，这样在找可转换的单词时
        // ，查找的时间复杂度都是O(1)的
        // 因为单词长度相同
        int L = beginWord.length();
        Map<String, List<String>> map = new HashMap<>();
        // 遍历wordList
        wordList.forEach(
            // 对于每个word，根据长度遍历生成中间态
            word -> {
                for (int i = 0; i < L; i++) {
                    // 上次就是在这写错了，i + 1 写成了 1.
                    String key = word.substring(0, i) + "*" + word.substring(i + 1, L);
                    List<String> list = map.getOrDefault(key, new ArrayList<>());
                    list.add(word);
                    map.put(key, list);
                }
            }
        );
        // 最后得到一个，以中间态为key，包含此中间态的word列表为value的hash，找可转换的单词时，
        // 只要先找出当前单词的所有中间态，再根据中间态找出所有的列表就行了
        // 2.BFS广度优先搜索
        // 创建队列
        Queue<Pair<String, Integer>> Q = new LinkedList<>();
        // 将首结点加入队列
        Q.add(new Pair(beginWord, 1));
        // 创建visited记录访问过的结点，防止环，重复访问
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        // while循环，当队列不为空
        while(!Q.isEmpty()) {
            // 弹出队列头结点 process node 处理结点
            Pair<String, Integer> p = Q.remove();
            String keyWord = p.getKey();
            Integer level = p.getValue();
            // 找出下一个结点
            for (int i = 0; i < L; i++) {
                String key = keyWord.substring(0, i) + "*" + keyWord.substring(i + 1, L);
                List<String> list = map.getOrDefault(key, new ArrayList<>());
                for (String s : list) {
                    if (s.equals(endWord)) {
                        return level + 1;
                    }
                    if (!visited.containsKey(s)) {
                        visited.put(s, true);
                        Q.add(new Pair(s, level + 1));
                    }
                }
            }
        }

        // 如果直到循环结束，都没找到满足条件的结点，说明不存在
        return 0;
    }
}