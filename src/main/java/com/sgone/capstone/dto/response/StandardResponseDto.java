package com.sgone.capstone.dto.response;

public class StandardResponseDto <T> {

    private Boolean success;
    private String message;
    private T payload;

    public StandardResponseDto() {}

    public StandardResponseDto(Boolean success,
                               String message,
                               T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
