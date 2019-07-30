package com.ffs.api;

import com.alibaba.fastjson.JSON;
import lombok.Data;


@Data
public class CommonResult {
    /**
     * 操作成功
     */
    public static final int SUCCESS = 200;
    /**
     * 非业务类异常
     */
    public static final int INTERNAL_SERVER_ERROR = 500;
    /**
     * 未认证
     */
    public static final int UNAUTHORIZED = 401;
    /**
     * 未授权
     */
    public static final int FORBIDDEN = 403;
    /**
     * 无资源
     */
    public static final int NOT_FOUND = 404;
    /**
     * 语义有误，当前请求无法被服务器理解，或者请求参数错误。
     */
    public static final int BAD_REQUEST = 400;
    /**
     * 被请求的资源已永久移动到新位置，并且将来任何对此资源的引用都应该使用本响应返回的若干个 URI 之一。
     */
    public static final int MOVED_PERMANENTLY = 301;

    /**
     * 请求的资源临时从不同的 URI响应请求。该重定向是临时的。
     */
    public static final int MOVE_TEMPORARILY = 302;

    private int code;
    private String msg;
    private Object data;

    public CommonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public CommonResult() {
    }

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonResult success(Object data) {
        this.code = SUCCESS;
        this.msg = "success";
        this.data = data;
        return this;
    }

    /**
     * 未登录时使用
     *
     * @param data 错误信息
     */
    public CommonResult unauthorized(Object data) {
        this.code = UNAUTHORIZED;
        this.msg = "unauthorized";
        this.data = data;
        return this;
    }

    /**
     * 未授权时使用
     *
     * @param data 错误信息
     */
    public CommonResult forbidden(Object data) {
        this.code = FORBIDDEN;
        this.msg = "forbidden";
        this.data = data;
        return this;
    }

    /**
     * 非业务类异常时使用
     *
     * @param data
     */
    public CommonResult internalServerError(Object data) {
        this.code = INTERNAL_SERVER_ERROR;
        this.msg = "internal server error";
        this.data = data;
        return this;
    }

    /**
     * 语义有误，当前请求无法被服务器理解，或者请求参数错误时使用
     *
     * @param data
     */
    public CommonResult badRequest(Object data) {
        this.code = BAD_REQUEST;
        this.msg = "bad request";
        this.data = data;
        return this;
    }

    /**
     * 被请求的资源已永久移动到新位置时使用，并且将来任何对此资源的引用都应该使用本响应返回的若干个 URI 之一。
     *
     * @param data
     */
    public CommonResult movedPermanently(Object data) {
        this.code = MOVED_PERMANENTLY;
        this.msg = "moved permanently";
        this.data = data;
        return this;
    }

    /**
     * 请求的资源临时从不同的 URI响应请求时使用。该重定向是临时的。
     *
     * @param data
     */
    public CommonResult moveTemporarily(Object data) {
        this.code = MOVE_TEMPORARILY;
        this.msg = "move temporarily";
        this.data = data;
        return this;
    }

    /**
     * 没有该资源
     *
     * @param data
     */
    public CommonResult notFound(Object data) {
        this.code = NOT_FOUND;
        this.msg = "not found";
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}