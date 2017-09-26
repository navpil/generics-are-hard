package ua.lviv.navpil.generics_are_hard.deep_inheritance;

import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Freshman;
import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Person;
import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Student;

import java.util.logging.Logger;

public class Problem {

    public static final Logger LOG = Logger.getLogger(Problem.class.getName());

    public static void main(String[] args) {

        /*
         * I want a method which will accept the Dao and a thing it should save.
         *
         * So for example I want to pass Person and PersonDao and have the Person saved,
         * If I pass Freshman and a FreshmanDao I want a Freshman be saved.
         *
         * But nothing works
         */
        PersonDao personDao = new FreshmanDao();

        /*
         * Since methods are not overloads, correct methods are not called.
         *
         * The "Saving freshman" will not appear.
         */
        saveWithDaoNoGenerics(new Freshman(), personDao);

        /*
         * Even if we try generics, it just won't work
         */
        saveWithDao(new Freshman(), personDao);

        /*
         * Even more generics won't work
         */
        saveWithDaoReallyGenerified(new Freshman(), personDao);

        /*
         * And if you think that passing all correct parameters will work, you're wrong
         */
        saveWithDaoReallyGenerified(new Freshman(), new FreshmanDao());

    }

    private static void saveWithDaoNoGenerics(Person person, PersonDao dao) {
        dao.save(person);
    }

    private static <T extends Person> void saveWithDao(T person, PersonDao dao) {
        dao.save(person);
    }

    private static <T extends Person, DAO extends PersonDao> void saveWithDaoReallyGenerified(T person, DAO dao) {
        dao.save(person);
    }



    public static class PersonDao {

        void save(Person person) {
            LOG.info("Saving person");
        }


    }


    public static class StudentDao extends PersonDao {

        void save(Student person) {
            super.save(person);
            LOG.info("Saving student");
        }
    }


    public static class FreshmanDao extends StudentDao {

        void save(Freshman person) {
            super.save(person);
            LOG.info("Saving freshman");
        }
    }
}
