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

import javax.annotation.ParametersAreNonnullByDefault;
import java.rmi.Remote;
import java.rmi.RemoteException;

@ParametersAreNonnullByDefault
public interface RemoteDHT<K, V> extends Remote {

    static String NAME = "RemoteDHT";

    void put(K key, V value) throws RemoteException;

    V get(K key) throws RemoteException;

    void remove(K key) throws RemoteException;
}
