
package com.github.mustfun.warning.core.logging;

public class NoLoggingImpl implements Log {

    private int    infoCount;
    private int    errorCount;
    private int    warnCount;
    private int    debugCount;
    private String loggerName;

    public NoLoggingImpl(String loggerName){
        this.loggerName = loggerName;
    }

    public String getLoggerName() {
        return this.loggerName;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public void error(String s, Throwable e) {
        error(s);

        if (e != null) {
            e.printStackTrace();
        }
    }

    public void error(String s) {
        errorCount++;
        if (s != null) {
            System.err.println(loggerName + " : " + s);
        }
    }

    public void debug(String s) {
        debugCount++;
    }

    public void debug(String s, Throwable e) {
        debugCount++;
    }

    public void warn(String s) {
        warnCount++;
    }

    @Override
    public void warn(String s, Throwable e) {
        warnCount++;
    }

    public int getErrorCount() {
        return errorCount;
    }

    @Override
    public int getWarnCount() {
        return warnCount;
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
        return false;
    }

    @Override
    public void info(String s) {
        infoCount++;
    }

    @Override
    public boolean isWarnEnabled() {
        return false;
    }

    public int getInfoCount() {
        return infoCount;
    }

    public int getDebugCount() {
        return debugCount;
    }

}
