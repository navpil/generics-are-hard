package ua.lviv.navpil.generics_are_hard.basics;

import ua.lviv.navpil.generics_are_hard.Holder;

import static ua.lviv.navpil.generics_are_hard.Mammals.*;
import static ua.lviv.navpil.generics_are_hard.basics.Exceptions.assertClassCastExceptionIsThrown;
import static ua.lviv.navpil.generics_are_hard.basics.Exceptions.assertExceptionIsThrown;
import static ua.lviv.navpil.generics_are_hard.basics.Holders.*;

public class Assignments {

    public static void main(String[] args) {

        GenericCreatureHolder<Cat> catHolder = new GenericCreatureHolder<Cat>(new Cat());

        //This is illegal, because we could then put Dog into the h, which is incorrect.
//        GenericCreatureHolder<Creature> h = catHolder;

        //Yes, behavior is different to arrays...
        Creature[] creatures = new Cat[1];
        //...and it's good that generics behave differently:
        assertExceptionIsThrown(() -> {creatures[0] = new Dog();}, ArrayStoreException.class);

        //With generics you can use unbounded wildcard:

        //This is legal...
        GenericCreatureHolder<?> unboundedHolder = catHolder;
        //... because we can't put anything into holder parametrized with <?> or <? extends AnyThing> ...
//        unboundedHolder.setCreature(new Cat());
//        unboundedHolder.setCreature(new Dog());
        //... it's read-only
        Creature c2 = unboundedHolder.getCreature();

        //You can use bounded wildcard:

        GenericCreatureHolder<? extends Mammal> lowerBoundHolder = catHolder;
        //We are sure that it's at least Mammal
        Mammal creature = lowerBoundHolder.getCreature();
        //But we still can't put anything into it:
//        lowerBoundHolder.setCreature(new Mammal());
//        lowerBoundHolder.setCreature(new Cat());
//        lowerBoundHolder.setCreature(new Dog());


        //You can use upper bounds for setting the creature:

        GenericCreatureHolder<? super Cat> upperBoundHolder = catHolder;
        //Legal:
        upperBoundHolder.setCreature(new Cat());
        //Illegal:
//        upperBoundHolder.setCreature(new Mammal());
        //Mammal belongs into <? super Cat> but don't be fooled: this means the the catHolder could have been
        // GenericCreatureHolder<Mammal>, but it does not mean that holder will accept Mammal as an argument

        //We can get the Creature. This is because the GenericCreatureHolder is <T extends Creature>
        Creature creature1 = upperBoundHolder.getCreature();

        //If we use unbounded Holder, then the getItem() returns us an Object
        Holder<? super Cat> genericHolder = new Holder<>(new Cat());
        Object item = genericHolder.getItem();


        //Raw types also can be used here, though it is discouraged...
        GenericCreatureHolder rawHolder = catHolder;
        Creature c3 = rawHolder.getCreature();
        //...because of this:
        rawHolder.setCreature(new Dog());
        assertClassCastExceptionIsThrown(() ->  {Cat c4 = catHolder.getCreature();});
        //See also 'BeCarefulWithRawTypes'


        //If you want to both set and get Cats, you simply use the type argument, no bounds:
        GenericCreatureHolder<Cat> catHolder2 = catHolder;

        catHolder2.setCreature(new Cat());
        Cat cat = catHolder2.getCreature();

    }
}
