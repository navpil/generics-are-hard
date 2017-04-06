package ua.lviv.navpil.generics_are_hard.class_handler;

public class HandlerFactoryImpl implements HandlerFactory {
    @Override
    public Class<? extends Handler> getHandler(String options) {
        if(options == null || options.isEmpty()) {
            return SimpleHandler.class;
        } else {
            return ComplicatedHandler.class;
        }
    }
}
