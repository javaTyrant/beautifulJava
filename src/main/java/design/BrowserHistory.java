package design;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lumac
 * @since 2020/6/7
 */
public class BrowserHistory {
    List<String> urls;
    int index;

    public BrowserHistory(String homepage) {
        // 初始化
        int index = 0;
        urls = new ArrayList<>();
        urls.add(homepage);
    }

    public void visit(String url) {
        // 访问某个url，有可能要覆盖
        if (index < urls.size()) {
            urls = urls.subList(0, index + 1);
        }
        index++;
        urls.add(url);
    }

    public String back(int steps) {
        // 回退steps步
        index = index - steps;
        if (index < 0) {
            index = 0;
        }
        return urls.get(index);
    }

    public String forward(int steps) {
        // 前进steps步
        index = index + steps;
        if (urls.size() <= index) {
            index = urls.size() - 1;
        }
        return urls.get(index);
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("www.baidu.com");
        browserHistory.visit("www.le w's'x'g'b'y'se'detcode.com");
        System.out.println(browserHistory.back(3));
        browserHistory.visit("www.google.com");
    }
}
