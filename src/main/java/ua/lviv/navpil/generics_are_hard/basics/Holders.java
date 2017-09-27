package ua.lviv.navpil.generics_are_hard.basics;

import static ua.lviv.navpil.generics_are_hard.Mammals.*;

public class Holders {

    //These two classes will look very similar runtime, because generics will be erased, but compiler will add casts
    // behind the scenes

    public static class CreatureHolder {
        private Creature creature;

        public CreatureHolder(Creature creature) {
            this.creature = creature;
        }

        public Creature getCreature() {
            return creature;
        }

        public void setCreature(Creature creature) {
            this.creature = creature;
        }
    }

    //But we see the parameter T in the compiled bytecode. Why so? Well, probably because we need information about
    // generics at _compile_ time. But they are erased in _runtime_. Be sure, runtime this field becomes just
    // private Creature creature;

    public static class GenericCreatureHolder<T extends Creature> {
        private T creature;

        public GenericCreatureHolder(T creature) {
            this.creature = creature;
        }

        public T getCreature() {
            return creature;
        }

        public void setCreature(T creature) {
            this.creature = creature;
        }
    }

    //If I want something which has the same behaviour as CreatureHolder and I don't want to bother with those funny
    // triangle brackets, I can do this:
    public static class OnlyCreatureHolder extends GenericCreatureHolder<Creature> {
        public OnlyCreatureHolder(Creature creature) {
            super(creature);
        }
    }
}

//generics-are-hard/target/classes> javap -p -c 'ua.lviv.navpil.generics_are_hard.basics.Holders$CreatureHolder'
//generics-are-hard/target/classes> javap -p -c 'ua.lviv.navpil.generics_are_hard.basics.Holders$GenericCreatureHolder'

/*
Compiled from "Holders.java"
public class ua.lviv.navpil.generics_are_hard.basics.Holders$CreatureHolder {
  private ua.lviv.navpil.generics_are_hard.Mammals$Creature creature;

  public ua.lviv.navpil.generics_are_hard.basics.Holders$CreatureHolder(ua.lviv.navpil.generics_are_hard.Mammals$Creature);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #2                  // Field creature:Lua/lviv/navpil/generics_are_hard/Mammals$Creature;
       9: return

  public ua.lviv.navpil.generics_are_hard.Mammals$Creature getCreature();
    Code:
       0: aload_0
       1: getfield      #2                  // Field creature:Lua/lviv/navpil/generics_are_hard/Mammals$Creature;
       4: areturn

  public void setCreature(ua.lviv.navpil.generics_are_hard.Mammals$Creature);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field creature:Lua/lviv/navpil/generics_are_hard/Mammals$Creature;
       5: return
}

*/

/*
Compiled from "Holders.java"
public class ua.lviv.navpil.generics_are_hard.basics.Holders$GenericCreatureHolder<T extends ua.lviv.navpil.generics_are_hard.Mammals$Creature> {
  private T creature;

  public ua.lviv.navpil.generics_are_hard.basics.Holders$GenericCreatureHolder(T);
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: aload_0
       5: aload_1
       6: putfield      #2                  // Field creature:Lua/lviv/navpil/generics_are_hard/Mammals$Creature;
       9: return

  public T getCreature();
    Code:
       0: aload_0
       1: getfield      #2                  // Field creature:Lua/lviv/navpil/generics_are_hard/Mammals$Creature;
       4: areturn

  public void setCreature(T);
    Code:
       0: aload_0
       1: aload_1
       2: putfield      #2                  // Field creature:Lua/lviv/navpil/generics_are_hard/Mammals$Creature;
       5: return
}

*/


