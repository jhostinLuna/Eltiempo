package com.eltiempo.dir.retrofit;

import com.eltiempo.dir.modelo.ObjPrediccion;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interfacetiempo {
    @GET("data/2.5/weather")
    Call<ObjPrediccion> getTiempo(@Query("q") String q, @Query("appid") String appid);

    @GET("data/2.5/forecast")
    Call<ObjPrediccion> getListTiempo(@Query("q") String q,
                                          @Query("lang") String lang,
                                          @Query("units") String units,
                                          @Query("appid") String appid);
}
