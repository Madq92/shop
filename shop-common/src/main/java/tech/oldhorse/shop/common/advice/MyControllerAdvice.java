package tech.oldhorse.shop.common.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import tech.oldhorse.shop.common.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller的环绕拦截类。
 *
 * @author Jerry
 * @date 2024-07-02
 */
@ControllerAdvice
public class MyControllerAdvice {

    /**
     * 转换前端传入的日期变量参数为指定格式。
     *
     * @param binder 数据绑定参数。
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat(DateUtil.COMMON_SHORT_DATETIME_FORMAT), false));
    }
}
