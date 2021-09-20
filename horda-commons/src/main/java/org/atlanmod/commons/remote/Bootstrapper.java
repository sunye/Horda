/*
This file is part of Horda.

Horda is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Horda is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Horda.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.atlanmod.commons.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 *
 * @author sunye
 */
public interface Bootstrapper extends Remote {

    /**
     * Registers a commons to this Bootstrapper.
     *
     * @param t The Tester to be registered.
     * @return the generated ID for the added node, or Integer.MAX_VALUE
     * if all nodes have already been registered
     * @throws java.rmi.RemoteException When a communication error occurs.
     */
    int register(DistributedTester t) throws RemoteException;

    /**
     * Makes the boostrapper leave the system.
     *
     * @throws RemoteException
     */
    void quit() throws RemoteException;
}

