package ua.lviv.navpil.generics_are_hard.bridge_methods;

public class Item<T> {

    private T value;

    public Item(T value) {
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
