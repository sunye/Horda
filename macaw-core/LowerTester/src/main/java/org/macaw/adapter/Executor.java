/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.adapter;

import org.macaw.messages.MethodCall;

/**
 *
 * @author sunye
 */
public interface Executor {
    
    void execute(MethodCall mc);
    
}
