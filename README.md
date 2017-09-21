# Generics Are Hard

Some sample code with generics, which are hard. Explanation about each package can be found below.

I also highly suggest reading [Java Generics Tutorial](https://docs.oracle.com/javase/tutorial/java/generics/index.html)
and section Generics in Effective Java by Joshua Bloch.

## basics

Generics work only compile time, there are no generics runtime. This is called "erasure". Most common problems is that 
 you cannot find the `TypeOfGeneric` runtime and you cannot do the `GenericOverload` of the methods.

## voids

This is just a fun fact that you can parametrize with Void to avoid (see the pun?) putting any objects into the Holder

## bridge methods

Bridge methods are explained in [Java Tutorials](https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html)
 
They rarely cause troubles, but `IntegerItem` illustrates what can happen
  
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
When EatableFruitHolder is instantiated it is parametrized with Apple and T becomes Apple but we cannot know anything 
about the type T in the constructor itself. So imagine we parametrized it with Banana, and still try to push Apple
as an alternative fruit. That won't work, because apples are not bananas. In this particular case you may use instanceof
and casts, but this is a slippery path.
</details>

What is not easily explainable is why method `getEatableFruitOrAppleHolder` does not compile.

`EatableFruitHolder` does not solve the `FoodEater` problem.

## type tokens

This is a very clever technique to store the reference to the parametrized class taken from [here](http://gafter.blogspot.com/2006/12/super-type-tokens.html).

`ClassBasedFavourites` stores favourite objects of each class with a class being a key. You can't differentiate between 
`List<String>` and `List<Integer>` with this technique, because of erasure. Now have a look at `TypeReference` and how
it works in `TypeReferenceFavourites`. This is not very straightforward, but it's nice and it works.

However be aware that generics are erased anyway - there is no magic, so please read comments for `TypeReferencesAreNotPerfect`.

## wildcards

Wildcards are a bit tricky and you'd better avoid them.

You should always prefer `<T extends MyClass>` over `<? extends MyClass>` if possible.

For example second signature is preferable:

 - `public void processWildcardAnimal(Holder<? extends Animal> animal)`
 - `public <T extends Animal> void processExactAnimal(Holder<T> animal)`

It's explained in the calls for `getWildcardHolder()` and `getTHolder()`. It's not easy to use the first method.

Syntax `public <? extends Animal> getAnimal()` is impossible and `public <T extends Animal> T getAnimal()` 
makes no sense unless you pass something in the method - class or instance (see `getAnimal()` overloads).

`processExactlyWithHolderCallback` requires to use type T, but `processWildcardWithHolderCallback` overloads would 
 accept anything into callback.
 
`processExactlyWithCallback` does not compile but it's easy to see why - you can parametrize it with everything, for 
example `Dog` so you can't use `Cat` inside (similar to alternative fruit in `EatableFruitHolder`).

However I could not find the explanation why 
        
        public void processWildcardWithCallback(Callback<? super Animal> callback)

compiles and 

        public <T super Animal> void processExactSuperWithCallback(Callback<T> callback) {
        
does not. Can you?

## class handler

On the contrary to the wildcards section, it seems impossible to avoid `<? extends Handler>` syntax when you're dealing
with classes themselves. Let's say that there is some `HandlerFactory` which would return us the Handler class we should
use (`Handler` has two children: `SimpleHandler` and `ComplicatedHandler`). You'd like to instantiate those later.

One of the possible workaround is to use the `Constructor` object, which would keep the wildcard inside.

## deep inheritance

The `Problem.java` describes what I want to achieve. In particular I want to be able to save the object which is down
in the deep inheritance hierarchy.

When inheritance is two layers, there is a natural way of writing generics as described in `NotReallyASolution.java`.

The Solution.java shows how to solve the problem.

Please note, however, that we can parametrize PersonDao with Freshman and behavior is different for two seemingly the
same `PersonDao<Freshman>`. We can't prevent PersonDao be _not_ parametrized with FreshmanDao. Or can we?

## builder

Builder, generics and hierarchy is hard. I describe a problem in a `Problem.java`. I show the usual solution for the
two-level hierarchy in the `NotReallyASolution.java`. Why it's not really a solution is described in the class.

As you see, there is no `Solution.java`. It's yet to be written. The naive approach of simply merging it with a
'deep inheritance' Solution just did not work.
