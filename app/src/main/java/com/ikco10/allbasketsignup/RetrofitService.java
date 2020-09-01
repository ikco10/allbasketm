package com.ikco10.allbasketsignup;

import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface RetrofitService {

    @Headers("Content-Type: application/json")
    @POST("memsignup.jsp")
    Observable<RepoResult> memsignup(@Body JsonObject params);

    @Headers("Content-Type: application/json")
    @POST("memmodify.jsp")
    Call<RepoResult> memmodify(@Body JsonObject params);

    @GET("getmemberregistercheck.jsp")
    Call<FragmentMemberSignup2.Result> memcheck(@Query("num") String num, @Query("type") String type);

}