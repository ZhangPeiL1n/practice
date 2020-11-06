package com.zpl.practice.java8.methodreference;

/**
 * @author 张沛霖
 * @date 2020/10/19
 */
public class MethodReferenceTest {
    public static void main(String[] args) {
        // ArrayList<Car> cars = new ArrayList<>();
        // for (int i = 0;i < 5; i++){
        //     Car car = Car.create(Car::new);
        //     cars.add(car);
        // }
        // cars.forEach(Car::showCar);
        // // System.out.println("------------");
        // // cars.forEach((t) -> System.out.println(t.toString()));
        // List<String> names = Arrays.asList("Bbo", "Alice", "Tim");
        // List<Person> persons = names.stream().map(Person::new).collect(Collectors.toList());
        // System.out.println(persons);
    }

    @FunctionalInterface
    interface Factory<T> {
        T create();
    }

    static class Car {
        public void showCar() {
            System.out.println(this.toString());
        }

        public static Car create(Factory<Car> factory) {
            return factory.create();
        }
    }


    class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person:" + this.name;
        }
    }
}
