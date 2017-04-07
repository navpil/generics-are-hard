package ua.lviv.navpil.generics_are_hard.basics;

import ua.lviv.navpil.generics_are_hard.Holder;

public class TypeOfGeneric {

    public static <T> Class<T> getClassOfGenerics(Holder<T> holder) {
        //Impossible to do, information about generics does not exist runtime
//        return T.class;
        return null;
    }

    public static Class<?> getClassOfGenerics2(Holder<?> holder) {
        //Impossible to do, information about generics does not exist runtime, (method getGenericClass() does not exist)
//        return holder.getGenericClass();
        return null;
    }

}
