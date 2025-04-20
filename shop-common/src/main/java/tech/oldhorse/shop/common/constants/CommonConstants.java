package tech.oldhorse.shop.common.constants;

/**
 * 通用静态变量
 */
public class CommonConstants {
    public final static String COLON = ":";
    public final static String SEMICOLON = ";";
    public final static String COMMA = ",";
    public final static String SEP_UNDERLINE = "_";
    public static final String SEQ_VERTICAL = "|";
    public static String EMPTY_STR = "";
    /**
     * 错误描述
     */
    public final static String ERROR_MSG = "errorMsg";
    /**
     * 分批查询大小
     */
    public static final int BATCH_IN_QUERY_SIZE = 50;
    /**
     * 批量查询线程数
     */
    public static final int BATCH_QUERY_THREAD_SIZE = 3;
    public static final int BATCH_INSERT_SIZE = 200;
    public static final int BATCH_UPDATE_SIZE = 200;
    public static final int BATCH_DELETE_SIZE = 200;
    public static final int BATCH_QUERY_SIZE = 100;

    public static final String SESSION_EMPLOYEE_KEY = "CURRENT_EMPLOYEE";
    public static final String SESSION_WX_USER_KEY = "CURRENT_WX_USER";
    /**
     * 请求头跟踪id名。
     */
    public static final String HTTP_REQUEST_ID = "requestId";
}
