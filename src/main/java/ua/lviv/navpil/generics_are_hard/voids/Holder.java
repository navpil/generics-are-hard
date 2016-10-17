package ua.lviv.navpil.generics_are_hard.voids;

public class Holder<T> {

    public T item;

    public static void main(String [] args) {
        Holder<Void> voidHolder = new Holder<>();
    }
}
