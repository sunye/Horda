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

import org.atlanmod.commons.common.MethodDescription;
import org.atlanmod.commons.remote.Tester;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Eduardo Almeida.
 * @author jeugenio
 * @version 1.0
 * @since 1.0
 * @see java.lang.Runnable
 * @see Tester
 * @see MethodDescription
 */
public class MethodExecute implements Runnable {

    private static final Logger LOG = Logger.getLogger(MethodExecute.class.getName());

    private Tester tester;
    private MethodDescription md;

    /**
     *
     * @param t the commons.
     * @param m the method to be executed by the commons.
     */
    public MethodExecute(Tester t, MethodDescription m) {
        assert t != null : "Null Tester";
        assert m != null : "Null MethodDescription";
        
        tester = t;
        md = m;
    }

    /**
     * Asks the Tester to execute the Method.
     */
    public void run() {
        LOG.entering("MethodExecute", "run()");
        try {
            if (!md.getWhen().equals("")) {
                LOG.log(Level.WARNING, "md.getWhen()={0}", md.getWhen());
                Process jtProcess = null;
                try {
                    jtProcess = Runtime.getRuntime().exec(md.getWhen());
                } catch (IOException ex) {
                    Logger.getLogger(MethodExecute.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    jtProcess.waitFor();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MethodExecute.class.getName()).log(Level.SEVERE, null, ex);
                }               
                /**try {
                    jtProcess.waitFor();
                } catch (InterruptedException ex) {
                    LOG.warning(ex.toString());
                }  */                     
            }
            if (md.getAnswers() > 0) {
                tester.execute(md);
            }
            //commons.execute(md);
        } catch (RemoteException e) {
            LOG.log(Level.SEVERE,null, e);
            for (StackTraceElement each : e.getStackTrace()) {
                LOG.severe(each.toString());
            }
        }
        LOG.exiting("MethodExecute", "run()");
    }
}
