package ua.lviv.navpil.generics_are_hard.wildcards;

import ua.lviv.navpil.generics_are_hard.Holder;

public class WildcardMethods {

    public static void main(String[] args) {
        WildcardMethods methods = new WildcardMethods();
        methods.test();
    }

    private void test() {
        //this works:
        processExactAnimal( getCatHolder() );
        processWildcardAnimal( getCatHolder() );

        processWildcardWithHolderCallback( new Callback<Holder<? extends Animal>>() {
            @Override
            public void success(Holder<? extends Animal> holder) {

            }
        } );

        processExactlyWithHolderCallback( new Callback<Holder<Dog>>() {
            @Override
            public void success(Holder<Dog> holder) {
                holder.item.iAmDog();
            }
        }, new Dog() );


    }

    public <T extends Animal> void processExactlyWithHolderCallback(Callback<Holder<T>> callback, T o) {
        callback.success( getHolder(o) );
    }

    public void processWildcardWithHolderCallback(Callback<Holder<? extends Animal>> callback) {
        callback.success( getCatHolder() );
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
//    public <T super Animal> void processWildcardWithCallback2(Callback<T> callback) {
//    }

    public <T extends Animal> void processExactAnimal(Holder<T> animal) {
        System.out.println("I'm processing the exact animal " + animal.item);
    }

    public void processWildcardAnimal(Holder<? extends Animal> animal) {
        System.out.println("I'm processing the exact animal " + animal.item);
    }


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

