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
package org.atlanmod.horda.coordinator.bootstrapper;

import org.atlanmod.commons.remote.DistributedTester;
import org.atlanmod.commons.util.HNode;
import org.atlanmod.commons.util.HTree;
import org.atlanmod.commons.util.TesterUtil;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Veronique Pelleau
 * @author Aboubakar Koita
 */
public class ConcreteBtreeStrategy implements TreeStrategy {

    private static final Logger LOG = Logger.getLogger(ConcreteBtreeStrategy.class.getName());
    /**
     * default values for global variables
     */
    private TesterUtil defaults;
    /**
     * Tree containing Tester Id X Tester
     */
    private final HTree<Integer, DistributedTester> testers;
    /**
     * Number of expected testers.
     */
    private final int expectedTesters;

    public ConcreteBtreeStrategy(TesterUtil tu) {
        defaults = tu;
        testers = new HTree<Integer, DistributedTester>(defaults.getTreeOrder());
        expectedTesters = defaults.getExpectedTesters();
    }

    /**
     *
     * @param tester
     * @return
     * @throws RemoteException
     */
    public void register(DistributedTester tester) {
        LOG.entering("ConcreteBtreeStrategy", "register(Tester)");

        int id = testers.size();
        testers.put(new Integer(id), tester);
        LOG.exiting("ConcreteBtreeStrategy", "register(Tester)");

    }

    public void buildTree() {
    }


    public int getNodesSize() {
        //return tree.getNodesSize();
        return 0;
    }

    public void setCommunication() {
        HNode<Integer, DistributedTester> node = testers.head();
        this.setCommunication(node);
    }

    private void setCommunication(HNode<Integer, DistributedTester> n) {
        assert !n.isLeaf();

        HNode<Integer, DistributedTester>[] children = n.children();
        List<DistributedTester> nodes = new ArrayList<DistributedTester>(children.length);
        for (HNode<Integer, DistributedTester> each : children) {
            nodes.add(each.value());
        }
        try {
            LOG.fine(String.format("Registering %d testers to commons %d", nodes.size(), n.value().getId()));
        } catch (RemoteException ex) {
            Logger.getLogger(ConcreteBtreeStrategy.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            DistributedTester c =  n.value();
            c.registerTesters(nodes);
        } catch (RemoteException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        for (HNode<Integer, DistributedTester> each : children) {
            if (!each.isLeaf()) {
                this.setCommunication(each);
            }
        }
    }

    public int getRegistered() {
        return testers.size();
    }


    public void startRoot() throws RemoteException {
        DistributedTester root = testers.head().value();
        root.start();
    }

    public void cleanUp() {
        testers.clear();
    }
}


