package ua.lviv.navpil.generics_are_hard.builder;

import ua.lviv.navpil.generics_are_hard.builder.model.Being;
import ua.lviv.navpil.generics_are_hard.builder.model.Person;
import ua.lviv.navpil.generics_are_hard.builder.model.Student;

import java.util.logging.Logger;

public class NaiveApproach {

    public static final Logger LOG = Logger.getLogger(NaiveApproach.class.getName());

    public static void main(String[] args) {

        //Why Being?
        Being build = new PersonBuilder<>().withName("John").withType("Human").build();

        //Can change order of methods
        new PersonBuilder<>().withType("Person").withName("John").build();

        //It looks like we can rearrange methods (even though types don't match)...
        Being b1 = new StudentBuilder<>().withYear(1).withName("Paul").withType("Human").build();
        Person p1 = new StudentBuilder<>().withType("Human").withYear(1).withName("Paul").build();
        Being b2 = new StudentBuilder<>().withName("Human").withYear(1).withType("Paul").build();

        //...but not really
//        new StudentBuilder<>().withName("Human").withType("Paul").withYear(1).build();
//        new StudentBuilder<>().withType("Human").withName("Paul").withYear(1).build();
//        new StudentBuilder<>().withYear(1).withType("Human").withName("Paul").build();


        //However, how strange that may sound, the following syntax works
        StudentBuilder<StudentBuilder<StudentBuilder<StudentBuilder>>> studentBuilder = new StudentBuilder<>();

        //Watch it:
        Student s1 = studentBuilder.withYear(1).withName("Name").withType("Type").build();
        Student s2 = studentBuilder.withYear(1).withType("Type").withName("Name").build();
        Student s3 = studentBuilder.withType("Type").withYear(1).withName("Name").build();
        Student s4 = studentBuilder.withType("Type").withName("Name").withYear(1).build();
        Student s5 = studentBuilder.withName("Name").withType("Type").withYear(1).build();
        Student s6 = studentBuilder.withName("Name").withYear(1).withType("Type").build();

        //But only if we don't exceed the number of method calls
        Being b3 = studentBuilder.withYear(1).withName("Name").withType("Type").withType("Human").build();

        //I't like to find some nicer way
    }


    public static class BeingBuilder<SELF extends BeingBuilder> {

        protected String type;

        protected SELF self() {
            return (SELF)this;
        }

        SELF withType(String type) {
            this.type = type;
            return self();
        }

        Being build() {
            return new Being(type);
        }
    }

    public static class PersonBuilder<SELF extends PersonBuilder> extends BeingBuilder<SELF> {

        protected String name;

        SELF withName(String name) {
            this.name = name;
            return self();
        }

        Person build() {
            return new Person(type, name);
        }

    }

    public static class StudentBuilder<SELF extends StudentBuilder> extends PersonBuilder<SELF> {

        private int year;

        SELF withYear(int year) {
            this.year = year;
            return self();
        }

        Student build() {
            return new Student(type, name, year);
        }

    }

}
