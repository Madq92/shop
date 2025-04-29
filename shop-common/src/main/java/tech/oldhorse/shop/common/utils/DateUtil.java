package tech.oldhorse.shop.common.utils;


/**
 * 日期工具类，主要封装了部分joda-time中的方法，让很多代码一行完成，同时统一了日期到字符串的pattern格式。

 */
public class DateUtil {

    /**
     * 统一的日期pattern，今后可以根据自己的需求去修改。
     */
    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 统一的日期时间pattern，今后可以根据自己的需求去修改。
     */
    public static final String COMMON_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 统一的短日期时间pattern，今后可以根据自己的需求去修改。
     */
    public static final String COMMON_SHORT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 私有构造函数，明确标识该常量类的作用。
     */
    private DateUtil() {
    }
}
