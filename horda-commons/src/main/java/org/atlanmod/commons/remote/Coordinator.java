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

import org.atlanmod.commons.base.ResultSet;
import org.atlanmod.commons.coordinator.TesterRegistration;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Coordinator extends Remote {

    int register(Tester t) throws RemoteException;

    void registerMethods(TesterRegistration tr)
            throws RemoteException;

    void methodExecutionFinished(ResultSet result)
            throws RemoteException;

    /**
     * Finish the test case and calculates the global oracle
     *
     * @param Tester
     * @param Verdict
     *
     */
    void quit(Tester t) throws RemoteException;
}
