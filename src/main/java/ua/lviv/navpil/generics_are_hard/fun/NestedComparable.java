package ua.lviv.navpil.generics_are_hard.fun;

public class NestedComparable implements Comparable<Comparable<Comparable<NestedComparable>>>{

    /*
    You may get funny results when classes get parametrized with themselves.

    It is interesting to note what compiles without(?) warnings and what does not.

    Clearly you can't have any of these two methods together because of bridge methods (described elsewhere).
     */

    //1
    @Override
    public int compareTo(Comparable<Comparable<NestedComparable>> o) {return 0;}

    //2
//    public int compareTo(Comparable<Comparable> o) {return 0;}

    //3
//    public int compareTo(Comparable<NestedComparable> o) {return 0;}

    //4
//    public int compareTo(Comparable o) {return 0;}

}

/*
For those too lazy to uncomment methods and check it out themselves, method 1 and 4 compiles.
However methods 2 and 3 do not. And if for method 3 it's quite clear why, method 2 looks probably strange.
So you can whether have raw types or correct generics, you can't have semi-raw generics as in Comparable<Comparable>

I was tricked to think that number 4 compiles without warnings. And it actually does. But it does not.
If to compile with no flags, no warnings will be given. Even -Xlint:unchecked will give no warnings,
but -Xlint:all will, because the warning is actually 'rawtypes', not 'unchecked'

Lesson learned. To be safe with generics -Xlint:unchecked is not enough
 */
