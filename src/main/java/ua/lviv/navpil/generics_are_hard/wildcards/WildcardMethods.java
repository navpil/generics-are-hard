package ua.lviv.navpil.generics_are_hard.wildcards;

import ua.lviv.navpil.generics_are_hard.Holder;

public class WildcardMethods {

    public static void main(String[] args) {
        WildcardMethods methods = new WildcardMethods();
        methods.test();
    }

    private void test() {
        //this works, but see comments for the methods:
        processExactAnimal( getCatHolder() );
        processWildcardAnimal( getCatHolder() );

        //Prefer type bounds to wildcard bounds
        Holder<? extends Animal> wildcardHolder = getWildcardHolder();
        //Error: incompatible types: ua.lviv.navpil.generics_are_hard.wildcards.Cat cannot be converted to capture#1 of ? extends ua.lviv.navpil.generics_are_hard.wildcards.Animal
//        wildcardHolder.item = new Cat();

        Holder<Animal> tHolder = getTHolder();
        //OK:
        tHolder.item = new Cat();
        tHolder.item = new Animal();

        //Prefer type bounds to wildcard bounds
        processWildcardWithHolderCallback( new Callback<Holder<? extends Animal>>() {
            @Override
            public void success(Holder<? extends Animal> holder) {
                //can't parametrize it with dog
            }
        } );

        processExactlyWithHolderCallback( new Callback<Holder<Dog>>() {
            @Override
            public void success(Holder<Dog> holder) {
                holder.item.iAmDog();
            }
        }, new Dog() );


    }

    //Both of the methods are fine, but <?> gives no guarantee of what the type is, while <T> gives such guarantee
    public <T extends Animal> void processExactAnimal(Holder<T> animal) {
        System.out.println("I'm processing the exact animal " + animal.item);
        //legal: T == T
        animal.item = animal.item;
    }
    public void processWildcardAnimal(Holder<? extends Animal> animal) {
        System.out.println("I'm processing the wildcard animal " + animal.item);
        //illegal: <? extends Animal> != <? extends Animal>
        //animal.item = animal.item;
    }

    public Holder<? extends Animal> getWildcardHolder() {
        return new Holder<>();
    }

    public <T extends Animal> Holder<T> getTHolder() {
        return new Holder<>();
    }

    //impossible code
//    public <? extends Animal> getAnimal();
//    public <? extends Animal> Animal getAnimal();

    //Possible code, but doesn't bring us anywhere.
    public <T extends Animal> T getAnimal() throws IllegalAccessException, InstantiationException {
        //We can't instantiate T here
        T t = null;
        return t;
    }

    public <T extends Animal> T getAnimal(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        //We can instantiate T only by reflection
        return tClass.newInstance();
    }

    public <T extends Animal> T getAnimal(T animal) throws IllegalAccessException, InstantiationException {
        //We can return same animal which is passed.
        //Can be convenient because 'public Animal getAnimal(Animal a)' will return us Animal always, not an type of
        // and object which was passed into method.
        return animal;
    }

    //Makes more sense in the class:
    public abstract class AnimalHolder<T extends Animal> {
        public abstract T getAnimal();
    }

    //Callback which can work with any Holder which contains any Animal. No exact parametrization
    public void processWildcardWithHolderCallback(Callback<Holder<? extends Animal>> callback) {
        callback.success( getCatHolder() );
        callback.success( getWildcardHolder() );
    }

    //Should be parametrized with some kind of Animal.
    public <T extends Animal> void processExactlyWithHolderCallback(Callback<Holder<T>> callback, T o) {
        //Does not work - we parametrize with something, which does not need to be a cat
//        callback.success( getCatHolder() );
        callback.success( getHolder(o) );
    }

////This does not compile, because we can parametrize with anything, for example Dog.
//    public <T extends Animal> void processExactlyWithCallback(Callback<T> callback) {
//        callback.success( new Cat() );
//    }
//

    //Why this compiles
    public void processWildcardWithCallback(Callback<? super Animal> callback) {
        callback.success( new Cat() );
    }

    //And this not
//    public <T super Animal> void processExactSuperWithCallback(Callback<T> callback) {
//    }

    //Because while bound wildcards and bound types look similar, there are differences between them (see README)


    public Holder<Cat> getCatHolder() {
        Holder<Cat> catHolder = new Holder<>();
        catHolder.item = new Cat();
        return catHolder;
    }

    public Holder<Animal> getAnimalCat() {
        Holder<Animal> catHolder = new Holder<>();
        catHolder.item = new Cat();
        return catHolder;
    }


    public <T> Holder<T> getHolder(T o) {
        Holder<T> tHolder = new Holder<T>();
        tHolder.item = o;
        return tHolder;
    }



}

