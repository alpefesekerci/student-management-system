package com.mini.project.v4.dto;

public class ServiceResult<T> {
    private final boolean success;
    private final String message;
    private final T data;

    public ServiceResult(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ServiceResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
