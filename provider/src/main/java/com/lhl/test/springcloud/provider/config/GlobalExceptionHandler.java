package com.lhl.test.springcloud.provider.config;

import com.lhl.test.springcloud.provider.exceptions.BaseBizException;
import com.lhl.test.springcloud.service.common.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常（兜底）
     * @param e
     * @return
     */
    @ExceptionHandler
    public CommonResponse handlerException(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new CommonResponse("500", e.getMessage());
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = {BaseBizException.class})
    public CommonResponse handlerBizException(BaseBizException e) {
        LOGGER.error(e.getMessage(), e);
        return new CommonResponse("500", e.getMessage());
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error(e.getMessage(), e);

        return new CommonResponse("403", e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

}
