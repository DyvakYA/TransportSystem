/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extras;

public class LoggerHelper {

    private org.apache.log4j.Logger logger;

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
    public org.apache.log4j.Logger getLogger() {
        return logger;
    }

    /**
     *
     * @param logger
     */
    public void setLogger(org.apache.log4j.Logger logger) {
        this.logger = logger;
    }

}
