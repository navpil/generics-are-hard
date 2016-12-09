package ua.lviv.navpil.generics_are_hard.type_tokens;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Taken from here:
 * http://gafter.blogspot.com/2006/12/super-type-tokens.html
 *
 * @param <T>
 */
public abstract class TypeReference<T> {

    private final Type type;

    public TypeReference() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return this.type;
    }

}
