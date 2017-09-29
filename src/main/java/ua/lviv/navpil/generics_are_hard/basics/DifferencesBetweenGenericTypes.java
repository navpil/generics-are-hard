package ua.lviv.navpil.generics_are_hard.basics;

import java.util.ArrayList;
import java.util.List;

public class DifferencesBetweenGenericTypes {


    //There is often a question what is the difference between these methods:
    static class ListFactory {

        <T> List<T> getTList() {return createList();}

        List<?> getQuestionList() {return createList();}

        List<Object> getObjectList(){return createList();}

        List getRawList() {return createList();}

        public static void main(String[] args) {

            ListFactory f = new ListFactory();

            //We can parametrize this method and get the list of something we want
            //In this case parameter could be inferred, but I added it for clarity
            List<String> tList = f.<String>getTList();

            //This list is almost useless. ? denotes that this could be list of anything.
            //It could be parametrized with a String, or with Long - we don't know.
            List<?> qList = f.getQuestionList();
            //We can add only nulls to it
            qList.add(null);
            //We can't be sure of what ? so much, so that this even won't compile:
//            qList.add(qList.get(0));
            //but can we capture question marks:
            addToItself(qList);


            //This is an explicit Object list. No different to the String list.
            //You can add Objects, remove Objects etc.
            List<Object> objectList = f.getObjectList();
            objectList.add(new Object());
            Object o = objectList.get(0);

            //Please, do not use raw lists. Check 'BeCarefulWithRawTypes'
            //In this particular case it will not throw ClassCastExceptions.
            //But please parametrize it. It's not clear, what this list can hold.
            List rawList = f.getRawList();

        }

        public static <T> void addToItself(List<T> list) {
            list.add(list.get(0));
        }

    }


    //This one is similar to the previous, but it has generics inside generics
    static class ComparableListFactory {
        <T extends Comparable<T>> List<T> getTList() {return createList();}

        List<Comparable<?>> getQuestionList() {return createList();}

        List<Comparable<Object>> getObjectList(){return createList();}

        List<Comparable> getRawList() {return createList();}

        public static void main(String[] args) {
            ComparableListFactory f = new ComparableListFactory();
            //Strings are comparables, so this code is ok, we have to parametrize the method call,
            // but in this case the argument type is inferred:
            List<String> strings = f.getTList();

            //We get a list, into which we can put any kind of Comparable:
            List<Comparable<?>> comparables = f.getQuestionList();

            comparables.add("");
            comparables.add(1001);

            //Won't compile:
//            comparables.get(0).compareTo(comparables.get(0));
            //Let's try captures, as with lists:
            //This capture won't work:
//            compareFirstListElementToItself(comparables);
            //Neither does this:
//            compareToItself(comparables.get(0));
            //How to compare Comparable<?> to itself? Is there a way?
            //Please check 'comparables/CompareComparables'


            //Please note that none of these compile, they are different lists:
//            strings = comparables;
//            comparables = strings;

            List<Comparable<Object>> objs = f.getObjectList();
            //We can only put things which implement Comparable<Object> interface

            //Won't work:
//            objs.add("");
            //Object does not implement this interface, by the way:
//            objs.add(new Object());


            //Raw comparables: here we will get a ClassCastException
            List<Comparable> rawComparables = f.getRawList();

            rawComparables.add("");
            rawComparables.add(123L);

            //This unchecked warning wants to tell us something
            rawComparables.get(0).compareTo(2345L);

        }

        //NOT
//        public static <T> void compareFirstListElementToItself(List<Comparable<T>> list) {
//            list.get(0).compareTo(list.get(0));
//        }

        //But:
        public static <T extends Comparable<T>> void compareFirstListElementToItself(List<T> list) {
            //noinspection EqualsWithItself
            list.get(0).compareTo(list.get(0));
        }

        //NOT
//        public static <T > void compareToItself(Comparable<T> comparable) {
//            comparable.compareTo(comparable);
//        }

        //But:
        public static <T extends Comparable<T>> void compareToItself(T comparable) {
            //noinspection EqualsWithItself
            comparable.compareTo(comparable);
        }


    }


    public static void main(String[] args) {


    }

    //Please note, how versatile this method is. It will return any kind of list
    public static <T> List<T> createList() {
        return new ArrayList<>();
    }
}
