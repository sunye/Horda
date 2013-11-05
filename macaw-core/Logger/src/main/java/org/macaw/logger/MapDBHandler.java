/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.logger;

import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import org.mapdb.DB;

/**
 *
 * @author sunye
 */
public class MapDBHandler extends Handler {
    
    private final DB database;
    private final Map<Long, LogRecord> map;
    
    protected MapDBHandler (DB db) {
        database = db;
        map = db.getTreeMap("macaw:logger");
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
