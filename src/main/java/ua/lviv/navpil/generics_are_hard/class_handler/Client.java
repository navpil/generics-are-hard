package ua.lviv.navpil.generics_are_hard.class_handler;

import ua.lviv.navpil.generics_are_hard.class_handler.model.Handler;

public class Client {
    public void doSomething(Class<? extends Handler> handler) {
        System.out.println("I'm instantiating the class and calling methods of a " + handler);
        Handler h = null;
        try {
            h = handler.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        h.handle();
    }

    public void doSomething(Constructor<Handler> constructor) {
        constructor.construct().handle();
    }
}
