package snowflake;

/**
 * @author lumac
 * @since 2020/6/8
 */
public class Demo {
    public static void main(String[] args) {
        SnowFlake snowflake = new SnowFlake(2, 5);
        long id1 = snowflake.nextId();
        long id2 = snowflake.nextId();
        long id3 = snowflake.nextId();
        System.out.println(id1);
        System.out.println(id2);
        System.out.println(id3);
    }
}
