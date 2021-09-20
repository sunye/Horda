package org.atlanmod.horda.fake.dht;

import fr.inria.atlanmod.commons.log.Log;
import org.atlanmod.appa.api.DHT;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Any;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * org.atlanmod.horda.fake.dht.FakeDHT is a simple, centralized, Hash Table.
 *
 * @author AtlanMod team.
 */
public class FakeDHT<K extends Serializable, V extends Serializable> implements DHT<K,V> {

    private Map<K, V> data = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void store(K key, V value) throws IOException {
        data.put(key, value);
    }

    @Override
    public Future<V> retrieve(K key) throws IOException {
        if (!data.containsKey(key)) {
            Log.warn("Key [{}] does not exist.");
        }
        return new FakeAnswer<V>(data.get(key));
    }

    @Override
    public void remove(K key) throws IOException {
        data.remove(key);
    }

    static class FakeAnswer<V> implements Future<V> {
        private final V value;

        public FakeAnswer(V value) {
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
            return true;
        }

        @Override
        public V get() throws InterruptedException, ExecutionException {
            return this.value;
        }

        @Override
        public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return this.value;
        }
    }
}
