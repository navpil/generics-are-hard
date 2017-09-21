package ua.lviv.navpil.generics_are_hard.deep_inheritance;

import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Freshman;
import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Person;
import ua.lviv.navpil.generics_are_hard.deep_inheritance.model.Student;

import java.util.logging.Logger;

public class NotReallyASolution {

    public static final Logger LOG = Logger.getLogger(NotReallyASolution.class.getName());

    public static void main(String[] args) {
        /*
         * Usual approach of generifying will work for two level hierarchy
         * StudentDao will save a student correctly
         */
        StudentDao studentDao = new StudentDao();
        saveWithDao(new Student(), studentDao);

        LOG.info("\n");
        /*
         * However this simple way does not work for Freshman, who is too deep in the hierarchy
         * And 'Saving freshman' will never be printed. Even though 'Saving student' will work for FreshmanDao
         */
        FreshmanDao dao1 = new FreshmanDao();
        PersonDao<Freshman> dao2 = new PersonDao<>();

        LOG.info("Saving with real freshman dao");
        saveWithDao(new Freshman(), dao1);
        LOG.info("\n");
        LOG.info("Saving with fake freshman dao");
        saveWithDao(new Freshman(), dao2);
    }

    private static <T extends Person> void saveWithDao(T person, PersonDao<T> dao) {
        dao.save(person);
    }


    /*
     * Let's generify PersonDao
     *
     */
    public static class PersonDao<T extends Person> {

        void save(T person) {
            LOG.info("Saving person");
       }

    }


    /*
     * Usual way of subclassing
     */
    public static class StudentDao extends PersonDao<Student> {

        @Override
        void save(Student person) {
            super.save(person);
            LOG.info("Saving student");
        }
    }


                                                        /*impossible:*/
    public static class FreshmanDao extends StudentDao /*<Freshman>*/ {

        //This does not override and it will never gets called
//    @Override
        void save(Freshman person) {
            super.save(person);
            LOG.info("Saving freshman");
            throw new AssertionError("This will never be called");
        }
    }
}
