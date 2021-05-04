package designpattern.observer;

/**
 * @author lumac
 * @since 2021/2/20
 */
public class Hobbits implements WeatherObserver {

    @Override
    public void update(WeatherType currentWeather) {
        System.out.println("The hobbits are facing " + currentWeather.getDescription() + " weather now");
    }
}
