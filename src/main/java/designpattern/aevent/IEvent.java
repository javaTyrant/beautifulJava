package designpattern.aevent;

/**
 * Events that fulfill the start stop and list out current status behaviour follow this interface.
 *
 * @author lumac
 * @since 2020/6/16
 */
public interface IEvent {
    void start();

    void stop();

    void status();
}
