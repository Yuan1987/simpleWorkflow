package com.dynastech.base.util;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.dynastech.base.log.SystemLog;
import com.dynastech.system.entity.User;


/**
 * 数据库存储日志 工具类
 * 
 * @author yuan
 *
 */
public class LogUtil {

	/**
	 * @param logger 
	 * @param type 日志业务类型 (logType枚举)
	 * @param msg  日志信息
	 * @param user 操作人
	 */
	public static void addLog(Logger logger, String type,String msg, User user) {
		if (user != null) {
			MDC.put("userid", user.getId());
			MDC.put("username", user.getDisplayname());
		}
		MDC.put("id", UUID.randomUUID().toString());
		MDC.put("type", type);
		MDC.put("msg", msg);
		SystemLog.dbLog(logger, msg);
	}
}
