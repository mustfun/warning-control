
package com.github.mustfun.warning.core.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JakartaCommonsLoggingImpl implements com.github.mustfun.warning.core.logging.Log {

    private Log log;

    private int errorCount;
    private int warnCount;
    private int infoCount;
    private int debugCount;

    /**
     * @param log
     * @since 0.2.1
     */
    public JakartaCommonsLoggingImpl(Log log) {
        this.log = log;
    }

    public JakartaCommonsLoggingImpl(String loggerName) {
        log = LogFactory.getLog(loggerName);
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(s, e);
        errorCount++;
    }

    @Override
    public void error(String s) {
        log.error(s);
        errorCount++;
    }

    @Override
    public void debug(String s) {
        debugCount++;
        log.debug(s);
    }

    @Override
    public void debug(String s, Throwable e) {
        debugCount++;
        log.debug(s, e);
    }

    @Override
    public void warn(String s) {
        log.warn(s);
        warnCount++;
    }

    @Override
    public void warn(String s, Throwable e) {
        log.warn(s, e);
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
        debugCount++;
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        log.info(msg);
        infoCount++;
    }

    @Override
    public int getInfoCount() {
        return infoCount;
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public int getDebugCount() {
        return debugCount;
    }

}
