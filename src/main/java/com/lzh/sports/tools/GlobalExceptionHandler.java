package com.lzh.sports.tools;

import com.lzh.sports.SysConst;
import com.lzh.sports.tools.dto.ResponseData;
import com.lzh.sports.tools.exception.CustomException;
import org.springframework.web.bind.annotation.*;
/**
 * 全局异常处理切面
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    @ResponseBody
    public ResponseData bizExceptionHandler(CustomException e) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(SysConst.STATUS_500);
        responseData.setMsg(e.getErrorMsg());
        responseData.setSuccess(false);
        return responseData;
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData exceptionHandler(Exception e) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(SysConst.STATUS_500);
        e.printStackTrace();

        System.out.println(e.getMessage());
        responseData.setMsg("系统异常,请联系管理员");
        responseData.setSuccess(false);
        return responseData;
    }
}
