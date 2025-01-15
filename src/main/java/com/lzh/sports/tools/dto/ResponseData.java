package com.lzh.sports.tools.dto;

import com.lzh.sports.SysConst;
import lombok.Data;

@Data
public class ResponseData<T> {
    public String Msg;

    public T Data;

    public String Code;

    public Boolean Success;

    /**
     *得到一个响应的实例
     * @return
     */
    public static ResponseData GetResponseDataInstance(Object data,String msg,Boolean success) {
        ResponseData responseData = new ResponseData();
        responseData.setSuccess(success);
        responseData.setData(data);
        responseData.setMsg(msg);
        responseData.setCode(SysConst.STATUS_200);
        return responseData;
    }
    /**
     * 响应一个没有消息体成功
     * @return
     */
    public static ResponseData OfSuccess() {
        ResponseData responseData = new ResponseData();
        responseData.setSuccess(true);
        responseData.setMsg("成功");
        responseData.setCode(SysConst.STATUS_200);
        return responseData;
    }

    /**
     * 响应一个没有消息体的失败
     * @return
     */
    public static ResponseData OfFailure() {
        ResponseData responseData = new ResponseData();
        responseData.setSuccess(false);
        responseData.setCode(SysConst.STATUS_200);
        return responseData;
    }
}
