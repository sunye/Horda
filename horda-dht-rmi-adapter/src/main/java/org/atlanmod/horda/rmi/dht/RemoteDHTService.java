package org.atlanmod.horda.rmi.dht;

import org.atlanmod.appa.api.DHT;
import org.atlanmod.appa.api.DHTService;

import java.io.Serializable;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteDHTService implements DHTService {

    private Registry registry;

    @Override
    public <K extends Serializable, V extends Serializable> DHT<K, V> createDHT() {
        return null;
    }

    public void start() throws RemoteException {
        registry = LocateRegistry.getRegistry();
    }
}
