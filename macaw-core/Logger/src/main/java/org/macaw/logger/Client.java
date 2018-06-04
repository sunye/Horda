/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.logger;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.File;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 *
 * @author sunye
 */
public class Client {

    private DB database;

    public void start() {
        database = DBMaker.fileDB(new File("testdb"))
                .closeOnJvmShutdown()
                .make();

        LogManager.getLogManager().reset();
        /*
        Logger globalLogger = Logger.getLogger("global");
        Handler[] handlers = globalLogger.getHandlers();
        for (Handler handler : handlers) {
            globalLogger.removeHandler(handler);
        }*/
    }

    public void stop() {
        database.close();
    }

    public static void main(String[] args) {
        Client cli = new Client();
        cli.start();
        Logger log = Logger.getLogger("org.macaw");
        log.info("Hello my Logger");
    }
}
