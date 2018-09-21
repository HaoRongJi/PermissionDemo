package com.hao.base.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络框架二次封装
 */
public class RetrofitUtils {

    private static volatile RetrofitUtils retrofitUtils;
    private OkHttpClient okHttpClient=null;

    private RetrofitUtils(){

        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build();





    }

    public<T> T createApi(String baseUrl,Class<T> clz){


        Retrofit retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
        return  retrofit.create(clz);

    }

    /**
     * 双重检验锁，同步锁
     * @return
     */
    public static RetrofitUtils getInstanse(){
        if (retrofitUtils==null){

            synchronized (RetrofitUtils.class){

                if (retrofitUtils==null){

                    retrofitUtils=new RetrofitUtils();

                }

            }

        }

        return  retrofitUtils;


    }



}
