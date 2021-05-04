package guavaDesgin.event;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author lumac
 * @since 2020/5/22
 */
public class Demo {
    static class DickEvent {
        private Integer len;
        private String color;

        public DickEvent(Integer len, String color) {
            this.len = len;
            this.color = color;
        }

        public Integer getLen() {
            return len;
        }

        public void setLen(Integer len) {
            this.len = len;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    //register和post是如何实现的
    public static void main(String[] args) {
        // 定义一个EventBus对象，这里的Joker是该对象的id
        EventBus eventBus = new EventBus("Joker");
        // 向上述EventBus对象中注册一个监听对象
        eventBus.register(new EventListener());
        eventBus.register(new EventDickListener());
        // 使用EventBus发布一个事件，该事件会给通知到所有注册的监听者
        eventBus.post(new DickEvent(18, "black"));
    }

    // 事件，监听者监听的事件的包装对象
    public static class Event {
        public String message;

        Event(String message) {
            this.message = message;
        }
    }

    // 监听者
    public static class EventListener {
        // 监听的方法，必须使用注解声明，且只能有一个参数，实际触发一个事件的时候会根据参数类型触发方法
        @Subscribe
        public void listen(Event event) {
            System.out.println("Event listener 1 event.message = " + event.message);
        }
    }

    // 监听者
    public static class EventDickListener {
        // 监听的方法，必须使用注解声明，且只能有一个参数，实际触发一个事件的时候会根据参数类型触发方法
        @Subscribe
        public void listen(DickEvent event) {
            System.out.println("oop,this is a huge" + event.getLen() + "," + event.getColor() + "dick");
        }

        @Subscribe
        public void listen1(DickEvent event) {
            System.out.println("oop,this is a huge" + event.getLen() + "," + event.getColor() + "dick");
        }
    }
}
