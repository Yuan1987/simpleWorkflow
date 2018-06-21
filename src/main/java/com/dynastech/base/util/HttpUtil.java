package com.dynastech.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 工具类
 * @author yuanhb
 *
 */
public class HttpUtil {
	private static Log log = LogFactory.getLog(HttpUtil.class);

	/**
	 * 发起https请求或者http请求并获取json数组集合
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static JSONArray httpRequestArray(String requestUrl, String requestMethod, String outputStr) {
		String str = null;
		if (StringUtils.isNotBlank(requestUrl)) {
			if (requestUrl.contains("https://")) {
				str = publicHttpRequest(requestUrl, requestMethod, outputStr);
			} else {
				str = httpPublicRequest(requestUrl, requestMethod, outputStr);
			}
		}
		JSONArray jsonArrObject = null;
		if (StringUtils.isNotBlank(str)) {
			jsonArrObject = JSONArray.fromObject(str);
		}
		return jsonArrObject;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestM
	 *            请求方法(get/post)
	 * @param outStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		String str = null;
		if (StringUtils.isNotBlank(requestUrl)) {
			if (requestUrl.contains("https://")) {
				str = publicHttpRequest(requestUrl, requestMethod, outputStr);
			} else {
				str = httpPublicRequest(requestUrl, requestMethod, outputStr);
			}
		}

		JSONObject jsonObject = null;
		if (StringUtils.isNotBlank(str)) {
			jsonObject = JSONObject.fromObject(str);
		}
		return jsonObject;
	}

	/**
	 * http或者https请求返回String
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	public static String httpRequestStr(String requestUrl, String requestMethod, String outputStr) {
		String str = null;
		if (StringUtils.isNotBlank(requestUrl)) {
			if (requestUrl.contains("https://")) {
				str = publicHttpRequest(requestUrl, requestMethod, outputStr);
			} else {
				str = httpPublicRequest(requestUrl, requestMethod, outputStr);
			}
		}
		return str;
	}

	/**
	 * 发起http请求公共部分
	 * 
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	private static String httpPublicRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		String msgStr = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setRequestProperty("content-type", "text/html");
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("utf-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			msgStr = buffer.toString();
		} catch (Exception e) {
			log.info("===httpPublicRequest method===", e);
		}
		return msgStr;
	}

	/**
	 * 提取发起请求公共部分
	 * 
	 * @author jianglei 2016年7月15日 下午2:06:23
	 * @param requestUrl
	 * @param requestMethod
	 * @param outputStr
	 * @return
	 */
	private static String publicHttpRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("TLSv1");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setRequestProperty("content-type", "text/html");
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("utf-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			log.info("===publicHttpRequest method===", e);
		}
		return buffer.toString();
	}

	/**
	 * 微信支付请求对账单，返回相关数据类型
	 * 
	 * @author jianglei 2016年8月8日 上午11:37:33
	 * @param requestUrl
	 *            连接
	 * @param requestMethod
	 *            //请求方法
	 * @param outputStr
	 *            参数
	 * @return 返回数组类型
	 */
	public static List<String> statements(String requestUrl, String requestMethod, String outputStr) {
		List<String> listStr = new ArrayList<String>();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("TLSv1");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setRequestProperty("content-type", "text/html");
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("utf-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				listStr.add(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			log.info("===statements method===", e);
		}
		return listStr;
	}

	/**
	 * 把获取的xml相关数据转换为map形式
	 * 
	 * @author jianglei 2016年7月15日 下午2:12:41
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方法 POST/GET
	 * @param outputStr
	 *            请求参数
	 * @return
	 */
	public static Element httpReqPraseXML(String requestUrl, String requestMethod, String outputStr) {
		String postStr = null;
		Document document = null;
		try {
			postStr = httpRequestStr(requestUrl, requestMethod, outputStr);
			if (StringUtils.isNotBlank(postStr)) {
				document = DocumentHelper.parseText(postStr);
			}
		} catch (Exception e) {
			log.info("===httpReqPraseXML method===", e);
		}
		if (null == document) {
			return null;
		} else {
			Element root = document.getRootElement();
			return root;
		}
	}

	/**
	 * 对象转xml 扩展xstream使其支持CDATA
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				@Override
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 对象转xml
	 * 
	 * @author jianglei 2016年7月16日 上午10:49:03
	 * @param entity
	 * @return
	 */
	public static String entityToXML(Object entity) {
		xstream.alias("xml", entity.getClass());
		return xstream.toXML(entity).replace("__", "_");
	}

	/**
	 * 对象转换为xml
	 * 
	 * @author jianglei 日期 2017年7月7日 上午11:44:15
	 * @param obj
	 *            对象
	 * @param isNull
	 *            空值是否去掉节点
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String objToXml(Object obj, boolean isNull) {
		if (isNull) {
			return xmlNodeisNull(obj);
		}
		return xmlNodeisNotNull(obj);
	}

	/**
	 * 对象转xml,不过滤属性值为空的节点
	 * @param obj
	 * @return
	 */
	private static String xmlNodeisNull(Object obj) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		try {
			Field[] field = obj.getClass().getDeclaredFields();
			Field.setAccessible(field, true);
			for (Field fd : field) {
				sb.append("<").append(fd.getName()).append(">").append("<![CDATA[").append(fd.get(obj)).append("]]>")
						.append("</").append(fd.getName()).append(">");
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 对象转xml,过滤属性值为空的节点
	 * @param obj
	 * @return
	 */
	private static String xmlNodeisNotNull(Object obj) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		try {
			Field[] field = obj.getClass().getDeclaredFields();
			Field.setAccessible(field, true);
			for (Field fd : field) {
				System.out.println(fd.getName() + ":" + StringUtils.isNotBlank(String.valueOf(fd.get(obj))));
				if (null != fd.get(obj) && StringUtils.isNotBlank(String.valueOf(fd.get(obj)))) {
					sb.append("<").append(fd.getName()).append(">").append("<![CDATA[").append(fd.get(obj))
							.append("]]>").append("</").append(fd.getName()).append(">");
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 从输入流读取post参数
	 * @param
	 * @param in
	 *            输入流
	 * @return
	 */
	public static String readStreamParameter(ServletInputStream in) {
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (Exception e) {
			log.info("===readStreamParameter method===", e);
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					log.info("===readStreamParameter reader close fail===", e);
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 把获取的xml相关数据转换为map形式
	 * @param
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @return
	 * @throws IOException
	 */
	public static Element praseXML(HttpServletRequest request, HttpServletResponse response) {
		String postStr = null;
		Document document = null;
		try {
			postStr = readStreamParameter(request.getInputStream());
			if (StringUtils.isNotBlank(postStr)) {
				document = DocumentHelper.parseText(postStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("===praseXML method===", e);
		}
		if (null == document) {
			return null;
		} else {
			Element root = document.getRootElement();
			return root;
		}
	}

	/**
	 * xml直接转map形式
	 * 
	 * @param strXml
	 * @return
	 */
	public static Element strToXml(String xmlStr) {
		Document document = null;
		try {
			if (StringUtils.isNotBlank(xmlStr)) {
				document = DocumentHelper.parseText(xmlStr);
			}
		} catch (Exception e) {
			log.info("===strToXml method===", e);
		}
		if (null == document) {
			return null;
		} else {
			Element root = document.getRootElement();
			return root;
		}
	}
}
