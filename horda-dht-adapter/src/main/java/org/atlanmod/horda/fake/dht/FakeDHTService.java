package org.atlanmod.horda.fake.dht;

import org.atlanmod.appa.api.DHT;
import org.atlanmod.appa.api.DHTService;

import javax.enterprise.inject.Produces;
import java.io.Serializable;

public class FakeDHTService implements DHTService {

    @Produces
    @Override
    public <K extends Serializable, V extends Serializable> DHT<K, V> createDHT() {
        return new FakeDHT<K, V>();
    }
}


