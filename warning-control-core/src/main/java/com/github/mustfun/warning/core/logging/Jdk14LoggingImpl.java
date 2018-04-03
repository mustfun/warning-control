
package com.github.mustfun.warning.core.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Jdk14LoggingImpl implements Log {

    private Logger log;

    private int errorCount;
    private int warnCount;
    private int infoCount;
    private int debugCount;

    private String loggerName;

    public Jdk14LoggingImpl(String loggerName) {
        this.loggerName = loggerName;
        log = Logger.getLogger(loggerName);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isLoggable(Level.FINE);
    }

    @Override
    public void error(String s, Throwable e) {
        log.logp(Level.SEVERE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s, e);
        errorCount++;
    }

    @Override
    public void error(String s) {
        log.logp(Level.SEVERE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s);
        errorCount++;
    }
    @Override
    public void debug(String s) {
        debugCount++;
        log.logp(Level.FINE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s);
    }
    @Override
    public void debug(String s, Throwable e) {
        debugCount++;
        log.logp(Level.FINE, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s, e);
    }
    @Override
    public void warn(String s) {
        log.logp(Level.WARNING, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s);
        warnCount++;
    }

    @Override
    public void warn(String s, Throwable e) {
        log.logp(Level.WARNING, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), s, e);
        warnCount++;
    }

    @Override
    public int getWarnCount() {
        return warnCount;
    }

    @Override
    public int getErrorCount() {
        return errorCount;
    }

    @Override
    public void resetStat() {
        errorCount = 0;
        warnCount = 0;
        infoCount = 0;
        debugCount = 0;
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isLoggable(Level.INFO);
    }

    @Override
    public void info(String msg) {
        log.logp(Level.INFO, loggerName, Thread.currentThread().getStackTrace()[1].getMethodName(), msg);
        infoCount++;
    }

    @Override
    public int getInfoCount() {
        return infoCount;
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isLoggable(Level.WARNING);
    }
    @Override
    public int getDebugCount() {
        return debugCount;
    }

}
