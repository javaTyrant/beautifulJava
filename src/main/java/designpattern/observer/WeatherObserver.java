package designpattern.observer;

/**
 * @author lumac
 * @since 2021/2/20
 */
public interface WeatherObserver {
    void update(WeatherType currType);
}
