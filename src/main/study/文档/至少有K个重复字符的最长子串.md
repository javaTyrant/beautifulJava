#### [至少有K个重复字符的最长子串](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/)

> 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
>
> 示例 1:
>
> 输入:
> s = "aaabb", k = 3
>
> 输出:
> 3
>
> 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
> 示例 2:
>
> 输入:
> s = "ababbc", k = 2
>
> 输出:
> 5
>
> 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。

假设我们的string是bababccc

我们先用一个count数组统计每个字符串出现的次数[2,3,3]

然后我们统计出没满足的索引[1,3]

i = 1时

pre=0



