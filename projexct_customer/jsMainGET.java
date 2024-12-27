package com.abc.projexct_customer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class jsMainGET {

    @SerializedName("data")
    @Expose
    private List<jsValueGET> data;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("response")
    @Expose
    private String response;

    public List<jsValueGET> getData() {
        return data;
    }

    public void setData(List<jsValueGET> data) {
        this.data = data;
    }

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
