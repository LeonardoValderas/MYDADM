package com.valdroide.gonzalezdanielaadm.lib.base;

/**
 * Created by LEO on 29/1/2017.
 */
public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
