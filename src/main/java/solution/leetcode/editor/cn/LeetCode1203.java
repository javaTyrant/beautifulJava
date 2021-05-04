package solution.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode1203 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
            for (int i = 0; i < n; i++) {
                if (group[i] == -1)
                    group[i] = m++;
            }
            List<Integer>[] groupEdges = new List[m];
            List<Integer>[] itemEdges = new List[n];
            int[] groupDegree = new int[m];
            int[] itemDegree = new int[n];
            for (int i = 0; i < n; i++) {
                List<Integer> list = beforeItems.get(i);
                for (int j = 0; j < list.size(); j++) {
                    int k = list.get(j);
                    if (group[k] == group[i]) {
                        itemDegree[i]++;
                        if (itemEdges[k] == null)
                            itemEdges[k] = new ArrayList<>();
                        itemEdges[k].add(i);
                    } else {
                        groupDegree[group[i]]++;
                        if (groupEdges[group[k]] == null)
                            groupEdges[group[k]] = new ArrayList<>();
                        groupEdges[group[k]].add(group[i]);
                    }
                }
            }
            int[] groups = new int[m];
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < m; i++) {
                if (groupDegree[i] == 0)
                    queue.offer(i);
            }
            int index = 0;
            while (!queue.isEmpty()) {
                int i = queue.poll();
                groups[index++] = i;
                if (groupEdges[i] == null)
                    continue;
                for (int j = 0; j < groupEdges[i].size(); j++) {
                    if (--groupDegree[groupEdges[i].get(j)] == 0)
                        queue.offer(groupEdges[i].get(j));
                }
            }
            if (index < m)
                return new int[0];
            List<Integer>[] groupMembers = new List[m];
            for (int i = 0; i < n; i++) {
                if (groupMembers[group[i]] == null)
                    groupMembers[group[i]] = new ArrayList<>();
                groupMembers[group[i]].add(i);
            }
            int[] itemsOrder = new int[n];
            index = 0;
            for (int i = 0; i < m; i++) {
                int g = groups[i];
                if (groupMembers[g] == null)
                    continue;
                for (int j = 0; j < groupMembers[g].size(); j++) {
                    if (itemDegree[groupMembers[g].get(j)] == 0)
                        queue.offer(groupMembers[g].get(j));
                }
                int count = 0;
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    itemsOrder[index++] = j;
                    count++;
                    if (itemEdges[j] == null)
                        continue;
                    int size = itemEdges[j].size();
                    for (int k = 0; k < size; k++) {
                        if (--itemDegree[itemEdges[j].get(k)] == 0)
                            queue.offer(itemEdges[j].get(k));
                    }
                }
                if (count != groupMembers[g].size())
                    return new int[0];
            }
            return itemsOrder;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
