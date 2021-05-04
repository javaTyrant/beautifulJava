package current;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lumac
 * @since 2021/1/25
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        Map<BigDecimal, BigDecimal[]> config = new HashMap<>();
    }

    static class PriceConfig {
        //排序 从apollo取值
        //@Value
        static Map<BigDecimal, BigDecimal[]> config = new LinkedHashMap<>();

        //{1:[0,300],2:[300,800],3:[800,1000]}
        static BigDecimal getConfByDis(BigDecimal dis) {
            BigDecimal price = BigDecimal.ZERO;
            for (Map.Entry<BigDecimal, BigDecimal[]> entry : config.entrySet()) {
                if (dis.compareTo(entry.getValue()[1]) <= 0) {
                    price = entry.getKey();
                    break;
                }
            }
            return price;
        }
    }

    class NormalCalculate implements Calculate {
        @Override
        public BigDecimal calculate(BigDecimal distance) {
            return PriceConfig.getConfByDis(distance).multiply(distance);
        }
    }

    interface Calculate {
        /**
         * 根据距离算手续费
         *
         * @param distance 距离
         * @return price
         */
        BigDecimal calculate(BigDecimal distance);
    }
}
