# generics-are-hard

Some sample code with generics, which are hard. Explanation about each package can be found below

## voids

This is just a fun fact that you can parametrize with Void to avoid (see the pun?) putting any objects into the Holder

## bridge methods

Bridge methods are explained in [Java Tutorials](https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html)
 
They rarely cause troubles, but IntegerItem illustrates what can happen
  
## multiple

There is such thing as multiple bounds:
        
        MyItem<T extends Number & Iterable>
        
However you can get into problems when you want to store the reference to such bounded object.

To describe such a use case there are: `Eatable` interface, `Fruit` superclass and `Banana` and `Apple` which are both 
eatable and fruits (not all fruits are eatable).
 
`FoodEater` wants to eat anything which is `Eatable` and `Fruit`, so both `Apple` and `Banana` could be passed into the
method. But in case he wants to keep this food for later use and consume it at the evening, he has troubles with storing
a reference to such object. The workaround is to use two fields, one of type `Eatable` and one of type `Fruit` and store
the passed object in both places.

`EatableFruitHolder` is a bit different - it can hold the reference to the passed object, but can't create the alternative
one. However this one is easily explainable. 

<details>
<summary>Can you guess why?</summary>
When EatableFruitHolder is instantiated it is parametrized with Apple and T becomes Apple but we cant know anything about the type T in the constructor itself
</details>

`EatableFruitHolder` does not solve the `FoodEater` problem.

## type tokens

This is a very clever technique to store the reference to the parametrized class taken from [here](http://gafter.blogspot.com/2006/12/super-type-tokens.html).

`ClassBasedFavourites` stores favourite objects of each class with a class being a key. You can't differentiate between 
`List<String>` and `List<Integer>` with this technique, because of erasure. Now have a look at `TypeReference` and how
it works in `TypeReferenceFavourites`. This is not very straightforward, but it's nice and it works.

However be aware that generics are reified anyway - there is no magic, so please read comments for `TypeReferencesAreNotPerfect`.

## wildcards

TODO, this one is a bit complicated

## class handler

TODO, this one is a bit complicated