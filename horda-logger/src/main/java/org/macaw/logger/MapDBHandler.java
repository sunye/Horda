/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.logger;

import org.mapdb.DB;
import org.mapdb.HTreeMap;
import org.mapdb.Serializer;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * @author sunye
 */
public class MapDBHandler extends Handler {

    private final DB database;
    private final HTreeMap<Long, LogRecord> map;

    protected MapDBHandler(DB db) {
        database = db;
        map = db
                .hashMap("macaw:logger", Serializer.STRING, Serializer.JAVA)
                .createOrOpen();
    }

    @Override
    public void publish(LogRecord record) {
        map.put(record.getSequenceNumber(), record);
    }

    @Override
    public void flush() {
        database.commit();
    }

    @Override
    public void close() throws SecurityException {
        database.close();
    }

}
