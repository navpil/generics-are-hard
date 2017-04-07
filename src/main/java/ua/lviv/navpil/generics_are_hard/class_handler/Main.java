package ua.lviv.navpil.generics_are_hard.class_handler;

import ua.lviv.navpil.generics_are_hard.class_handler.model.Handler;

public class Main {
    public static void main(String[] args) {
        HandlerFactory handlerFactory = new HandlerFactory();
        Client client = new Client();

        //This usage is ugly because of wildcards. Is there a better solution?
        Class<? extends Handler> handler = handlerFactory.getHandler("");
        client.doSomething(handler);


        //This is a possible workaround:
        Constructor<Handler> constructor = handlerFactory.getHandlerConstructor("");
        client.doSomething(constructor);
    }
}
