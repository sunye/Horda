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
package org.atlanmod.horda.coordinator.coordinator;

import org.atlanmod.commons.remote.Tester;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sunye
 */
public class TesterQuit implements Runnable {

    private static final Logger LOG = Logger.getLogger(TesterQuit.class.getName());
    private Tester tester;

    /**
     *
     * @param t the commons.
     */
    public TesterQuit(Tester t) {
        assert t != null : "Null Tester";

        tester = t;
    }

    /**
     * Asks the Tester to quit.
     */
    public void run() {
        LOG.entering("TesterQuit", "run()");
        try {
            tester.quit();
        } catch (RemoteException e) {
            LOG.log(Level.SEVERE, null, e);
            for (StackTraceElement each : e.getStackTrace()) {
                LOG.severe(each.toString());
            }
        }
        LOG.exiting("TesterQuit", "run()");
    }
}
