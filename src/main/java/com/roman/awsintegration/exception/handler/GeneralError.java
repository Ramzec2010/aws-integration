package com.roman.awsintegration.exception.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class GeneralError {

    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("error")
    private String error;

    public GeneralError() {
    }

    public GeneralError success(Boolean success) {
        this.success = success;
        return this;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public GeneralError status(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public GeneralError error(String error) {
        this.error = error;
        return this;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            GeneralError that = (GeneralError) o;
            return Objects.equals(this.success, that.success) && Objects.equals(this.status, that.status) && Objects.equals(this.error, that.error);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.success, this.status, this.error});
    }

    public String toString() {
        return "GeneralError{success=" + this.success + ", status=" + this.status + ", error='" + this.error + "'}";
    }

}
