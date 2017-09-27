package ua.lviv.navpil.generics_are_hard.basics;

import static ua.lviv.navpil.generics_are_hard.Mammals.*;
import static ua.lviv.navpil.generics_are_hard.basics.Exceptions.assertClassCastExceptionIsThrown;
import static ua.lviv.navpil.generics_are_hard.basics.Holders.*;

public class WhyDoWeNeedGenerics {

    public static void main(String[] args) {
        //What's the difference between non-generic and generic class?

        //No matter what we put into the creature holder, it will always return us a creature
        Creature number = new CreatureHolder(new Creature()).getCreature();
        Creature creature = new CreatureHolder(new Cat()).getCreature();

        //And often it's ok. Relying on polymorphism is ok in many cases. We do not always want to know the exact
        // type of the variable;

        //But what if we want to get the cat back?

        //Without generics we would have to create subclasses of CreatureHolder:
        class CatCreatureHolder extends CreatureHolder {

            public CatCreatureHolder(Cat cat) {
                super(cat);
            }

            @Override
            public Cat getCreature() {
                return (Cat)super.getCreature();
            }

            //And now we are a bit stuck with setters
            public void setCreature(Cat creature) {
                super.setCreature(creature);
            }

            //Should we throw ClassCastException here? There is no compile time fix
            public void setCreature(Creature creature) {
                setCreature((Cat) creature);
            }
        }

        CatCreatureHolder catCreatureHolder = new CatCreatureHolder(new Cat());
        //All of these setters throw ClassCastExceptions
        assertClassCastExceptionIsThrown(() -> catCreatureHolder.setCreature(new Dog()));
        assertClassCastExceptionIsThrown(() -> catCreatureHolder.setCreature(new Mammal()));
        assertClassCastExceptionIsThrown(() -> catCreatureHolder.setCreature(new Creature()));

        //There are at least two ways of fixing this issue without generics:
        // 1. Immutability will remove the problem with setters.
        // 2. Do not use subclassing at all and prefer other techniques, like Composition.

        //But generics make our life easier:

        GenericCreatureHolder<Cat> catHolder = new GenericCreatureHolder<Cat>(new Cat());
        Cat c1 = catHolder.getCreature();

        //Compile time check, no ClassCastExceptions:
        catHolder.setCreature(new Cat());
//        catHolder.setCreature(new Creature());//does not compile
    }

}
