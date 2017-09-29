package ua.lviv.navpil.generics_are_hard.comparable;


import java.util.ArrayList;
import java.util.List;

public class CompareComparables {

    public static void main(String[] args) {

        List<Comparable<?>> comparables = new ArrayList<>();
        comparables.add("How to compare this string?");


        //None of these compile, Why?
//        compareFirstListElementToItself(comparables);
//        compareToItself(comparables.get(0));

        //Well, because technically:
        Apple apple = new Apple();
        Orange orange = new Orange();

        apple.compareTo(orange);
        orange.compareTo(apple);
        //You can implement Comparable, so that you compare Apples to Oranges
        //That's why we can't assume that every comparable will be <T extends Comparable<T>>

        //won't compile
//        compareToItself(apple);

        //So what to do?

        class ComparableList {
            private List<Comparable<?>> list = new ArrayList<>();

            //We are sure that this comparable will be corect
            public <T extends Comparable<T>> void add(Comparable<T> c) {
                list.add(c);
            }

            public int compareToItself(int index) {
                //Yes, it's raw type, but we know what we are doing.
                Comparable comparable = list.get(index);
                return comparable.compareTo(comparable);

                //So, can we use raw types? Usually - no, that's bad idea.
                //But here we did ensure that ClassCast will not be thrown
            }
        }

        ComparableList comparableList = new ComparableList();
        comparableList.add("asdfasdf");
        comparableList.compareToItself(0);
        comparableList.add(1203);
        comparableList.compareToItself(1);

        //Won't compile
//        comparableList.add(orange)


    }

    static class Orange implements Comparable<Apple>{
        @Override
        public int compareTo(Apple apple) {
            return 0;
        }
    }
    static class Apple implements Comparable<Orange> {
        @Override
        public int compareTo(Orange orange) {
            return 0;
        }
    }



    //Won't compile
//        public static <T> void compareFirstListElementToItself(List<Comparable<T>> list) {
//            list.get(0).compareTo(list.get(0));
//        }

    public static <T extends Comparable<T>> void compareFirstListElementToItself(List<T> list) {
        list.get(0).compareTo(list.get(0));
    }

    //Won't compile
//        public static <T > void compareToItself(Comparable<T> comparable) {
//            comparable.compareTo(comparable);
//        }

    public static <T extends Comparable<T>> void compareToItself(T comparable) {
        comparable.compareTo(comparable);
    }

}

