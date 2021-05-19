package com.eltiempo.dir.retrofit;

import com.eltiempo.dir.modelo.Tiempo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interfacetiempo {
    @GET("data/2.5/weather")
    Call<Tiempo> getTiempo(@Query("q") String q,@Query("appid") String appid);
}
