package ua.lviv.navpil.generics_are_hard.deep_inheritance;

import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Freshman;
import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Person;
import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Student;

import java.util.logging.Logger;

public class Solution {

    public static final Logger LOG = Logger.getLogger(Solution.class.getName());

    public static void main(String[] args) {
        PersonDao<Freshman> dao1 = new FreshmanDao<>();
        PersonDao<Freshman> dao2 = new PersonDao<>();

        //Note that two daos have different behavior
        LOG.info("Saving with real freshman dao");
        saveWithDao(new Freshman(), dao1);
        LOG.info("\n");
        LOG.info("Saving with fake freshman dao");
        saveWithDao(new Freshman(), dao2);

    }

    private static <T extends Person> void saveWithDao(T person, PersonDao<T> dao) {
        dao.save(person);
    }


    public static class PersonDao<T extends Person> {

        void save(T person) {
            LOG.info("Saving person");
        }

    }

    /*
     * We need to keep generics...
     */
    public static class StudentDao<T extends Student> extends PersonDao<T> {

        @Override
        void save(T person) {
            super.save(person);
            LOG.info("Saving student");
        }
    }

    /*
     * ...so we can continue subclassing
     */
    public static class FreshmanDao<T extends Freshman> extends StudentDao<T> {

        //This now overrides
        @Override
        void save(T person) {
            super.save(person);
            LOG.info("Saving freshman");
        }
    }
}
