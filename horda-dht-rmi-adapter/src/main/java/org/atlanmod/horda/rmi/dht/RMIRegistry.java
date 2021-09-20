/*
 * Copyright (c) 2016-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package org.atlanmod.horda.rmi.dht;

import fr.inria.atlanmod.commons.log.Log;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

import javax.annotation.ParametersAreNonnullByDefault;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * RMIRegistry is a simple wrapper for using the {@code java.rmi.registry.Registry}
 */
@ParametersAreNonnullByDefault
public class RMIRegistry {

    public static final String NAME = "rmiregistry";
    public static final String PROTOCOL = "jrmp";
    public static final int PORT = Registry.REGISTRY_PORT;
    public static final int TIMES = 5;
    public static final int DELAY_MILLI = 300;

    /**
     * RMI registry (broker)
     */
    private Registry registry;

    public RMIRegistry(String hostName, int port) throws RemoteException {
        registry = LocateRegistry.getRegistry(hostName, port);
        Log.info("rmi registry found: {0}:{1}", hostName, port);
    }

    public RMIRegistry() throws RemoteException {
        registry = LocateRegistry.createRegistry(RMIRegistry.PORT);
    }

    public void rebind(String name, Remote object) {
        assert Objects.nonNull(registry);

        try {
            registry.rebind(name, object);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Remote lookup(String name) {

        RetryPolicy retryPolicy = new RetryPolicy()
                .retryOn(NotBoundException.class, RemoteException.class)
                .withDelay(DELAY_MILLI, TimeUnit.MILLISECONDS)
                .withMaxRetries(TIMES);

        return Failsafe.with(retryPolicy)
                .get(() -> registry.lookup(name));
    }
}
