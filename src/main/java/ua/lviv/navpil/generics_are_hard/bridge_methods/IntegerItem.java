package ua.lviv.navpil.generics_are_hard.bridge_methods;

public class IntegerItem extends Item<Integer> {
    
    public IntegerItem(Integer value) {
        super(value);
    }

    @Override
    public void setValue(Integer value) {
        super.setValue(value);
    }

    //This won't compile, even though it looks like the signature is different from the setValue(Integer)
    //please see https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html for more information
//    public void setValue(Object value) {
//
//    }
}
