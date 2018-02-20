package com.example.jonfi.trendypal.data_from_instagram;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jonfi on 20/02/2018.
 */

public class RestClient {

    public static RetrofitService getRetrofitService() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService.class);
    }

}
