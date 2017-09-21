package ua.lviv.navpil.generics_are_hard.basics;

import java.util.List;

public class GenericOverload {

    public int findMedianAge(List<Animal> animals) {
        return animals.isEmpty() ? 0 : animals.stream().map(Animal::getAge).reduce((a, b) -> a + b).orElse(0) / animals.size();
    }

    //Will not compile, generic overload does not work because of erasure
//    public int findMedianAge(List<Person> people) {
//        return people.isEmpty() ? 0 : people.stream().map(Person::getAge).reduce((a, b) -> a + b).orElse(0) / people.size();
//    }

    public int findMedianPersonAge(List<Person> people) {
        return people.isEmpty() ? 0 : people.stream().map(Person::getAge).reduce((a, b) -> a + b).orElse(0) / people.size();
    }


    public static class Animal {
        private int age;

        public int getAge() {
            return age;
        }
    }

    public static class Person {
        private int age;

        public int getAge() {
            return age;
        }
    }
}


