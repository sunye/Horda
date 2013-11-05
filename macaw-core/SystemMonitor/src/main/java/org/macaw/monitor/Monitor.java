/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * information about the OS health (i.e., CPU, memory, I/O, swap, and networking). 
 */
package org.macaw.monitor;

/**
 *
 * @author sunye
 */
public interface Monitor {

    void start();

    void stop();
}
