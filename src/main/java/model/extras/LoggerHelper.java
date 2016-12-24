/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extras;

import org.apache.log4j.Logger;

/**
 *
 * @author KIRIL
 */
public class LoggerHelper {

    private Logger logger;

    private static LoggerHelper instance;

    private LoggerHelper() {

    }

    /**
     *
     * @return
     */
    public static LoggerHelper getInstance() {
        if (instance == null) {
            synchronized (LoggerHelper.class) {
                if (instance == null) {
                    instance = new LoggerHelper();
                }
            }
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     *
     * @param logger
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }

}
