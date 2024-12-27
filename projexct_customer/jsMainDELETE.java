package com.abc.projexct_customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class jsMainDELETE {
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("response")
    @Expose
    private String response;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
