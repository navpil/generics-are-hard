package ua.lviv.navpil.generics_are_hard.fun;

public class Recurring<T extends Recurring<T>>{

    public static void main(String[] args) {

        //-----------Raw types are fine
        new Recurring();
        new Recurring().doSomething();
        Recurring r1 = new Recurring();
        r1.doSomething();

        //-----------Diamond operators are fine
        new Recurring<>();
        new Recurring<>().doSomething();
        //What is it parametrized it? That is a question.
        //Is it parametrized with a question?
        Recurring<?> r2 = new Recurring<>();
        r2.doSomething();

        //-----------Try to parametrize with itself and we get a compile time error:
//        new Recurring<Recurring>();//Does not compile

        /*
        Because obviously Recurring does not extend Recurring<Recurring>.
        Is Recurring<Recurring> extends it?
         */

//        new Recurring<Recurring<Recurring>>();//Does not compile

        /*
        Nope. Because Recurring<Recurring> does not extend Recurring<Recurring<Recurring>>

        What about all-mighty questions?
        */

//        Recurring<Recurring<?>> r4 = new Recurring<>();//Does not compile

        /*
        No, questions can't be inside because... I don't know why. Probably it just makes no sense.

        This examples brings us nowhere. It hardly teaches us anything new about generics. Except that they are hard.
         */
    }

    void doSomething(){}

}
