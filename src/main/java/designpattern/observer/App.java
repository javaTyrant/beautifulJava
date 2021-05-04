package designpattern.observer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lumac
 * @since 2021/2/20
 */
@Slf4j
public class App {
    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        Weather weather = new Weather();
        weather.addObserver(new Orcs());
        weather.addObserver(new Hobbits());

        weather.timePasses();
        weather.timePasses();
        weather.timePasses();
        weather.timePasses();

        // Generic observer inspired by Java Generics and Collection by Naftalin & Wadler
//        LOGGER.info("--Running generic version--");
//        var genericWeather = new GWeather();
//        genericWeather.addObserver(new GOrcs());
//        genericWeather.addObserver(new GHobbits());
//
//        genericWeather.timePasses();
//        genericWeather.timePasses();
//        genericWeather.timePasses();
//        genericWeather.timePasses();
    }

    public void sort(int[] arrays) {
        sort(arrays, 0, arrays.length - 1);
    }

    public void sort(int[] arrays, int left, int right) {
        int pivot = arrays[left];

    }
}
