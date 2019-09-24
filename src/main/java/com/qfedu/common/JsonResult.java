package com.qfedu.common;

/**
 * 统一的 json类型 controller层方法返回值
 */
public class JsonResult {

    private Integer code;//0 表示成功，1 表示失败
    private Object info;// 具体信息

    public JsonResult() {
    }

    public JsonResult(Integer code, Object info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }
}
