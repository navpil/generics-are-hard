package ua.lviv.navpil.generics_are_hard;

public class Main {
    public static void main(String[] args) {
        HandlerFactoryImpl handlerFactory = new HandlerFactoryImpl();

        //This usage is ugly because of wildcards. Is there a better solution?
        Class<? extends Handler> handler = handlerFactory.getHandler("");
        new Client().doSomething(handler);
    }
}
