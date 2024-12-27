package com.abc.projexct_customer;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIinterface {

    @GET("customer")
    Call<jsMainGET> getData
            (@Query("CompId") int CompId,
             @Query("BranchId") int BranchId,
             @Query("AppId") int AppId);


//http://192.168.1.37:8010/customer?CustId=CUST2-82-134-1&ActiveStatus=A&UpdatedBy=468

    @DELETE("customer")
    Call<jsMainDELETE> deldata(
            @Query("CustId") String CustId,
            @Query("ActiveStatus") String ActiveStatus,
            @Query("UpdatedBy") int UpdatedBy);


}