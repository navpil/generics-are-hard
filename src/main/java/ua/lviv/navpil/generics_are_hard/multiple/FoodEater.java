package ua.lviv.navpil.generics_are_hard.multiple;

import ua.lviv.navpil.generics_are_hard.multiple.model.Eatable;
import ua.lviv.navpil.generics_are_hard.multiple.model.Fruit;

public class FoodEater {
    // TODO: 17.10.2016 How to set this thing?
//    private <? extends Fruit & Eatable> foodToEat;

    public <T extends Fruit & Eatable> void setFoodToEat(T foodToEat) {
        //can we create a field to which we can assign this value?
        EatableFruitHolder<T> tEatableFruitHolder = new EatableFruitHolder<>(foodToEat);
//        this.foodToEat = foodToEat;
    }
}
