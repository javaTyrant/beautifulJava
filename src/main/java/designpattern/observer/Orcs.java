package designpattern.observer;

/**
 * @author lumac
 * @since 2021/2/20
 */
public class Orcs implements WeatherObserver {

    @Override
    public void update(WeatherType currentWeather) {
        System.out.println("The orcs are facing " + currentWeather.getDescription() + " weather now");
    }
}
