package guavaDesgin;

import com.alibaba.fastjson.JSON;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author lumac
 * @since 2020-05-21
 */
public class RateDemo {
    //平滑突发限流
    @Test
    public void testSmoothBursty() {
        //创建一秒五个许可
        RateLimiter r = RateLimiter.create(5);
        while (true) {
            System.out.println("get 1 tokens: " + r.acquire() + "s");
        }
        /**
         * output: 基本上都是0.2s执行一次，符合一秒发放5个令牌的设定。
         * get 1 tokens: 0.0s
         * get 1 tokens: 0.182014s
         * get 1 tokens: 0.188464s
         * get 1 tokens: 0.198072s
         * get 1 tokens: 0.196048s
         * get 1 tokens: 0.197538s
         * get 1 tokens: 0.196049s
         */
    }
    @Test
    public void testJson() {
    }

    static class Person {
        private Integer age;
    }
    @Test
    public void testMq() {
        SizeInfo sizeInfo = new SizeInfo();
        sizeInfo.setJitAvailableSellDays(BigDecimal.ONE);
        sizeInfo.setSize("M");
        Mq mq = new Mq();
        mq.setSkc("abc");
        mq.setInfo(Arrays.asList(sizeInfo));
        System.out.println(JSON.toJSON(mq));
    }

    class Mq {
        private String skc;
        private List<SizeInfo> info;

        public String getSkc() {
            return skc;
        }

        public void setSkc(String skc) {
            this.skc = skc;
        }

        public List<SizeInfo> getInfo() {
            return info;
        }

        public void setInfo(List<SizeInfo> info) {
            this.info = info;
        }
    }

    class SizeInfo {
        private String size;
        private BigDecimal jitAvailableSellDays;

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public BigDecimal getJitAvailableSellDays() {
            return jitAvailableSellDays;
        }

        public void setJitAvailableSellDays(BigDecimal jitAvailableSellDays) {
            this.jitAvailableSellDays = jitAvailableSellDays;
        }
    }
}
