package com.ikco10.allbasketm;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {

    @Headers("Content-Type: application/json")
    @POST("memsignup.jsp")
    Observable<RepoResult> memsignup(@Body JsonObject params);

    @Headers("Content-Type: application/json")
    @POST("memmodify.jsp")
    Call<RepoResult> memmodify(@Body JsonObject params);

    @GET("memcheck.jsp")
    Call<FragmentMemberSignup2.Result> memcheck(@Query("num") String num, @Query("type") String type);

    @GET("memverify.jsp")
    Observable<RepoMember> memverify(@Query("name") String name, @Query("cel") String cel);

}