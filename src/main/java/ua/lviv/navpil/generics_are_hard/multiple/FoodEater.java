package ua.lviv.navpil.generics_are_hard.multiple;

public class FoodEater {
    // TODO: 17.10.2016 How to set this thing?
//    private <? extends Fruit & Eatable> foodToEat;

    public <T extends Fruit & Eatable> void setFoodToEat(T foodToEat) {
//        this.foodToEat = foodToEat;
    }
}
