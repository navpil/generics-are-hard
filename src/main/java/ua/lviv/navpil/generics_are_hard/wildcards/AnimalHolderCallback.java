package ua.lviv.navpil.generics_are_hard.wildcards;

import ua.lviv.navpil.generics_are_hard.Holder;

public class AnimalHolderCallback implements Callback<Holder<Animal>> {
    @Override
    public void success(Holder<Animal> t) {
        System.out.println("I have called back an animal " + t.item);
    }
}
