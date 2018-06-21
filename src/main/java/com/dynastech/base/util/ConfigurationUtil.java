package com.dynastech.base.util;

import java.util.ResourceBundle;

/**
 * 获取配置文件的工具类
 *
 */
public class ConfigurationUtil {
	static ResourceBundle conf = ResourceBundle.getBundle("config/base");
	public static String getResourcesValue(String key) {
		return conf.getString(key);
	}
}
