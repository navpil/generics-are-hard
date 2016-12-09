package ua.lviv.navpil.generics_are_hard.type_tokens;

import java.util.HashMap;
import java.util.Map;

/**
 * This is nice, but can't hold references to List<String>
 * See the TypeReferenceFavourites for another example
 *
 */
public class ClassBasedFavorites {
    private Map<Class<?>, Object> favorites =
            new HashMap<>();

    public <T> void setFavorite(Class<T> klass, T thing) {
        favorites.put(klass, thing);
    }

    public <T> T getFavorite(Class<T> klass) {
        return klass.cast(favorites.get(klass));
    }

    public static void main(String [] args) {
        ClassBasedFavorites f = new ClassBasedFavorites();
        f.setFavorite(String.class, "Java");
        f.setFavorite(Integer.class, 0xcafebabe);
        String s = f.getFavorite(String.class);
        int i = f.getFavorite(Integer.class);
    }
}
