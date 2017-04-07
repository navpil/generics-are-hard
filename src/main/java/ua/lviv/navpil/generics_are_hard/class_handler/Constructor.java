package ua.lviv.navpil.generics_are_hard.class_handler;

public class Constructor<T> {

    private Class<? extends T> clazz;

    public Constructor(Class<? extends T> clazz) {
        this.clazz = clazz;
    }


    public T construct() {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("");
        }
    }

}
