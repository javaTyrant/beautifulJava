package design;

/**
 * @author lumac
 * @since 2020/5/27
 */
public class Mystr {
    /**
     * 暴力法
     * 如何优化暴力法
     * 我们需要多少临时变量呢
     * 指针是个好东西
     *
     * @param haystack 源数据
     * @param needle   目标数据
     * @return 符合条件的开始索引
     */
    public static int strStr(String haystack, String needle) {
        int lenOfN = needle.length(), lenOfHay = haystack.length();
        if (lenOfN == 0) return 0;
        //首字母指针
        int leftPointer = 0;
        while (leftPointer < lenOfHay - lenOfN + 1) {
            //找到haystack中needle的第一个字符串
            //ccc,ac,如果第二个不是a,那就可不用找了,因为剩下的一定不满足
            //hay3,ned2,3-2+1
            while (leftPointer < lenOfHay - lenOfN + 1 && haystack.charAt(leftPointer) != needle.charAt(0)) {
                ++leftPointer;
            }
            //记录长度
            int currLen = 0;
            //指针
            int pL = 0;
            //临界值处理
            while (pL < lenOfN && leftPointer < lenOfHay && haystack.charAt(leftPointer) == needle.charAt(pL)) {
                ++leftPointer;
                ++pL;
                ++currLen;
            }
            //如果整个被找到,返回
            if (currLen == lenOfN) {
                return leftPointer - lenOfN;
            }
            //否则,回溯
            leftPointer = leftPointer - currLen + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("acabbc", "ab"));
    }
}
