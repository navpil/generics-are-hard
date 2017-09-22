package ua.lviv.navpil.generics_are_hard.basics;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BeCarefulWithRawTypes {

    public static final Logger LOG = Logger.getLogger(BeCarefulWithRawTypes.class.getName());

    public static void main(String[] args) {

        /*
        If you mix raw types with generified types you will get the compile time warnings,
         which you can ignore in some cases, but usually you should not, because otherwise you'll get the
         runtime exceptions.

         The below example is probably too simple. But please notice where the ClassCast is actually thrown
         */

        ArrayList<String> strings = new ArrayList<>();
        ArrayList bareArrayList = strings;

        bareArrayList.add(1);

        /*
        The following needs some small clarification. You've parametrized your class with String,
        so you may expect that when you call strings.get(0) it will try to return a String out of Integer and that
        would fail.
        However this is not the case. Since you don't assign the variable to String, it will not try to cast it
        behind the scenes.

        The byte code with and without assignment differs:

        strings.get(0);
        vs
        String s = (String)strings.get(0);

        This can bite you in some unusual places whenever you decide to use the @SuppressWarnings("unchecked")
         */
        //ClassCastException is not thrown here...
        strings.get(0);

        //...nor here...
        Object o = strings.get(0);
        System.out.println(o);

        //...but here it already is:
        try {
            String s = strings.get(0);
            System.out.println("This will never be printed: " + s);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Never ignore exceptions in production! Log them at least!", e);
        }
        //...and here too:
        System.out.println(strings.get(0));
    }
}
