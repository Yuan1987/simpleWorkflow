package com.dynastech.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author yuan
 */
public class DateUtil {

	/**
	 * 日期格式如:20150625161505
	 */
	public static final String YYYYMMDDHH24MISS = "yyyyMMddhhmmss";
	/**
	 * 日期格式如:2015-06-25 16:15:05
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式如:2015-06-25 16:15
	 */
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	/**
	 * 日期格式：年月日
	 */
	public static final String YYYYMMDD = "yyyyMMdd";
	/**
	 * 日期格式：年-月-日
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String YYYY_MM_DDTHH_MM = "yyyy-MM-dd'T'HH:mm";

	private static int weeks = 0;

	public static void main(String[] args) {
		System.out.println("获取当天日期:" + DateUtil.getNowStr("yyyy-MM-dd"));
		System.out.println("获取本周一日期:" + DateUtil.getMondayOFWeek(new Date()));
		System.out.println("获取本周日的日期~:" + DateUtil.getSunDayOfWeek(new Date()));
		System.out.println("获取本月第一天日期:" + DateUtil.getFirstDayOfMonth(new Date()));
		System.out.println("获取本月最后一天日期:" + DateUtil.getLastDayOfMonth(new Date()));
		System.out.println("获取上月第一天日期:" + DateUtil.getPreviousMonthFirst());
		System.out.println("获取上月最后一天的日期:" + DateUtil.getPreviousMonthEnd());
		System.out.println("获取今天是一周的第几天：" + DateUtil.getDayNoOfWeek(null));
		System.out.println("获取今天是本月的第几天：" + DateUtil.getDayNoOfMonth());
		System.out.println(DateUtil.isValidTime("2016-12-03 10:50:20 232323"));
		System.out.println("getMinByCurrent1：" + getMinByCurrent(30));
		System.out.println("getHourByCurrent2：" + getHourByCurrent(1));
		System.out.println("获取当前时间的后N天时间：" + DateUtil.getAfterDate(new Date(), 3, "yyyy-MM-dd HH:mm:ss"));
	 
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 获取当年的第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 * 
	 * @param year
	 *            年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 某天是一周的第几天
	 * 
	 * @return
	 */
	public static int getDayNoOfWeek(Date date) {

		if (date == null) {
			date = new Date();
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int whichDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (whichDay == 0){
			whichDay = 7;
		}
		return whichDay;
	}

	/**
	 * 本月今年的几个月
	 * 
	 * @return
	 */
	public static int getMontNoOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int whichDay = cal.get(Calendar.MONTH) + 1;
		return whichDay;
	}

	public static int getDayNoOfMonth() {
		Calendar cal = Calendar.getInstance();
		int i = cal.get(Calendar.DAY_OF_MONTH);
		return i;
	}

	/**
	 * 本月最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {

		if (date == null) {
			date = new Date();
		}

		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(5, 1);
		lastDate.add(2, 1);
		lastDate.add(5, -1);

		return lastDate.getTime();
	}

	/**
	 * 本月最后一天
	 * 
	 * @return
	 */
	public static String getLastDayOfMonthStr(Date date) {

		if (date == null) {
			date = new Date();
		}

		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(5, 1);
		lastDate.add(2, 1);
		lastDate.add(5, -1);

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		return sdf.format(lastDate.getTime());
	}

	/**
	 * 上月第一天
	 * 
	 * @return
	 */
	public static Date getPreviousMonthFirst() {

		Calendar lastDate = Calendar.getInstance();

		lastDate.set(5, 1);

		lastDate.add(2, -1);

		return lastDate.getTime();
	}

	/**
	 * 本月的第一天
	 * 
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {

		if (date == null) {
			date = new Date();
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(5, 1);

		return c.getTime();
	}

	/**
	 * 本月的第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonthStr(Date date) {

		if (date == null) {
			date = new Date();
		}

		Calendar c = Calendar.getInstance();

		c.setTime(date);

		c.set(5, 1);

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		return sdf.format(c.getTime());
	}

	public static Date getSunDayOfWeek(Date date) {
		DateUtil.weeks = 0;

		int mondayPlus = getMondayPlus(date);

		GregorianCalendar currentDate = new GregorianCalendar();

		currentDate.setTime(date);

		currentDate.add(5, mondayPlus + 6);

		Date sunday = currentDate.getTime();

		return sunday;
	}

	/**
	 * 获取今天
	 * 
	 * @param dateformat
	 * @return
	 */
	public static Date getNow() {
		Date now = new Date();
		return now;
	}

	/**
	 * 获取今天 yyyy-mm-dd
	 * 
	 * @return
	 */
	public static String getNowStr(String p) {
		Date now = new Date();
		SimpleDateFormat myFormatter = new SimpleDateFormat(p);
		return myFormatter.format(now);
	}

	/**
	 * 获取某天的后N天的日期
	 */

	public static String getAfterDate(Date date, int dayNum, String pattern) {

		Calendar c = Calendar.getInstance();

		if (date == null) {
			date = new Date();
		}
		c.setTime(date);

		SimpleDateFormat sdfd = new SimpleDateFormat(pattern);

		c.add(Calendar.DATE, +dayNum);

		return sdfd.format(c.getTime());
	}

	/**
	 * 获取某天的前N天的日期
	 */

	public static String getBeforeDate(Date date, int dayNum, String pattern) {

		Calendar c = Calendar.getInstance();

		if (date == null) {
			date = new Date();
		}
		c.setTime(date);

		SimpleDateFormat sdfd = new SimpleDateFormat(pattern);

		c.add(Calendar.DATE, -dayNum);

		return sdfd.format(c.getTime());
	}

	/**
	 * 获取某天的前N天的日期
	 */
	public static Date getBeforeDate(Date date, int dayNum) {

		Calendar c = Calendar.getInstance();

		if (date == null) {
			date = new Date();
		}
		c.setTime(date);

		c.add(Calendar.DATE, -dayNum);

		return c.getTime();
	}

	private static int getMondayPlus(Date date) {
		Calendar cd = Calendar.getInstance();

		cd.setTime(date);

		int dayOfWeek = cd.get(7) - 1;

		if (dayOfWeek == 1) {
			return 0;
		}
		return (1 - dayOfWeek);
	}

	/**
	 * 获取当周第一天（星期一 ） 中国习惯
	 * 
	 * @return
	 */
	public static Date getMondayOFWeek(Date date) {
		DateUtil.weeks = 0;

		int mondayPlus = getMondayPlus(date);

		GregorianCalendar currentDate = new GregorianCalendar();

		currentDate.setTime(date);

		currentDate.add(5, mondayPlus);

		Date monday = currentDate.getTime();

		return monday;
	}

	/**
	 * 上月最后一天
	 * 
	 * @return
	 */
	public static Date getPreviousMonthEnd() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(2, -1);
		lastDate.set(5, 1);
		lastDate.roll(5, -1);
		return lastDate.getTime();
	}

	/**
	 * 判断是否满足yyyy-MM-dd HH:mm:ss这种格式
	 * 
	 * @param dayTime
	 * @return
	 */
	public static boolean isValidTime(String dayTime) {
		if (StringUtils.isBlank(dayTime)) {
			return false;
		}
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
				+ "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(dayTime);
		return matcher.matches();
	}

	/**
	 * 判断是否满足yyyy-MM-dd这种格式
	 * 
	 * @param dayTime
	 * @return
	 */

	public static boolean isValidDay(String dayTime) {
		if (StringUtils.isBlank(dayTime)) {
			return false;
		}
		String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(dayTime);
		return matcher.matches();
	}

	/**
	 * 返回系统时间 linzm 2016-04-23
	 * 
	 * @throws Exception
	 * @throws Exception
	 **/
	public static String getServerTime(String timeType) {
		String time = "";
		SimpleDateFormat sdf = new SimpleDateFormat(timeType);

		Date now = new Date();
		time = sdf.format(now);

		return time;
	}

	/**
	 * 获取当前日期年月，返回字符串格式为 yyyy_mm 如:2016_07 2016年7月23日 上午6:36:00
	 * 
	 * @return
	 */
	public static String getSystemYear_Month() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
		String yearMonth = sdf.format(new Date());
		return yearMonth;
	}

	/**
	 * 将date转换成String
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String dateToStr(Date date) {
		return dateToStr(date, null);
	}

	public static Date parseStrToDate(String dateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formatDateToStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 将date转换成String
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String dateToStr(Date date, String aMask) {
		String ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask)){
			mask = YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		ret = sdf.format(date);
		return ret;
	}

	public static String dateToUTCStr(Date date, String aMask) {
		String ret = null;
		String mask = aMask;
		if (mask == null || "".equals(mask)){
			mask = YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(mask);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		ret = sdf.format(date);
		return ret;
	}

	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0){
		    w = 0;
		}
		return weekDays[w];
	}

	/**
	 * 获取当前时间之前或之后的小时时间
	 * @param hour：为正数时表示之后，为负数表示之前
	 * @return
	 */
	public static String getHourByCurrent(int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, hour);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

	}
	
	/**
	 * 获取当前时间之前或之后的小时时间(传入值 为分钟)
	 * @param hour：为正数时表示之后，为负数表示之前
	 * @return
	 */
	public static String getMinByCurrent(int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, min);
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
	}
	
	
	/**
	 * 日期增加
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date plus(Date date,int type,int num){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(type, num);
		return c.getTime();
	}
	
	/**
	 * 计算两个日期之间相差的分钟数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @throws ParseException
	 */
	public static int minutesBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between = (time2 - time1) / (1000 * 60);
		return Integer.parseInt(String.valueOf(between));
	}

}
