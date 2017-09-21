package ua.lviv.navpil.generics_are_hard.builder.model;

public class Person extends Being {

    private String name;

    public Person(String type, String name) {
        super(type);
        this.name = name;
    }
}
