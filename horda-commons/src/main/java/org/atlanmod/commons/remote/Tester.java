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

import org.atlanmod.commons.common.MethodDescription;
import org.atlanmod.commons.tester.TesterImpl;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface represent a <i>commons/i>. A <i>commons</i> is a component who control a peer
 * to test and executes on him the <i>actions</i> of a <i>test case</i>.
 * @author Eduardo Almeida
 * @author Aboubakar Koita
 * @version 1.0
 * @since 1.0 
 * @see TesterImpl
 */
public interface Tester extends Remote {

    /**
     * Sets the coordinator for this commons.
     *
     * @param coord
     */
    public void setCoordinator(Coordinator coord) throws RemoteException;

    /**
     * Execute a <i>test case action</i> thanks to it description.
     *
     * @param m is a instance of <tt>MathodDescription</tt> class containing 
     * all informations allowing the correct execution of the <i>test case
     * action</i> that it describes.
     * 
     * @throws RemoteException because the method is distant
     */
    public void execute(MethodDescription m) throws RemoteException;

    /**
     * Return the <i>commons's</i> id.
     *
     * @throws RemoteException
     */
    public int getId() throws RemoteException;

    /**
     * Stop the <i>commons</i>.
     */
    public void kill() throws RemoteException;

    /**
     * Starts the commons.
     *
     * @throws RemoteException
     */
    public void start() throws RemoteException;

    /**
     * Asks the commons to leave the system.
     * 
     * @throws RemoteException
     */
    public void quit() throws RemoteException;

}
