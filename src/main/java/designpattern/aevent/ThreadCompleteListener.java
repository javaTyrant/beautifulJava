package designpattern.aevent;

/**
 * Interface with listener behaviour related to Thread Completion.
 *
 * @author lumac
 * @since 2020/6/16
 */
public interface ThreadCompleteListener {
    void completedEventHandler(final int eventId);
}
