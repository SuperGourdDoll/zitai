package com.sgd.zitai.retrofit;




import com.sgd.zitai.constants.ConstantCode;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Allen Liu on 2016/5/27.
 */
public class MyRetrofit {
    public static Retrofit getInstance(){
        return RetrofitHelp.retrofit;
    }
    public MyRetrofit() {
    }
    private static class RetrofitHelp{
       private static final Retrofit  retrofit=new Retrofit.Builder()
               .baseUrl(ConstantCode.baseurl)
               .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
               .addConverterFactory(GsonConverterFactory.create())
               .build();
    }
}
