package com.dynastech.base.log;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

public class SystemLog {
	/**
	 * 继承Level
	 * 
	 * @author yuan
	 * 
	 */
	private static class SystemLogLevel extends Level {
		public SystemLogLevel(int level, String levelStr, int syslogEquivalent) {
			super(level, levelStr, syslogEquivalent);
		}
	}

	/**
	 * 自定义级别名称，以及级别范围
	 */
	private static final Level dbLevel = new SystemLogLevel(20050, "db", SyslogAppender.LOG_LOCAL0);

	/**
	 * 使用日志打印logger中的log方法
	 * 
	 * @param logger
	 * @param objLogInfo
	 */
	public static void dbLog(Logger logger, Object objLogInfo) {
		logger.log(dbLevel, objLogInfo);
	}
}
