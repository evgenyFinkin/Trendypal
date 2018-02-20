package com.example.jonfi.trendypal.data_from_instagram;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jonfi on 20/02/2018.
 */

public interface RetrofitService {

    @GET("v1/users/self")
    Call<InstagramResponse> getUser(@Query("access_token") String access_token);
}
