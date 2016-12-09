package ua.lviv.navpil.generics_are_hard.type_tokens;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This one is improved version of favourites so it can hold references to generalized types
 */
public class TypeReferenceFavorites {
    private Map<Type, Object> favorites =
            new HashMap<>();

    public <T> void setFavorite(TypeReference<T> reference, T thing) {
        favorites.put(reference.getType(), thing);
    }

    public <T> T getFavorite(TypeReference<T> klass) {
        return (T) favorites.get(klass.getType());
    }

    public static void main( String[] args )
    {
        TypeReferenceFavorites f = new TypeReferenceFavorites();
        f.setFavorite(new TypeReference<String>() {}, "Java");
        f.setFavorite(new TypeReference<Integer>() {}, 0xcafebabe);
        ArrayList<List<String>> listOfLists = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Just for test");
        listOfLists.add(strings);
        f.setFavorite(new TypeReference<List<List<String>>>() {}, listOfLists);
        String s = f.getFavorite(new TypeReference<String>() {});
        int i = f.getFavorite(new TypeReference<Integer>() {});

        System.out.println(s);
        System.out.println(i);
        System.out.println(f.getFavorite(new TypeReference<List<List<String>>>() {}));
    }

}
