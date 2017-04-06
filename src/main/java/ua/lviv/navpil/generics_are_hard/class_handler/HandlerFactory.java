package ua.lviv.navpil.generics_are_hard.class_handler;

public interface HandlerFactory {
    Class<? extends Handler> getHandler(String options);
}
