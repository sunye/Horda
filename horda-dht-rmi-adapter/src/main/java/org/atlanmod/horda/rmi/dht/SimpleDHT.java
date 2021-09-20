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
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

@ParametersAreNonnullByDefault
public class SimpleDHT<K extends Serializable, V extends Serializable> implements RemoteDHT<K, V> {

    private final Map<K, V> map = new HashMap<>();

    @Override
    public void put(K key, V value) throws RemoteException {
        map.put(key, value);
    }

    @Override
    public V get(K key) throws RemoteException {
        return map.get(key);
    }

    @Override
    public void remove(K key) throws RemoteException {
        map.remove(key);
    }
}
