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

import fr.inria.atlanmod.commons.log.Log;
import org.atlanmod.appa.api.DHT;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@ParametersAreNonnullByDefault
public class RemoteDHTAdapter<K extends Serializable, V extends Serializable> implements DHT<K, V> {

    private RemoteDHT<K,V> dht;

    public RemoteDHTAdapter(RMIRegistry registry) {
        dht = (RemoteDHT<K, V>) registry.lookup(RemoteDHT.NAME);
    }

    public RemoteDHTAdapter(RemoteDHT<K,V> dht) {
        this.dht = dht;
    }

    public void store(K key, V value) {
        try {
            dht.put(key, value);
        } catch (RemoteException e) {
            Log.error("Remote error on RemoteDHTAdapter", e);
        }
    }

    @Override
    public Future<V> retrieve(K key) {
        V result;
        try {
            result =  dht.get(key);
        } catch (RemoteException e) {
            Log.error("Remote error on RemoteDHTAdapter", e);
            result = null;
        }
        return new GetValue<>(result);
    }


    @Override
    public void remove(K key) {
        try {
            dht.remove(key);
        } catch (RemoteException e) {
            Log.error("Remote error on RemoteDHTAdapter", e);
        }
    }

    @ParametersAreNonnullByDefault
    private static class GetValue<V> implements Future<V> {

        private V value;

        public GetValue(V value) {
            this.value = value;
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public V get() throws InterruptedException, ExecutionException {
            return value;
        }

        @Override
        public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return value;
        }
    }
}
