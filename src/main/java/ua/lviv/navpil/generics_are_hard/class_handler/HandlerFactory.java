package ua.lviv.navpil.generics_are_hard.class_handler;

import ua.lviv.navpil.generics_are_hard.class_handler.model.ComplicatedHandler;
import ua.lviv.navpil.generics_are_hard.class_handler.model.Handler;
import ua.lviv.navpil.generics_are_hard.class_handler.model.SimpleHandler;

public class HandlerFactory {

    public Class<? extends Handler> getHandler(String options) {
        if(options == null || options.isEmpty()) {
            return SimpleHandler.class;
        } else {
            return ComplicatedHandler.class;
        }
    }

    public Constructor<Handler> getHandlerConstructor(String options) {
        if(options == null || options.isEmpty()) {
            return new Constructor<>(SimpleHandler.class);
        } else {
            return new Constructor<>(ComplicatedHandler.class);
        }
    }
}
