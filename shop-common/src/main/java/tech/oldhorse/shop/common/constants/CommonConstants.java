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
     * Token有效时间，秒
     */
    public static final Long TOKEN_TIMEOUT = 60 * 60 * 24L;
    /**
     * 请求头跟踪id名。
     */
    public static final String HTTP_REQUEST_ID = "requestId";
    /**
     * 重要说明：该值为项目生成后的缺省密钥，仅为使用户可以快速上手并跑通流程。
     * 在实际的应用中，一定要为不同的项目或服务，自行生成公钥和私钥，并将 PRIVATE_KEY 的引用改为服务的配置项。
     * 密钥的生成方式，可通过执行tech.oldhorse.shop.common.utils.RsaUtil类的main函数动态生成。
     */
    public static final String PRIVATE_KEY =
            "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItrv6UAzRuh6eK1IatEScSz8/byyHHCd38Pw6bjYTpM3oUNnhvMOJ/ECKhKJXu1/pH4CxGYCmcUqU2RCMErTu0kTLm66JoN5zXSfQ5C+s4NvTnFxV0ldnhpTjXmcSNfczoWZBttMaY20IvPhqqejhi8y+PNLhmndWFtubVgJts5AgMBAAECgYAA7MRO8SgrKoINb7OrOe7+V85CwV4MiSR3SpC68u+ybg+c1NgLaamnf6qqCSHiBfVMHRfftEE/aU7tNYzYETQ7Ems57rArjz+YP2ElR0LhoCyVj88QISKEWHD6pfIA7Q5srLFk+xZjniBRaRyRrWfePV1//H4jXVk2NKiTdKR0zQJBAO5g4Jtihn6kTBDUmMjSqP0JblOn1Oc4n3OpaTgS9CBz2kqvMqhPytvDC75NCzUr6CwhWTydxQJWfZvluvor/fUCQQCVui2sMnY9LIoVoZggaUdVLddJHgKbMVidtwA/3saLMZWbFh0KJeUQA1Ity8fDJ875Vt2Ylk2WNRY+Am36Rfm1AkBs87Z1DskuJOXnY2QZlb9LN/5HGhSDQiwO70V6LZ+LHaiOLhlXOHEdlL5PUmNnABRz5yIkRHABK4rpsEyPf3/pAkB/ejIkqBoPX3p5jEmvXrNDDHJ9aDWj2FGwhfyKZKv6Gs9baWp0F2t9RLaVEykTOTN5CsJEhcOtHanAReVI7ob5AkAZiM0ZTsKSOvFd41sVrdUKaylqubRBdd+RiipRtb8Whvx25GBQAlHTMgsYYVMLavo94l7OzkdDpP3KQL8/9fII";
    public static final String PUBLIC_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLa7+lAM0boenitSGrREnEs/P28shxwnd/D8Om42E6TN6FDZ4bzDifxAioSiV7tf6R+AsRmApnFKlNkQjBK07tJEy5uuiaDec10n0OQvrODb05xcVdJXZ4aU415nEjX3M6FmQbbTGmNtCLz4aqno4YvMvjzS4Zp3Vhbbm1YCbbOQIDAQAB";
    public static String EMPTY_STR = "";

}
