package com.qibao.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * WEB服务工具类
 * 
 * @author ztjie
 * 
 */
public class DateUtil {

	/**
	 * 后去日期凌晨 @return Date @author davidcun @date 2015年4月16日 @throws
	 */
	public static Date getDateStart(Date date) {
		Date dt = DateUtils.setHours(date, 0);
		dt = DateUtils.setMinutes(dt, 0);
		dt = DateUtils.setSeconds(dt, 0);
		dt = DateUtils.setMilliseconds(dt, 0);
		return dt;
	}

	/**
	 * 判断时间是否在某个之间之前
	 * 
	 * @author davidcun
	 */
	public static boolean isBefore(Date date, int h, int m, int s) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int ch = cal.get(Calendar.HOUR_OF_DAY);
		int cm = cal.get(Calendar.MINUTE);
		int cs = cal.get(Calendar.SECOND);
		if (ch > h) {
			return false;
		} else if (cm > m) {
			return false;
		} else if (cs > s) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 得到两个日期之间的天数只差
	 * 
	 * @author davidcun
	 */
	public static Integer daysBetween(Date smallDate, Date bigDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (smallDate == null || bigDate == null) {
				return -1;
			}
			smallDate = sdf.parse(sdf.format(smallDate));
			bigDate = sdf.parse(sdf.format(bigDate));
		} catch (ParseException e) {
			e.printStackTrace();
			// 出错返回-1,
			return -1;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(smallDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bigDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static Date afterDays(Date now, Integer days) {
		long d = days;
		d = d * 24 * 3600 * 1000;
		return new Date(now.getTime() + d);
	}

	/**
	 * 
	 * <p>
	 * 得到传入时间所在的月的最后一天
	 * </p>
	 * 
	 * @author ztjie
	 * @date 2013-12-5 下午10:41:16
	 * @param date
	 * @return
	 * @see
	 */
	public static Date monthLastTime(Date date, int beforeMonth) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + beforeMonth);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 
	 * <p>
	 * 得到传入时间所在月的第一天
	 * </p>
	 * 
	 * @author ztjie
	 * @date 2013-12-5 下午9:40:15
	 * @param date
	 * @return
	 * @see
	 */
	public static Date monthFirstTime(Date date, int beforeMonth) {
		Calendar cal = Calendar.getInstance();// 获取当前日期
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + beforeMonth, cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
	 * <p>
	 * 得到传入时间后几天的开始时间
	 * </p>
	 * 
	 * @author ztjie
	 * @date 2013-12-5 下午9:03:54
	 * @return
	 * @see
	 */
	public static Date beforeDateStartTime(Date date, int before) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + before, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
	 * <p>
	 * 得到传入时间后几天的结束时间
	 * </p>
	 * 
	 * @author ztjie
	 * @date 2013-12-5 下午9:04:18
	 * @return
	 * @see
	 */
	public static Date beforeDateLastTime(Date date, int before) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + before, 23, 59, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}

	/**
	 * 调整时间为得到传入时间所在的天最后一秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date oneDateLastTime(Date date) {
		if (date == null) {
			return date;
		}
		return DateUtil.beforeDateLastTime(date, 0);
	}

	/**
	 * 
	 * <p>
	 * 得到几年前的当前时间
	 * </p>
	 * 
	 * @author Think
	 * @date 2013-12-7 上午10:16:13
	 * @param date
	 * @param before
	 * @return
	 * @see
	 */
	public static Date beforeYearTime(Date date, int before) {
		Calendar cal = Calendar.getInstance();// 得到一个Calendar的实例
		cal.setTime(date); // 设置时间为当前时间
		cal.add(Calendar.YEAR, before); // 年份减1
		return cal.getTime();
	}

	/**
	 * @author yindezhou
	 * @date 2014-9-23 下午4:59:35
	 * @param 必须是
	 *            23:00:00格式的时间或者 23:00格式
	 */
	public static Time getTimeFmt(String time) {
		if (StringUtils.isBlank(time)) {
			return null;
		}
		String[] t = time.split(":");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t[0]));
		cal.set(Calendar.MINUTE, Integer.parseInt(t[1]));
		if (t.length >= 3) {
			cal.set(Calendar.SECOND, Integer.parseInt(t[2]));
		} else {
			cal.set(Calendar.SECOND, 0);
		}
		return new Time(cal.getTimeInMillis());
	}

	public static String getFormatDate(Date date) {
		String str = "";
		if (date == null) {
			return str;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		return sf.format(date);
	}

	public static String getFormatDate(Date date, String format) {
		String str = "";
		if (date == null) {
			return str;
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	public static Date addDate(Date beginDate, int limit) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) + limit);
		try {
			return dft.parse(dft.format(date.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date StringToDate(String dateString){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formatSecond(Object second){
		String  html="0秒";
		if(second!=null){
			Double s=(Double) second;
			String format;
			Object[] array;
			Integer hours =(int) (s/(60*60));
			Integer minutes = (int) (s/60-hours*60);
			Integer seconds = (int) (s-minutes*60-hours*60*60);
			if(hours>0){
				format="%1$,d时%2$,d分%3$,d秒";
				array=new Object[]{hours,minutes,seconds};
			}else if(minutes>0){
				format="%1$,d分%2$,d秒";
				array=new Object[]{minutes,seconds};
			}else{
				format="%1$,d秒";
				array=new Object[]{seconds};
			}
			html= String.format(format, array);
		}
		return html;
	}

/*******************************************************************************/
	/**
	 *  lipeng  2017/07/27
	 *  StringToDate 按照
	 */
	public static Date getFormatHSMDate(String dateString){
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getFormatDateString(Date date) {
		String str = "";
		if (date == null) {
			return str;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		return sf.format(date);
	}

}
