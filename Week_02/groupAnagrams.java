class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        // 遍历，排序
        for (String s : strs) {
            char[] bucket = new char[26];
            char[] tempCharArray = s.toCharArray();
            for (int i = 0; i < tempCharArray.length; i++) {
                bucket[tempCharArray[i] - 'a']++;
            }
            String keyString = String.valueOf(bucket);
            if (!map.containsKey(keyString)) {
                map.put(keyString, new ArrayList<String>());
            }
            map.get(keyString).add(s);
        }
        return new ArrayList(map.values());
    }
}
// 1.make sure
// 2.all solutions find the best
// 遍历数组，看每一个String包含哪些字母，出现次数为多少，将相同的放到一起
// 遍历数组，将所有String都排序，将equals相等的放到一起
// 
// 3.code
// 4.test