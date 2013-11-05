/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.tools;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author sunye
 */
public class Network {

    public static int freeLocalPort() {
        int port = -1;
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(0);
            port = socket.getLocalPort();
        } catch (IOException e) {
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        return port;
    }
}
