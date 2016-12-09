package ua.lviv.navpil.generics_are_hard.type_tokens;

import java.util.ArrayList;

/**
 * Even though TypeReferences are cool, it's not omnipotent.
 * Try to run this class and you'll see, that it does not know about real generic type. It's just T
 */
public class TypeReferencesAreNotPerfect {

    public static void main(String [] args) {
        TypeReference<ArrayList<String>> typeReference = createTypeReference(new ArrayList<String>());
        System.out.println(typeReference.getType());
    }

    private static<T> TypeReference<T> createTypeReference(T o) {
        return new TypeReference<T>() {};
    }
}
