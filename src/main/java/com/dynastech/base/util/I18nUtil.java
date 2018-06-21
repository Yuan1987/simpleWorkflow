package com.dynastech.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

/**
 * 国际化接口
 * 
 * @author yuan
 *
 */
public class I18nUtil {
	private static Logger logger = Logger.getLogger(I18nUtil.class);
	private static HashMap<String, String> codeMap = new HashMap<String, String>();
	private static Properties pros = null;
	
	public static String getTextValue(String key) {
		try {
			return SpringUtils.getApplicationContext().getMessage(key, null, null);
		} catch (Exception e) {
			logger.warn("", e);
			return "";
		}
	}
	
	public static String getTextValue(String key, Object[] args) {
		try {
			return SpringUtils.getApplicationContext().getMessage(key, args, null);
		} catch (Exception e) {
			logger.warn("", e);
			return "";
		}
	}

	public static String getTextValue(String key, Object[] args, String defaultVal) {
		try {
			return SpringUtils.getApplicationContext().getMessage(key, args, null);
		} catch (Exception e) {
			logger.warn("", e);
			return defaultVal;
		}
	}

	/**
	 * 使用Spring加载资源文件,返回所有的内容
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 * @throws Exception
	 **/
	public static Properties getResourceForSpring() {
		if (pros != null && !pros.isEmpty()) {
			return pros;
		}
		File cfgFile = null;
		try {
			cfgFile = ResourceUtils.getFile("classpath:i18n/messages_zh_CN.properties");
			FileInputStream fis = null;
			try {
				pros = new Properties();
				fis = new FileInputStream(cfgFile);
				pros.load(fis);
			} catch (Exception e) {
				logger.debug(" loading rest/message FileNotFound:" + e);
			} finally {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			logger.debug(" loading rest/message error:", e);
			return new Properties();
		}
		return pros;
	}
}
