package ua.lviv.navpil.generics_are_hard.builder;

import ua.lviv.navpil.generics_are_hard.builder.model.Being;
import ua.lviv.navpil.generics_are_hard.builder.model.Person;
import ua.lviv.navpil.generics_are_hard.builder.model.Student;

import java.util.logging.Logger;

public class NotReallyASolution {

    public static final Logger LOG = Logger.getLogger(NotReallyASolution.class.getName());

    public static void main(String[] args) {

        //No casts
        Person person1 = new PersonBuilder().withName("John").withType("Human").build();

        //Can change order of methods
        Person person2 = new PersonBuilder().withType("Person").withName("John").build();

        //But how to make the student builder? We are now back were we've started.
        Person build = new StudentBuilder().withYear(1).withName("Paul").withType("Human").build();
    }


    public static class BeingBuilder<SELF extends BeingBuilder<SELF>> {

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

    public static class PersonBuilder extends BeingBuilder<PersonBuilder> {

        protected String name;

        PersonBuilder withName(String name) {
            this.name = name;
            return self();
        }

        Person build() {
            return new Person(type, name);
        }

    }

    public static class StudentBuilder extends PersonBuilder {

        private int year;

        StudentBuilder withYear(int year) {
            this.year = year;
            return this;
        }

        Student build() {
            return new Student(type, name, year);
        }

    }

}
