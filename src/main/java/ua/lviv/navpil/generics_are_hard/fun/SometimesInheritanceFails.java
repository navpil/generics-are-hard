package ua.lviv.navpil.generics_are_hard.fun;

public class SometimesInheritanceFails {

    //No generics
    public static class ThisObviouslyWorks {

        public static class Person {
            public Person getSelf() {return this;}
        }

        public static class Student  extends Person{
            @Override
            public Student getSelf() {return this;}
        }
    }

    //Both have generics
    public static class AndThisAlsoWorks {

        public static class Person {
            public <T> Person getSelf() {return this;}
        }

        public static class Student  extends Person{
            @Override
            public <T> Student getSelf() {return this;}
        }
    }

    //Parent <T> and child is not
    public static class ThisWorksAsWell {

        public static class Person {
            public <T> Person getSelf() {return this;}
        }

        public static class Student  extends Person{
            @Override
            public Student getSelf() {return this;}
        }
    }

    //Parent raw and child is <T>
    public static class ButWhyThisFails {

//        name clash:
//              <T>getSelf() in ButWhyThisFails.Student
//          and getSelf() in ButWhyThisFails.Person
//       have the same erasure, yet neither overrides the other

        public static class Person {
            public Person getSelf() {return this;}
        }

        public static class Student  extends Person{
    //        @Override
//            public <T> Student getSelf() {return this;}
        }
    }

}
