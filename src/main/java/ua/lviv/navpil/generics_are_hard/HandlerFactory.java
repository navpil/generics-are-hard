package ua.lviv.navpil.generics_are_hard;

public interface HandlerFactory {
    Class<? extends Handler> getHandler(String options);
}
