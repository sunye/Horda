/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.messages;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author sunye
 */
public class MessageFactory {

    private static AtomicInteger lastId = new AtomicInteger(0);

    public static MethodCall newMethodCall(String name, Serializable[] args, int timeout) {

        return new MethodCall(lastId.incrementAndGet(), name, args, timeout);
    }

    public static MethodResult newMethodResult(MethodCall md) {
        return new MethodResult(lastId.incrementAndGet(), md);
    }
}
