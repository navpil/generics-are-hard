package ua.lviv.navpil.generics_are_hard.basics;

public class Exceptions {

    public static void assertClassCastExceptionIsThrown(Runnable runnable) {
        assertExceptionIsThrown(runnable, ClassCastException.class);
    }

    /*
    It would be nice to parametrize this method with the exception type and use it in catch clause,
    But it's impossible:

        private static <E extends Exception> void assertExceptionIsThrown(Runnable runnable){
            try {
                runnable.run();
                //Won't work:
            } catch (E e) {
                return;
            }
            //Won't work:
            throw new AssertionError(E.class.getName() + " should have been thrown");
        }
    */
    public static void assertExceptionIsThrown(Runnable runnable, Class<? extends Exception> exceptionClass){
        try {
            runnable.run();
        } catch (Exception e) {
            if (e.getClass().equals(exceptionClass)) {
                return;
            }
        }
        throw new AssertionError(exceptionClass.getName() + " should have been thrown");
    }
}
