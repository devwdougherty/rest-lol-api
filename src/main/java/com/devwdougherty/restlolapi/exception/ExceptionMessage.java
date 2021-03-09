package com.devwdougherty.restlolapi.exception;

import java.util.Date;

public class ExceptionMessage {

    private String message;

    private Date timestamp;

    private Integer statusCode;

    private String type;

    private String path;

    public ExceptionMessage() {
    }

    public ExceptionMessage(String message, Date timestamp, Integer statusCode, String type, String path) {

        this.message = message;
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.type = type;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "ExceptionMessage{" +
                "message='" + message + '\'' +
                ", timestamp=" + timestamp +
                ", statusCode=" + statusCode +
                ", type='" + type + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
