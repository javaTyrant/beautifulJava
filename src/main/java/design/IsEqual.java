package design;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class IsEqual {
    int father[] = new int[26];

    public int find(int x) {
        if (father[x] == x)
            return x;
        int fx = find(father[x]);
        father[x] = fx;
        return fx;
    }

    public boolean equationsPossible(String[] equations) {

        int len = equations.length;
        for (int i = 0; i < 26; i++)
            father[i] = i;
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int fx = find(str.charAt(0) - 'a');
                int fy = find(str.charAt(3) - 'a');
                father[fx] = fy;
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int fx = find(str.charAt(0) - 'a');
                int fy = find(str.charAt(3) - 'a');
                if (fx == fy)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsEqual is = new IsEqual();
        String[] arr = {"a==b", "b==c", "a!=c"};
        System.out.println(is.equationsPossible(arr));
    }
}
