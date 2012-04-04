/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevoree.library.freepastry;

import java.io.IOException;
import java.net.InetSocketAddress;
import rice.environment.Environment;
import rice.pastry.NodeIdFactory;
import rice.pastry.PastryNodeFactory;
import rice.pastry.socket.SocketPastryNodeFactory;
import rice.pastry.standard.RandomNodeIdFactory;
import rice.pastry.PastryNode;
import rice.pastry.commonapi.PastryIdFactory;

/**
 *
 * @author sunye
 */
public class PastryBootstrapper {

    private PastryNodeFactory factory;
    private Environment environment;
    private InetSocketAddress socketAddress;
    private NodeIdFactory nidFactory;
    private PastryNode node;
    //private PastryIdFactory pastryIdFactory;

    public PastryBootstrapper(InetSocketAddress address) {
        socketAddress = address;
        environment = new Environment();
        nidFactory = new RandomNodeIdFactory(environment);
    }

    public boolean bootsrap() throws InterruptedException, IOException {

        factory = new SocketPastryNodeFactory(nidFactory, 
                socketAddress.getPort(), environment);
        node = factory.newNode();
        node.boot(socketAddress);

        synchronized (this) {
            while (!node.isReady() && !node.joinFailed()) {
                // delay so we don't busy-wait
                this.wait(500);
                // abort if can't join
                if (node.joinFailed()) {
                    throw new IOException("Could not join the FreePastry ring. "
                            + "Reason:" + node.joinFailedReason());
                }
            }
        }
        return true;
    }
}
