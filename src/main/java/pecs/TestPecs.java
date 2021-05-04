package pecs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author lumac
 * @since 2020/6/11
 */
public class TestPecs {
    //<T extends Comparable<T>>:类型T必须实现Comparable接口,并且这个接口类型是T.只有这样,T的
    //实例直接才能互相比较大小
    //<T extends Comparable<? super T>>
    //类型T必须实现Comparable接口,并且这个接口的类型是T或者T的任一父类.这样声明后，
    // T 的实例之间，T 的实例和它的父类的实例之间，可以相互比较大小。
    public static <T extends Comparable<T>> void mySort1(List<T> list) {
        Collections.sort(list);
    }

    public static <T extends Comparable<? super T>> void mySort2(List<T> list) {
        Collections.sort(list);
    }

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal(25));
        animals.add(new Dog(35));

        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog(5));
        dogs.add(new Dog(18));
        mySort1(animals);
        //编译错误
//        mySort1(dogs);
        mySort2(dogs);
        List basket = new ArrayList();
        basket.add("水果");
        //运行报错
        //Fruit o = (Fruit) basket.get(0);
        //extend只知道是子类
        List<? extends Fruit> fruits = new ArrayList<>();
        //不能写只能读
        //fruits.add(new Fruit());
        //fruits.add(new Apple());
        List<? super Fruit> fruits1 = new ArrayList<>();
        //因为add是fruit超类,所以无法取
        fruits1.add(new Apple());
        fruits1.add(new Fruit());
//        for (Fruit f : fruits1) {
//            System.out.println(f);
//        }

    }

    static class Fruit {
    }

    static class Apple extends Fruit {
    }

    static class Dog extends Animal {
        public Dog(int age) {
            super(age);
        }
    }

    static class Animal implements Comparable<Animal> {
        protected int age;

        public Animal(int age) {
            this.age = age;
        }

        //使用年龄与另一实例比较大小
        @Override
        public int compareTo(Animal other) {
            return this.age - other.age;
        }
    }
}
