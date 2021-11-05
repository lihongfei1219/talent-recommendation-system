package com.lff.talent_recommendation_system.exception;


import java.lang.annotation.*;

/**
 * @Author: 李飞飞
 * @Description:统一处理异常
 * @Date: Created in 8:59 2021/10/24
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HandleException {
    /**
     * 异常捕获后提示语
     * @return
     */
    String value() default "操作错误";

}



