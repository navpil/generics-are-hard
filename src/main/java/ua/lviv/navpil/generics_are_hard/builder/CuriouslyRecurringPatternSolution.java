package ua.lviv.navpil.generics_are_hard.builder;

import ua.lviv.navpil.generics_are_hard.builder.model.Being;
import ua.lviv.navpil.generics_are_hard.builder.model.Person;
import ua.lviv.navpil.generics_are_hard.builder.model.Student;

import java.util.logging.Logger;

public class CuriouslyRecurringPatternSolution {

    public static final Logger LOG = Logger.getLogger(CuriouslyRecurringPatternSolution.class.getName());

    public static void main(String[] args) {

        //This approach finally works
        Person p1 = new PersonBuilder<>().withName("John").withType("Human").build();
        Person p2 = new PersonBuilder<>().withType("Person").withName("John").build();

        Student s1 = new StudentBuilder<>().withYear(1).withName("Paul").withType("Human").build();
        Student s2 = new StudentBuilder<>().withType("Human").withYear(1).withName("Paul").build();
        Student s3 = new StudentBuilder<>().withName("Human").withYear(1).withType("Paul").build();
        Student s4 = new StudentBuilder<>().withName("Human").withType("Paul").withYear(1).build();
        Student s5 = new StudentBuilder<>().withType("Human").withName("Paul").withYear(1).build();
        Student s6 = new StudentBuilder<>().withYear(1).withType("Human").withName("Paul").build();

        //wild guess...
        Student build = new StudentBuilder<FinalBuilder>().withYear(1).withType("Human").withName("Paul").build();

        //However I still did not find out what is the StudentBuilder parametrized with. IntelliJ is of no help
//        StudentBuilder<SELF> selfStudentBuilder = new StudentBuilder<>();//????
    }

    // vom: perhaps that what it is parametrized with?
    public static class FinalBuilder extends StudentBuilder<FinalBuilder> {}


    public static class BeingBuilder<SELF extends BeingBuilder<SELF>> {

        protected String type;

        @SuppressWarnings("unchecked")
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

    public static class PersonBuilder<SELF extends PersonBuilder<SELF>> extends BeingBuilder<SELF> {

        protected String name;

        SELF withName(String name) {
            this.name = name;
            return self();
        }

        Person build() {
            return new Person(type, name);
        }

    }

    public static class StudentBuilder<SELF extends StudentBuilder<SELF>> extends PersonBuilder<SELF> {

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
