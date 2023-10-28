package tech.huning.wall.e.util.math;

import tech.huning.wall.e.specs.exception.CheckException;
import tech.huning.wall.e.util.constant.UtilResultCode;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal工具类,用于精确运算
 *
 * <p>更多内容参看<a href="https://huning.tech" target="_blank"><b>胡宁Tech</b></a>
 * @author huning
 */
public class BigDecimalUtil {

    /**
     * 默认运算精度
     */
    private static final int DEFAULT_SCALE = 10;

    private BigDecimalUtil() {
    }

    /**
     * 精确加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {
        return add(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    /**
     * 精确加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    /**
     * 加法运算
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 保留小数位数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     *减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double subtract(double v1, double v2) {
        return subtract(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    /**
     * 减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static BigDecimal subtract(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    /**
     * 减法运算
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 保留小数位数
     * @return 两个参数的差
     */
    public static String subtract(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double multiply(double v1, double v2) {
        return multiply(Double.toString(v1), Double.toString(v2)).doubleValue();
    }

    /**
     * 乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal multiply(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /**
     * 乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留小数位数
     * @return 两个参数的积
     */
    public static double multiply(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2).doubleValue(), scale);
    }

    /**
     * 乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留小数位数
     * @return 两个参数的积
     */
    public static String multiply(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double divide(double v1, double v2) {
        return divide(v1, v2, DEFAULT_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 保留小数位数
     * @return 两个参数的商
     */
    public static double divide(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 保留小数位数
     * @return 两个参数的商
     */
    public static String divide(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v1);
        return b1.divide(b2, scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 取余数
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static String remainder(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2).setScale(scale, RoundingMode.HALF_UP).toString();
    }

    /**
     * 取余数  BigDecimal
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static BigDecimal remainder(BigDecimal v1, BigDecimal v2, int scale) {
        if (scale < 0) {
            throw new CheckException(UtilResultCode.SCALE_MUST_BE_POSITIVE);
        }
        return v1.remainder(v2).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 取最大值
     *
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 返回两个数中大的一个值
     */
    public static double max(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.max(b2).doubleValue();
    }

    /**
     * 取最小值
     *
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 返回两个数中小的一个值
     */
    public static double min(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.min(b2).doubleValue();
    }

    /**
     * 精确对比两个数字
     *
     * @param v1 需要被对比的第一个数
     * @param v2 需要被对比的第二个数
     * @return 如果两个数一样则返回0，如果第一个数比第二个数大则返回1，反之返回-1
     */
    public static int compareTo(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.compareTo(b2);
    }

}

