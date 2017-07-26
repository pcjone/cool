package com.cool.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期操作辅助类
 * 
 * @author ShenHuaJie
 * @version $Id: DateUtil.java, v 0.1 2014年3月28日 上午8:58:11 ShenHuaJie Exp $
 */
public final class DateUtil {
	private DateUtil() {
	}

	public static java.text.SimpleDateFormat sdfLong = new java.text.SimpleDateFormat("yyyy-MM-dd");
	
	public static java.text.SimpleDateFormat sdfShort = new java.text.SimpleDateFormat("yyyyMMdd");
	
	public static  SimpleDateFormat sdfLongTiem = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static String PATTERN = "yyyy-MM-dd";

	static java.text.SimpleDateFormat sdfLongTime = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmss");

	static java.text.SimpleDateFormat sdfLongTimePlusMill = new java.text.SimpleDateFormat(
			"yyyyMMddHHmmssSSSS");

	public static final String PATTERN_DATE = "yyyy-MM-dd";
	
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
	
	private static long DAY_IN_MILLISECOND = 0x5265c00L;

	public static String getOrderNo() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date) + DataUtil.rand(100);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date) {
		return format(date, PATTERN);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static final String format(Object date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null) {
			return format(date);
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 获取日期
	 * 
	 * @return
	 */
	public static final String getDate() {
		return format(new Date());
	}

	/**
	 * 获取日期时间
	 * 
	 * @return
	 */
	public static final String getDateTime() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取日期
	 * 
	 * @param pattern
	 * @return
	 */
	public static final String getDateTime(String pattern) {
		return format(new Date(), pattern);
	}

	/**
	 * 日期计算
	 * 
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static final Date addDate(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 字符串转换为日期:不支持yyM[M]d[d]格式
	 * 
	 * @param date
	 * @return
	 */
	public static final Date stringToDate(String date) {
		if (date == null) {
			return null;
		}
		String separator = String.valueOf(date.charAt(4));
		String pattern = "yyyyMMdd";
		if (!separator.matches("\\d*")) {
			pattern = "yyyy" + separator + "MM" + separator + "dd";
			if (date.length() < 10) {
				pattern = "yyyy" + separator + "M" + separator + "d";
			}
		} else if (date.length() < 8) {
			pattern = "yyyyMd";
		}
		pattern += " HH:mm:ss.SSS";
		pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 间隔天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getDayBetween(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.HOUR_OF_DAY, 0);
		end.set(Calendar.MINUTE, 0);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);

		long n = end.getTimeInMillis() - start.getTimeInMillis();
		return (int) (n / (60 * 60 * 24 * 1000l));
	}

	/**
	 * 间隔月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetween(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		return n;
	}

	/**
	 * 间隔月，多一天就多算一个月
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null || !startDate.before(endDate)) {
			return null;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		int year1 = start.get(Calendar.YEAR);
		int year2 = end.get(Calendar.YEAR);
		int month1 = start.get(Calendar.MONTH);
		int month2 = end.get(Calendar.MONTH);
		int n = (year2 - year1) * 12;
		n = n + month2 - month1;
		int day1 = start.get(Calendar.DAY_OF_MONTH);
		int day2 = end.get(Calendar.DAY_OF_MONTH);
		if (day1 <= day2) {
			n++;
		}
		return n;
	}

	/**
	 * 获取当前日期为日期型
	 *
	 * @return 当前日期，java.util.Date类型
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		return d;
	}

	/**
	 * 获取统计时间点，如刚好是整点则在原时间点-1小时
	 * <p>如统计时间为7点01分，则时间点为8点</p>
	 * @param endTime 结束时间
	 * @return
	 */
	public static int getTimeBreak(Date endTime) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		if (cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.SECOND) == 0) {
			cal.add(Calendar.HOUR, -1);
		}
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到当前年份值:1900
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowYear() throws Exception {
		String nowYear = "";
		try {
			String strTemp = getNowLongTime();
			nowYear = strTemp.substring(0, 4);
			return nowYear;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 得到当前月份值:12
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowMonth() throws Exception {
		String nowMonth = "";
		try {
			String strTemp = getNowLongTime();
			nowMonth = strTemp.substring(4, 6);
			return nowMonth;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 得到当前日期值:30
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowDay() throws Exception {
		String nowDay = "";
		try {
			String strTemp = getNowLongTime();
			nowDay = strTemp.substring(6, 8);
			return nowDay;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 得到当前小时值:23
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowHour() throws Exception {
		String nowHour = "";
		try {
			String strTemp = getNowPlusTimeMill();
			nowHour = strTemp.substring(8, 10);
			return nowHour;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Descrption:取得当前日期时间,格式为:YYYYMMDDHHMISS
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowLongTime() throws Exception {
		String nowTime = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowTime = sdfLongTime.format(date);
			return nowTime;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Descrption:取得当前日期到毫秒极,格式为:yyyyMMddHHmmssSSSS
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowPlusTimeMill() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlusMill.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取输入日期的零点 如传入时间为yyyy-mm-dd hh:mm:ss 返回为yyyy-mm-dd 00:00:00
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getBegOfDay(Date dt) {
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// System.out.println(dateformat1.format(dt));
		Date date = null;

		try {
			date = dateformat1.parse(dateformat1.format(dt));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	/**
	 * Descrption:取得当前日期,格式为:YYYYMMDD
	 * 
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowShortDate() throws Exception {
		String nowDate = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfShort.format(date);
			return nowDate;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 得到两个日期之间相差的天数+小时+分钟+秒
	 * 
	 * @param arriveTime 小的日期 is not null
	 * @param leaveTime 大的日期 is not null
	 * @return exp.3天5小时40分钟30秒
	 */
	public static String daysHmsBetweenDates(Timestamp arriveTime, Timestamp leaveTime) {
		long diff = leaveTime.getTime() - arriveTime.getTime();
		int days = (int) (diff / (24 * 60 * 60 * 1000));
		diff %= 24 * 60 * 60 * 1000;
		int hours = (int) (diff / (60 * 60 * 1000));
		diff %= 60 * 60 * 1000;
		int minites = (int) (diff / (60 * 1000));
		diff %= 60 * 1000;
		int second = (int) (diff / 1000);
		String duraction = "";
		if (days != 0) {

			duraction += days + "天";
		}
		if (hours != 0) {

			duraction += hours + "小时";
		}
		if (minites != 0) {

			duraction += minites + "分钟";
		}
		if (second != 0) {

			duraction += second + "秒";
		}
		return duraction;
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * 得到两个日期之间相差的天数
	 *
	 * @param newDate
	 *            大的日期
	 * @param oldDate
	 *            小的日期
	 * @return newDate-oldDate相差的天数
	 */
	public static int daysBetweenDates(Date newDate, Date oldDate) {
		int days = 0;
		Calendar calo = Calendar.getInstance();
		Calendar caln = Calendar.getInstance();
		calo.setTime(oldDate);
		caln.setTime(newDate);
		int oday = calo.get(Calendar.DAY_OF_YEAR);
		int nyear = caln.get(Calendar.YEAR);
		int oyear = calo.get(Calendar.YEAR);
		while (nyear > oyear) {
			calo.set(Calendar.MONTH, 11);
			calo.set(Calendar.DATE, 31);
			days = days + calo.get(Calendar.DAY_OF_YEAR);
			oyear = oyear + 1;
			calo.set(Calendar.YEAR, oyear);
		}
		int nday = caln.get(Calendar.DAY_OF_YEAR);
		days = days + nday - oday;

		return days;
	}

	/**
	 * 得到将date增加指定天数后的date
	 *
	 * @param date
	 *            日期
	 * @param intBetween
	 *            增加的天数
	 * @return date 加上intBetween天数后的日期
	 */
	public static Date increaseDay(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}

	/**
	 * Returns a Date set to the last possible millisecond of the day, just
	 * before midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getEndOfDay(Date day) {
		return getEndOfDay(day, Calendar.getInstance());
	}

	public static Date getEndOfDay(Date day, Calendar cal) {
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	/**
	 * Returns a Date set to the first possible millisecond of the day, just
	 * after midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getStartOfDay(Date day) {
		return getStartOfDay(day, Calendar.getInstance());
	}

	/**
	 * Returns a Date set to the first possible millisecond of the day, just
	 * after midnight. If a null day is passed in, a new Date is created.
	 * midnight (00m 00h 00s)
	 */
	public static Date getStartOfDay(Date day, Calendar cal) {
		if (day == null)
			day = new Date();
		cal.setTime(day);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
		cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
		return cal.getTime();
	}

	/**
	 * @author zhangyong
	 * @return DATE 型加具体的天数
	 * 
	 * @param Date
	 *            date, int days
	 */
	public static Date dateAddDays(Date date, int days) {
		long now = date.getTime() + (long) days * DAY_IN_MILLISECOND;
		return new Date(now);
	}

	public static Date dateDayAdd(Date date, int days) {
		long now = date.getTime() + (long) days * DAY_IN_MILLISECOND;
		return new Date(now);
	}

	/**
	 * 获取当月的第一天，例如2016-07-01
	 */
	public static String getStringOfFirstDayInMonth() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String temp = sdf.format(date);
		String firstDayInMoth = "";
		firstDayInMoth = temp + "-01";
		return firstDayInMoth;
	}

	/**
	 * 日期字符串转Date
	 * @param dateStr
	 *            日期字符串
	 * @param aFormat
	 *            日期转换模型
	 */
	public static Date parse(String dateStr, SimpleDateFormat aFormat) throws ParseException {
		if (StringUtils.isEmpty(dateStr) || aFormat == null) {
			return null;
		}
		return aFormat.parse(dateStr);
	}
	
	public static String date2String(java.util.Date date, String pattern) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException("timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 获取输入日期的某一点 如传入时间为yyyy-mm-dd hh:mm:ss 返回为yyyy-mm-dd 00:00:00 parm 格式为
	 * 00:00:00 或 02:00:00
	 * 
	 * @param dt
	 * @return
	 */
	public static Date getTimeOfDay(Date dt, String parm) {
		if (parm == null || parm.length() <= 0) {
			parm = "00:00:00";
		}
		String time = getDateLong(dt) + " " + parm;
		Date date = string2Date(time, PATTERN_STANDARD);
		return date;
	}
	
	/**
	 * 取得与原日期相差一定天数的日期，返回Date型日期
	 *
	 * @param date
	 *            原日期
	 * @param intBetween
	 *            相差的天数
	 * @return date加上intBetween天后的日期
	 */
	public static Date getDateBetween(Date date, int intBetween) {
		Calendar calo = Calendar.getInstance();
		calo.setTime(date);
		calo.add(Calendar.DATE, intBetween);
		return calo.getTime();
	}
	
	/**
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getDateLong(Date date) {
		String nowDate = "";
		try {
			if (date != null)
				nowDate = sdfLong.format(date);
			return nowDate;
		} catch (Exception e) {
			System.out.println("Error at getDate:" + e.getMessage());
			return "";
		}
	}
	
	public static Date string2Date(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}


	public static Date getBegOfYesterday(Date date) {

		date=date==null? DateUtil.dateAddDays(new Date(),-1):date;
		return DateUtil.getBegOfDay(date);
	}

	/**
	 *
	 * 字符串形式转化为Date类型 String类型按照format格式转为Date类型
	 **/
	public static Date fromStringToDate(String format, String dateTime) throws ParseException {
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		date = sdf.parse(dateTime);
		return date;
	}

	/**
	 * 得到当前日期，格式yyyy-MM-dd。
	 *
	 * @return String 格式化的日期字符串
	 */
	public static String getYesterday() {
		Date cDate = new Date();
		cDate.setTime(cDate.getTime() - 24 * 3600 * 1000);
		SimpleDateFormat cSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return cSimpleDateFormat.format(cDate);
	}
}
