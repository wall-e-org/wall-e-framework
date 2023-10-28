package tech.huning.wall.e.util;

import tech.huning.wall.e.specs.constant.CommonConstant;
import tech.huning.wall.e.specs.exception.SystemException;
import tech.huning.wall.e.specs.model.ResultCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class DateUtil {

	/**
	 * 日期格式
	 */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public static final String YY_MM_DD_HH_MM = "yy/MM/dd HH:mm";
	public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String YYYY = "yyyy";

	/**
	 * 字符串转为日期类型
	 * @param date 日期字符串
	 * @param format 格式
	 * @return 日期
	 * @throws SystemException 系统异常
	 */
	public static Date parse(String date, String format) throws SystemException {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(date);
		} catch (ParseException e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
		
	}

	/**
	 * 日期转为字符串类型
	 * @param date 日期
	 * @param format 格式
	 * @return 日期字符串
	 * @throws SystemException 系统异常
	 */
	public static String format(Date date, String format) throws SystemException {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
		
	}

	/**
	 * 日期转为字符串类型(静默转化)
	 * @param date 日期
	 * @param format 格式
	 * @return 日期字符串
	 */
	public static String formatInSilence(Date date, String format) {
		try {
			return format(date, format);
		} catch (SystemException e) {

		}
		return CommonConstant.EMPTY_STRING;
	}

	/**
	 * 毫秒转为字符串类型
	 * @param milliseconds 毫秒
	 * @param format 格式
	 * @return 日期字符串
	 * @throws SystemException 系统异常
	 */
	public static String format(long milliseconds, String format) throws SystemException {

		try {
			return format(new Date(milliseconds), format);
		} catch (Exception e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}

	}

	/**
	 * 毫秒转为字符串类型(静默转化)
	 * @param milliseconds 毫秒
	 * @param format 格式
	 * @return 日期
	 */
	public static String formatInSilence(long milliseconds, String format)  {

		try {
			return format(new Date(milliseconds), format);
		} catch (Exception e) {

		}
		return CommonConstant.EMPTY_STRING;

	}

	/**
	 *  日期增加小时
	 * @param date 日期
	 * @param hour 小时
	 * @return 日期
	 * @throws SystemException 系统异常
	 */
	public static Date addHour(Date date, int hour) throws SystemException {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR_OF_DAY, hour);
			return calendar.getTime();
		} catch (Exception e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
	}

	/**
	 * 日期增加天数
	 * @param date 日期
	 * @param day 天数
	 * @return 日期
	 * @throws SystemException 系统异常
	 */
	public static Date addDay(Date date, int day) throws SystemException {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return calendar.getTime();
		} catch (Exception e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
	}

	/**
	 * 日期增加毫秒数
	 * @param date 日期
	 * @param millisecond 毫秒
	 * @return 日期
	 * @throws SystemException 系统异常
	 */
	public static Date addMillisecond(Date date, int millisecond) throws SystemException {
		try {
			long dateMillisecond = date.getTime() + millisecond;
			return new Date(dateMillisecond);
		} catch (Exception e) {
			throw new SystemException(e, ResultCode.FAILURE);
		}
	}

}
