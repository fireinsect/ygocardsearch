package com.ocg.ocgcard.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * JSON 返回模型
 */
public class Result<D> implements Serializable {

    @JsonProperty("isSuccess")
    private boolean success = false;

    private int status;
    private String msg;

    private D data;

    public static <T> Result<T> create() {
        return new Result<>();
    }

    public boolean isSuccess() {
        return success;
    }

    public Result setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public Result<D> setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result<D> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public D getData() {
        return data;
    }

    public Result<D> setData(D data) {
        this.data = data;
        return this;
    }
}