package com.heritage.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.HttpStatus;
public class APIError {
    private Integer status;
    @JsonFormat(
            shape = Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private Object errors;

    private APIError() {
        this.timestamp = LocalDateTime.now();
    }

    public APIError(HttpStatus status) {
        this();
        this.status = status.value();
    }

    public APIError(HttpStatus status, Throwable ex) {
        this();
        this.status = status.value();
        this.message = "Unexpected Error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public APIError(String message, HttpStatus status, Throwable tx) {
        this();
        this.status = status.value();
        this.message = message;
        this.debugMessage = tx.getLocalizedMessage();
    }

    public APIError(String message, HttpStatus httpStatus) {
        this();
        this.status = httpStatus.value();
        this.message = message;
    }

    public APIError(String message, Object errors, HttpStatus httpStatus) {
        this();
        this.message = message;
        this.status = httpStatus.value();
        this.errors = errors;
    }

    public APIError(Object errors, HttpStatus httpStatus, Throwable tx) {
        this();
        this.status = httpStatus.value();
        this.debugMessage = tx.getLocalizedMessage();
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public Integer getStatus() {
        return this.status;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDebugMessage() {
        return this.debugMessage;
    }

    public Object getErrors() {
        return this.errors;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    @JsonFormat(
            shape = Shape.STRING,
            pattern = "dd-MM-yyyy hh:mm:ss"
    )
    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setDebugMessage(final String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public void setErrors(final Object errors) {
        this.errors = errors;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof APIError)) {
            return false;
        } else {
            APIError other = (APIError)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label71: {
                    Object this$status = this.getStatus();
                    Object other$status = other.getStatus();
                    if (this$status == null) {
                        if (other$status == null) {
                            break label71;
                        }
                    } else if (this$status.equals(other$status)) {
                        break label71;
                    }

                    return false;
                }

                Object this$timestamp = this.getTimestamp();
                Object other$timestamp = other.getTimestamp();
                if (this$timestamp == null) {
                    if (other$timestamp != null) {
                        return false;
                    }
                } else if (!this$timestamp.equals(other$timestamp)) {
                    return false;
                }

                label57: {
                    Object this$message = this.getMessage();
                    Object other$message = other.getMessage();
                    if (this$message == null) {
                        if (other$message == null) {
                            break label57;
                        }
                    } else if (this$message.equals(other$message)) {
                        break label57;
                    }

                    return false;
                }

                Object this$debugMessage = this.getDebugMessage();
                Object other$debugMessage = other.getDebugMessage();
                if (this$debugMessage == null) {
                    if (other$debugMessage != null) {
                        return false;
                    }
                } else if (!this$debugMessage.equals(other$debugMessage)) {
                    return false;
                }

                Object this$errors = this.getErrors();
                Object other$errors = other.getErrors();
                if (this$errors == null) {
                    if (other$errors == null) {
                        return true;
                    }
                } else if (this$errors.equals(other$errors)) {
                    return true;
                }

                return false;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof APIError;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $status = this.getStatus();
        result = result * 59 + ($status == null ? 43 : $status.hashCode());
        Object $timestamp = this.getTimestamp();
        result = result * 59 + ($timestamp == null ? 43 : $timestamp.hashCode());
        Object $message = this.getMessage();
        result = result * 59 + ($message == null ? 43 : $message.hashCode());
        Object $debugMessage = this.getDebugMessage();
        result = result * 59 + ($debugMessage == null ? 43 : $debugMessage.hashCode());
        Object $errors = this.getErrors();
        result = result * 59 + ($errors == null ? 43 : $errors.hashCode());
        return result;
    }
}
