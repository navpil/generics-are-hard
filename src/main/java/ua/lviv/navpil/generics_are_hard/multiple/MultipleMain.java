package ua.lviv.navpil.generics_are_hard.multiple;

import ua.lviv.navpil.generics_are_hard.multiple.model.Apple;
import ua.lviv.navpil.generics_are_hard.multiple.model.Banana;

public class MultipleMain {

    public static void main(String[] args) {

        //see the following classes to specific questions, which are quite the same
        //How to hold a reference to a thing which extends something and implements something?

        //We have fruits, which might not be eatable and eatables which might not be fruits

        new EatableFruitHolder<>( new Apple() );

        FoodEater foodEater = new FoodEater();
        foodEater.setFoodToEat(new Apple());
        foodEater.setFoodToEat(new Banana());

    }


}

