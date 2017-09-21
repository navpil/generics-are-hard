package ua.lviv.navpil.generics_are_hard.builder;

import ua.lviv.navpil.generics_are_hard.builder.model.Being;
import ua.lviv.navpil.generics_are_hard.builder.model.Person;

import java.util.logging.Logger;

public class Problem {

    public static final Logger LOG = Logger.getLogger(Problem.class.getName());

    public static void main(String[] args) {

        //Hmm... we get the being instead of person.
        Being build = new PersonBuilder().withName("John").withType("Human").build();

        //Yes, we can cast it, but we'd better use generics...
        Person person = (Person)build;

        //...especially this won't work without generics. I can't call methods in another order
        //new PersonBuilder().withType("Person").withName()
    }


    public static class BeingBuilder {

        protected String type;

        BeingBuilder withType(String type) {
            this.type = type;
            return this;
        }

        Being build() {
            return new Being(type);
        }
    }

    public static class PersonBuilder extends BeingBuilder {

        private String name;

        PersonBuilder withName(String name) {
            this.name = name;
            return this;
        }

        Person build() {
            return new Person(type, name);
        }

    }

}
