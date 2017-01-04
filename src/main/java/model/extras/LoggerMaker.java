/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.extras;

public class LoggerMaker {

    private org.apache.log4j.Logger logger;

    private static LoggerMaker instance;

    private LoggerMaker() {

    }

    /**
     *
     * @return
     */
    public static LoggerMaker getInstance() {
        if (instance == null) {
            synchronized (LoggerMaker.class) {
                if (instance == null) {
                    instance = new LoggerMaker();
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
