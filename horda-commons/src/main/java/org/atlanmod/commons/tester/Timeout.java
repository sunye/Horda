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
package org.atlanmod.commons.tester;

import org.atlanmod.commons.remote.Coordinator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eduardo Almeida.
 * @author sunye
 * @version 1.0
 * @since 1.0
 * @see java.lang.Runnable
 * @see java.lang.Thread
 */
public class Timeout implements Runnable {

    private static Logger LOG = Logger.getLogger(Timeout.class.getName());

    private long timeout;

    private Thread thread;

    public Timeout(Thread t, long millis) {
        timeout = millis;
        thread = t;
    }

    /**
     * measure the life time of an thread
     *
     * @param t the commons which will be registered
     * @param list the MethodDescription list
     * @see Coordinator#register(fr.inria.Horda.Tester,fr.inria.Horda.parser.MethodDescription)
     * @throws InterruptedException
     */
    public void run() {
        LOG.finest("Entering Timeout thread");
        try {
            thread.join(timeout);
            if (thread.isAlive()) {
                LOG.finest("I will interrupt execution thread");
                thread.interrupt();
            }
        } catch (InterruptedException e) {
            LOG.log(Level.SEVERE, "TimeoutThread Interrupted Exception", e);
        }
    }

}
