package ua.lviv.navpil.generics_are_hard.multiple;

import ua.lviv.navpil.generics_are_hard.multiple.model.Eatable;
import ua.lviv.navpil.generics_are_hard.multiple.model.Fruit;

public class EatableFruitHolder<T extends Fruit & Eatable> {

    private final T eatableFruit;
    private T alternativeFruit;

    //TODO: how can I simply hold a reference to anything which implements Eatable Fruit?
//    private Holder<? extends Fruit&Eatable> oneMoreFruit;

    public EatableFruitHolder(T eatableFruit) {
        this.eatableFruit = eatableFruit;
        //This does not compile because I can parametrize the class with whatever I want, e.g. Banana,
        //But how can I hold anything?, see line 11
//        this.alternativeFruit = new Apple();

    }

    public T getEatableFruit() {
        return eatableFruit;
    }

    //So maybe let's try to return apple as alternative always? What should be the signature?

    //This won't compile - F can be anything
//    public <F extends Fruit & Eatable> F getEatableFruitOrApple(boolean eatApple) {
//        if (eatApple) {
//            return new Apple();
//        } else {
//            return eatableFruit;
//        }
//    }

    //This one does not compile badly
//    public Holder<? extends Fruit & Eatable> getEatableFruitOrAppleHolder(boolean eatApple) {
//        if (eatApple) {
//            return new Holder<>(new Apple());
//        } else {
//            return new Holder<>(eatableFruit);
//        }
//    }

}
