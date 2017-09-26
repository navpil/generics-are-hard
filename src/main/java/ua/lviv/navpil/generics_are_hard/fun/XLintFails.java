package ua.lviv.navpil.generics_are_hard.fun;

import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

//This code compiles without a warning (java 1.8.0_131-b11) but fails runtime with ClassCastException
//
//generics-are-hard/src/main/java> javac -Xlint:all ua/lviv/navpil/generics_are_hard/fun/XLintFails.java
public class XLintFails {

    private static final Logger LOG = Logger.getLogger(XLintFails.class.getName());

    public static void main(String[] args) {

        /*
        The Comparable interface is a bit tricky with inheritance in general, but it's possible to do right.
        However when you decide to be overly clever and use generics, as in this example you may get a surprising result:
         1. code compiles without a single warning when -Xlint:unchecked flag is used
         2. but it fails runtime with ClassCastException
         */

        try {
            //Here the wildcard <?> is used, so let's pretend that -Xlint behavior is OK...
            TreeSet<Person<?>> people = new TreeSet<>();

            people.add(new Person<>());
            people.add(new Student<>());

            people.forEach(System.out::println);
        } catch (ClassCastException e) {
            LOG.log(Level.WARNING, "Fail with wildcards", e);
        }

        try {
            //...but here everything is bound, and -Xlint still fails to find a problem
            addToTree(
                    addToTree(new TreeSet<>(), new Person<>()),
                    new Student<>()
            ).forEach(System.out::println);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Fail with inference", e);
        }
    }

    /**
     * Adds an object to a TreeSet
     *
     * @param set set to add an object to
     * @param obj object to add
     * @param <T> type of set
     * @param <U> type of an object (should be subtype of T)
     * @return updated set
     */
    private static <T, U extends T> TreeSet<T> addToTree(TreeSet<T> set, U obj) {
        set.add(obj);
        return set;
    }

    public static class Person<T extends Person<T>> implements Comparable<T> {
        @Override
        public int compareTo(T o) {
            return 0;
        }
    }

    public static class Student<T extends Student<T>> extends Person<T> implements Comparable<T> {
        @Override
        public int compareTo(T o) {
            return 0;
        }
    }




}
