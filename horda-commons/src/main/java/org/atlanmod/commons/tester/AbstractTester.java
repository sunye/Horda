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

import org.atlanmod.commons.util.HordaLogger;
import org.atlanmod.commons.util.TesterUtil;
import org.atlanmod.commons.Globals;
import org.atlanmod.commons.remote.GlobalVariables;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sunye
 */
public abstract class AbstractTester {

    private static final Logger LOG = Logger.getLogger(AbstractTester.class.getName());
    protected int id;
    protected transient GlobalVariables globals;
    protected transient TesterUtil defaults = TesterUtil.instance;

    /**
     * No arguments constructor.
     * Needed for serialization/deserialization of subclasses.
     */
    public AbstractTester() {
    }

    public AbstractTester(GlobalVariables gv) {
        this.globals = gv;
    }

    /**
     * Returns this commons's id
     * @return the commons's id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the commons id.
     * @param i
     */
    public void setId(int i) {
        this.id = i;
        this.initialzeGlobals();
    }

    @Override
    public String toString() {
        return String.format("Tester: %d", id);
    }

    public void put(Integer key, Object object) throws RemoteException {
        this.globalTable().put(key, object);
    }

    public Map<Integer, Object> getCollection() throws RemoteException {
        return this.globalTable().getCollection();
    }

    public Object get(Integer key) throws RemoteException {
        return this.globalTable().get(key);
    }

    public boolean containsKey(Integer key) throws RemoteException {
        return this.globalTable().containsKey(key);
    }

    public void clear() throws RemoteException {
        this.globalTable().clearCollection();
    }

    protected final GlobalVariables globalTable() {
        return globals;
    }

    protected void initializeLogger() {
        try {
            HordaLogger.createLogger(defaults, String.format("Tester%d.log", id));
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    private void initialzeGlobals() {
        Globals.setId(id);
    }
}
