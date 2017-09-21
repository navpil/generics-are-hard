package ua.lviv.navpil.generics_are_hard.builder.model;

public class Student extends Person {

    private int year;

    public Student(String type, String name, int year) {
        super(type, name);
        this.year = year;
    }
}
